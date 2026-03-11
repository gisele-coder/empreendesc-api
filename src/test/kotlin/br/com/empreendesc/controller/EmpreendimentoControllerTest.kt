package br.com.empreendesc.controller

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.domain.Status
import br.com.empreendesc.dto.EmpreendimentoRequest
import br.com.empreendesc.service.EmpreendimentoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class EmpreendimentoControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var service: EmpreendimentoService

    @Test
    fun `POST empreendimentos should create empreendimento`() {
        val request = EmpreendimentoRequest(
            nome = "Teste",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        val saved = Empreendimento(
            id = 1L,
            nome = request.nome,
            nomeEmpreendedor = request.nomeEmpreendedor,
            municipio = request.municipio,
            segmento = request.segmento,
            contato = request.contato,
            status = request.status
        )

        whenever(service.create(any())).thenReturn(saved)

        mockMvc.perform(
            post("/empreendimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("Teste"))
    }

    @Test
    fun `GET empreendimentos should return list`() {
        val empreendimentos = listOf(
            Empreendimento(
                id = 1L,
                nome = "Teste",
                nomeEmpreendedor = "Empreendedor",
                municipio = "Cidade",
                segmento = Segmento.COMERCIO,
                contato = "contato@test.com",
                status = Status.ATIVO
            )
        )

        whenever(service.findAll(any())).thenReturn(org.springframework.data.domain.PageImpl(empreendimentos))

        mockMvc.perform(get("/empreendimentos"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value(1L))
            .andExpect(jsonPath("$.content[0].nome").value("Teste"))
    }

    @Test
    fun `GET empreendimentos by id should return one`() {
        val empreendimento = Empreendimento(
            id = 1L,
            nome = "Teste",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        whenever(service.findById(1L)).thenReturn(empreendimento)

        mockMvc.perform(get("/empreendimentos/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("Teste"))
    }

    @Test
    fun `PUT empreendimentos should update empreendimento`() {

        val request = EmpreendimentoRequest(
            nome = "Atualizado",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        val existing = Empreendimento(
            id = 1L,
            nome = "Teste",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        existing.nome = "Atualizado"
        val updated = existing

        whenever(service.findById(1L)).thenReturn(existing)
        whenever(service.create(any())).thenReturn(updated)

        mockMvc.perform(
            put("/empreendimentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("Atualizado"))
    }

    @Test
    fun `DELETE empreendimentos should delete empreendimento`() {
        val existing = Empreendimento(
            id = 1L,
            nome = "Teste",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        whenever(service.findById(1L)).thenReturn(existing)

        mockMvc.perform(delete("/empreendimentos/1"))
            .andExpect(status().isNoContent)
    }
}

