package br.edu.ifpb.aps.jifesp.testeController;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifpb.aps.jifesp.controller.EquipeController;
import br.edu.ifpb.aps.jifesp.entity.EquipeEntity;
import br.edu.ifpb.aps.jifesp.service.EquipeService;

@WebMvcTest({ EquipeController.class })
@AutoConfigureMockMvc
public class EquipeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private EquipeService equipeService;

    private EquipeEntity equipe1;
    private EquipeEntity equipe2;

    @BeforeEach
    void setUp() {
        equipe1 = new EquipeEntity("Tigres IFPB", "João", List.of());
        equipe2 = new EquipeEntity("Leões IFPB", "Maria", List.of());
    }

    @Test
    void deveSalvarEquipeComSucesso() throws Exception {
        Mockito.when(equipeService.save(Mockito.any(EquipeEntity.class))).thenReturn(equipe1);

        mockMvc.perform(post("/equipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(equipe1))).andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeEquipe").value("Tigres IFPB"))
                .andExpect(jsonPath("$.capitao").value("João"));
    }

    @Test
    void deveEditarEquipeComSucesso() throws Exception {
        Mockito.when(equipeService.update(
                Mockito.eq(2L),
                Mockito.any(EquipeEntity.class)))
                .thenReturn(equipe2);

        mockMvc.perform(
                put("/equipes/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipe2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeEquipe").value("Leões IFPB"))
                .andExpect(jsonPath("$.capitao").value("Maria"));
    }

    @Test
    void deveListarEquipesComSucesso() throws Exception {
        Mockito.when(equipeService.findAll())
                .thenReturn(List.of(equipe1, equipe2));

        mockMvc.perform(get("/equipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeEquipe").value("Tigres IFPB"))
                .andExpect(jsonPath("$[1].nomeEquipe").value("Leões IFPB"));
    }

    @Test
    void deveDeletarEquipeComSucesso() throws Exception {
        mockMvc.perform(delete("/equipes/{id}", 1L))
                .andExpect(status().isOk());

        Mockito.verify(equipeService).delete(1L);
    }
}
