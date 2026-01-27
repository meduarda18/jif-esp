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

import br.edu.ifpb.aps.jifesp.controller.AtletaController;
import br.edu.ifpb.aps.jifesp.entity.AtletaEntity;
import br.edu.ifpb.aps.jifesp.entity.Situacao;
import br.edu.ifpb.aps.jifesp.service.AtletaService;

@WebMvcTest({ AtletaController.class })
@AutoConfigureMockMvc
public class AtletaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private AtletaService atletaService;

    private AtletaEntity atleta1;
    private AtletaEntity atleta2;

    @BeforeEach
    void setUp() {
        atleta1 = new AtletaEntity("João Vitor", 12345);
        atleta1.setSituacao(Situacao.APTO);

        atleta2 = new AtletaEntity("Maria Eduarda", 67890);
        atleta2.setSituacao(Situacao.INAPTO);
    }

    @Test
    void deveSalvarAtletaComSucesso() throws Exception {
        Mockito.when(atletaService.save(Mockito.any(AtletaEntity.class))).thenReturn(atleta1);

        mockMvc.perform(
            post("/atletas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(atleta1))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("João Vitor"))
        .andExpect(jsonPath("$.matricula").value(12345));
    }

    @Test
    void deveEditarAtletaComSucesso() throws Exception {
        Mockito.when(atletaService.update(
            Mockito.eq(2L), 
            Mockito.any(AtletaEntity.class)))
        .thenReturn(atleta2);

        mockMvc.perform(put("/atletas/{id}", 2L)
            .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString((atleta2))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value("Maria Eduarda"))
            .andExpect(jsonPath("$.matricula").value(67890));
    }

    @Test
    void deveListarAtletasComSucesso() throws Exception {
        Mockito.when(atletaService.findAll()).thenReturn(List.of(atleta1, atleta2));

        mockMvc.perform(get("/atletas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("João Vitor"))
            .andExpect(jsonPath("$[1].nome").value("Maria Eduarda"));
    }

    @Test
    void deveDeletarAtletaComSucesso() throws Exception {
        mockMvc.perform(delete("/atletas/{id}", 1L))
            .andExpect(status().isOk());
        
        Mockito.verify(atletaService).delete(1L);
    }

    @Test
    void deveListarAtletasPorSituacao() throws Exception {
        List<AtletaEntity> atletas = List.of(atleta1, atleta2);
        Mockito.when(atletaService.filtrarPorSituacao(Situacao.APTO)).thenReturn(atletas);

        mockMvc.perform(
            get("/atletas/filtrar")
                .param("situacao", "APTO")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].nome").value("João Vitor"));
    }
}
