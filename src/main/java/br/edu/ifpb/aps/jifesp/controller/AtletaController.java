package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.AtletaEntity;
import br.edu.ifpb.aps.jifesp.service.AtletaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atletas")
public class AtletaController {

    private final AtletaService atletaService;

    public AtletaController(AtletaService atletaService){
        this.atletaService = atletaService;
    }

    @PostMapping
    public AtletaEntity salvarAtleta(@RequestBody AtletaEntity atletaEntity) {
        return atletaService.save(atletaEntity);
    }

    @PutMapping("/{id}")
    public AtletaEntity atualizarAtleta(@PathVariable Long id, @RequestBody AtletaEntity atletaEntity) {
        return atletaService.update(id, atletaEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarAtleta(@PathVariable Long id) {
        atletaService.delete(id);
    }

    @GetMapping
    public List<AtletaEntity> listarAtletas() {
        return atletaService.findAll();
    }
}
