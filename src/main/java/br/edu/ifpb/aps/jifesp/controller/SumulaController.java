package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.SumulaEntity;
import br.edu.ifpb.aps.jifesp.service.SumulaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SumulaController {
    
    private final SumulaService sumulaService;

    public SumulaController(SumulaService sumulaService) {
        this.sumulaService = sumulaService;
    }

    @PostMapping
    public SumulaEntity salvarSumula(@RequestBody SumulaEntity sumulaEntity) {
        return sumulaService.save(sumulaEntity);
    }

    @PutMapping("/{id}")
    public SumulaEntity atualizarSumula(@PathVariable Long id, @RequestBody SumulaEntity sumulaEntity) {
        return sumulaService.update(id, sumulaEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarSumula(@PathVariable Long id) {
        sumulaService.delete(id);
    }

    @GetMapping
    public List<SumulaEntity> listarSumulas() {
        return sumulaService.findAll();
    }

}
