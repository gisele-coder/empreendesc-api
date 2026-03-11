package br.com.empreendesc.service

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.domain.Status
import br.com.empreendesc.repository.EmpreendimentoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

@ExtendWith(MockitoExtension::class)
class EmpreendimentoServiceTest {

    @Mock
    private lateinit var repository: EmpreendimentoRepository

    @InjectMocks
    private lateinit var service: EmpreendimentoService

    @Test
    fun `should create empreendimento`() {

        val empreendimento = Empreendimento(
            nome = "Teste",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        whenever(repository.save(empreendimento)).thenReturn(empreendimento)

        val result = service.create(empreendimento)

        assertNotNull(result)
        assertEquals("Teste", result.nome)

        verify(repository).save(empreendimento)
    }

    @Test
    fun `should find empreendimento by id`() {
        val empreendimento = Empreendimento(
            id = 1L,
            nome = "Teste",
            nomeEmpreendedor = "Empreendedor",
            municipio = "Cidade",
            segmento = Segmento.COMERCIO,
            contato = "contato@test.com",
            status = Status.ATIVO
        )

        whenever(repository.findById(1L)).thenReturn(Optional.of(empreendimento))

        val result = service.findById(1L)

        assertNotNull(result)
        assertEquals(1L, result?.id)
        verify(repository).findById(1L)
    }

    @Test
    fun `should return empty when empreendimento does not exist`() {
        whenever(repository.findById(1L)).thenReturn(Optional.empty())

        val result = service.findById(1L)

        assertNull(result)
        verify(repository).findById(1L)
    }

    @Test
    fun `should delete empreendimento`() {
        whenever(repository.existsById(1L)).thenReturn(true)
        doNothing().whenever(repository).deleteById(1L)

        service.delete(1L)

        verify(repository).existsById(1L)
        verify(repository).deleteById(1L)
    }
}

