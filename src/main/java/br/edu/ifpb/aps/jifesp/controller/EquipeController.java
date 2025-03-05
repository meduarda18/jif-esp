package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.EquipeEntity;
import br.edu.ifpb.aps.jifesp.service.EquipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    private EquipeService equipeService;

    public EquipeController(EquipeService equipeService){
        this.equipeService = equipeService;
    }

    @PostMapping
    public EquipeEntity salvarEquipe(@RequestBody EquipeEntity equipeEntity) {
        return equipeService.save(equipeEntity);
    }

    @PutMapping("/{id}")
    public EquipeEntity atualizarEquipe(@PathVariable Long id, @RequestBody EquipeEntity equipeEntity) {
        return equipeService.update(id, equipeEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarEquipe(@PathVariable Long id) {
        equipeService.delete(id);
    }

    @GetMapping
    public List<EquipeEntity> listarEquipe() {
        return equipeService.findAll();
    }
}
