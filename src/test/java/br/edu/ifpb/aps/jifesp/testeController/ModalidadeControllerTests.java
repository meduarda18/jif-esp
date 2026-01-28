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

import br.edu.ifpb.aps.jifesp.controller.ModalidadeController;
import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import br.edu.ifpb.aps.jifesp.entity.NomeModalidade;
import br.edu.ifpb.aps.jifesp.service.ModalidadeService;

@WebMvcTest({ ModalidadeController.class })
@AutoConfigureMockMvc
public class ModalidadeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ModalidadeService modalidadeService;

    ModalidadeEntity modalidade1;
    ModalidadeEntity modalidade2;

    @BeforeEach
    void setUp() {
        modalidade1 = new ModalidadeEntity(NomeModalidade.TENIS_DE_MESA_FEMININO, 1,
                "Usar tenis e uniforme adequado...");
        modalidade2 = new ModalidadeEntity(NomeModalidade.VOLEIBOL_FEMININO, 6,
                "Usar tenis e uniforme numerado corretamente...");
    }

    @Test
    void deveSalvarUmaModalidadeComSucesso() throws Exception {
        Mockito.when(modalidadeService.save(Mockito.any(ModalidadeEntity.class))).thenReturn(modalidade2);

        mockMvc.perform(post("/modalidade").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modalidade2))).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("VOLEIBOL_FEMININO"))
                .andExpect(jsonPath("$.quantidadeParticipantes").value(6))
                .andExpect(jsonPath("$.regulamento").value("Usar tenis e uniforme numerado corretamente..."));
    }

    @Test
    void deveEditarUmaModalidadeComSucesso() throws Exception {
        Mockito.when(modalidadeService.update(Mockito.eq(1L), Mockito.any(ModalidadeEntity.class)))
                .thenReturn(modalidade1);

        mockMvc.perform(put("/modalidade/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modalidade1))).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("TENIS_DE_MESA_FEMININO"))
                .andExpect(jsonPath("$.quantidadeParticipantes").value(1))
                .andExpect(jsonPath("$.regulamento").value("Usar tenis e uniforme adequado..."));
    }

    @Test
    void deveListarModalidadeComSucesso() throws Exception {
        Mockito.when(modalidadeService.findAll()).thenReturn(List.of(modalidade1, modalidade2));

        mockMvc.perform(get("/modalidade")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("TENIS_DE_MESA_FEMININO"))
                .andExpect(jsonPath("$[0].quantidadeParticipantes").value(1))
                .andExpect(jsonPath("$[0].regulamento").value("Usar tenis e uniforme adequado..."))
                .andExpect(jsonPath("$[1].nome").value("VOLEIBOL_FEMININO"))
                .andExpect(jsonPath("$[1].quantidadeParticipantes").value(6))
                .andExpect(jsonPath("$[1].regulamento").value("Usar tenis e uniforme numerado corretamente..."));
    }

    @Test
    void deveDeletarUmaModalidadeComSucesso() throws Exception {
        mockMvc.perform(delete("/modalidade/{id}", 1L)).andExpect(status().isOk());

        Mockito.verify(modalidadeService).delete(1L);
    }
}
