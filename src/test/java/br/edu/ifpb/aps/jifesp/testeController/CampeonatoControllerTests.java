package br.edu.ifpb.aps.jifesp.testeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifpb.aps.jifesp.controller.CampeonatoController;
import br.edu.ifpb.aps.jifesp.entity.CampeonatoEntity;
import br.edu.ifpb.aps.jifesp.service.CampeonatoService;

@WebMvcTest({ CampeonatoController.class })
@AutoConfigureMockMvc
public class CampeonatoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CampeonatoService campeonatoService;

    CampeonatoEntity campeonato;
    CampeonatoEntity campeonato2;

    @BeforeEach
    void setUp() {
        campeonato = new CampeonatoEntity("Jogos Internos 2026", List.of());
        campeonato2 = new CampeonatoEntity("Jogos Internos 2025", List.of());
    }

    @Test
    void deveSalvarUmCampeonatoComSucesso() throws Exception {
        Mockito.when(campeonatoService.save(Mockito.any(CampeonatoEntity.class))).thenReturn(campeonato);

        mockMvc.perform(
                post("/campeonatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campeonato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Jogos Internos 2026"));
    }

    @Test
    void deveEditarUmCampeonatoComSucesso() throws Exception {
        Mockito.when(campeonatoService.update(Mockito.eq(1L), Mockito.any(CampeonatoEntity.class)))
                .thenReturn(campeonato);

        mockMvc.perform(
                put("/campeonatos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campeonato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Jogos Internos 2026"));
    }

    @Test
    void deveListarCampeonatosComSucesso() throws Exception {
        Mockito.when(campeonatoService.findAll()).thenReturn(List.of(campeonato, campeonato2));

        mockMvc.perform(get("/campeonatos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Jogos Internos 2026"))
                .andExpect(jsonPath("$[1].nome").value("Jogos Internos 2025"));
    }

    @Test
    void deveDeletarUmCampeonatoComSucesso() throws Exception {
        mockMvc.perform(delete("/campeonatos/{id}", 1L))
                .andExpect(status().isOk());

        Mockito.verify(campeonatoService).delete(1L);
    }
}
