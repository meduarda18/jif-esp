package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.ProfessorEntity;
import br.edu.ifpb.aps.jifesp.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorEntity salvarProfessor(@RequestBody ProfessorEntity professorEntity) {
        return professorService.save(professorEntity);
    }

    @PutMapping("/{id}")
    public ProfessorEntity atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorEntity professorEntity) {
        return professorService.update(id, professorEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable Long id) {
        professorService.delete(id);
    }

    @GetMapping
    public List<ProfessorEntity> listarProfessores() {
        return professorService.findAll();
    }

}
