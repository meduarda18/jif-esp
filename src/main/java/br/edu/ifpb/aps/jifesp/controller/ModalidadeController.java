package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import br.edu.ifpb.aps.jifesp.service.ModalidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modalidade")
public class ModalidadeController {

    private final ModalidadeService modalidadeService;

    public ModalidadeController(ModalidadeService modalidadeService) {
        this.modalidadeService = modalidadeService;
    }

    @PostMapping
    public ModalidadeEntity salvarModalidade(@RequestBody ModalidadeEntity modalidadeEntity) {
        return modalidadeService.save(modalidadeEntity);
    }

    @PutMapping("/{id}")
    public ModalidadeEntity atualizarModalidade(@PathVariable Long id, @RequestBody ModalidadeEntity modalidadeEntity) {
        return modalidadeService.update(id, modalidadeEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarModalidade(@PathVariable Long id) {
        modalidadeService.delete(id);
    }

    @GetMapping
    public List<ModalidadeEntity> listarModalidades() {
        return modalidadeService.findAll();
    }

}