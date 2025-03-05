package br.edu.ifpb.aps.jifesp.controller;

import br.edu.ifpb.aps.jifesp.entity.JogoEntity;
import br.edu.ifpb.aps.jifesp.service.JogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService){
        this.jogoService = jogoService;
    }

    @PostMapping
    public JogoEntity salvarJogo(@RequestBody JogoEntity jogoEntity) {
        return jogoService.save(jogoEntity);
    }

    @PutMapping("/{id}")
    public JogoEntity atualizarJogo(@PathVariable Long id, @RequestBody JogoEntity jogoEntity) {
        return jogoService.update(id, jogoEntity);
    }

    @DeleteMapping("/{id}")
    public void deletarJogo(@PathVariable Long id) {
        jogoService.delete(id);
    }

    @GetMapping
    public List<JogoEntity> listarJogos() {
        return jogoService.findAll();
    }
    
}
