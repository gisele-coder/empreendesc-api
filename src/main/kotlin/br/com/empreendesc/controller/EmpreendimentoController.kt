package br.com.empreendesc.controller

import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.dto.EmpreendimentoRequest
import br.com.empreendesc.dto.EmpreendimentoResponse
import br.com.empreendesc.mapper.EmpreendimentoMapper
import br.com.empreendesc.service.EmpreendimentoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/empreendimentos")
class EmpreendimentoController(
    private val empreendimentoService: EmpreendimentoService
) {

    @PostMapping
    fun create(@Valid @RequestBody request: EmpreendimentoRequest): ResponseEntity<EmpreendimentoResponse> {

        val entity = EmpreendimentoMapper.toEntity(request)
        val saved = empreendimentoService.create(entity)

        val response = EmpreendimentoMapper.toResponse(saved)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id)
            .toUri()

        return ResponseEntity.created(location).body(response)
    }

    @GetMapping
    fun findAll(
        @RequestParam(required = false) municipio: String?,
        @RequestParam(required = false) segmento: Segmento?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<EmpreendimentoResponse>> {

        val pageable = PageRequest.of(page, size)

        val pageResult = when {
            !municipio.isNullOrBlank() ->
                empreendimentoService.buscarPorMunicipio(municipio, pageable)

            segmento != null ->
                empreendimentoService.buscarPorSegmento(segmento, pageable)

            else ->
                empreendimentoService.findAll(pageable)
        }

        val responsePage = pageResult.map(EmpreendimentoMapper::toResponse)

        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<EmpreendimentoResponse> {

        val empreendimento = empreendimentoService.findById(id)
            ?: throw NoSuchElementException("Empreendimento não encontrado")

        val response = EmpreendimentoMapper.toResponse(empreendimento)

        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: EmpreendimentoRequest
    ): ResponseEntity<EmpreendimentoResponse> {

        val existing = empreendimentoService.findById(id)
            ?: throw NoSuchElementException("Empreendimento não encontrado")

        val updated = existing.apply {
            nome = request.nome
            nomeEmpreendedor = request.nomeEmpreendedor
            municipio = request.municipio
            segmento = request.segmento
            contato = request.contato
            status = request.status
        }

        val saved = empreendimentoService.create(updated)

        val response = EmpreendimentoMapper.toResponse(saved)

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {

        val existing = empreendimentoService.findById(id)
            ?: throw NoSuchElementException("Empreendimento não encontrado")

        empreendimentoService.delete(existing.id!!)

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/teste")
    fun teste(): String {
        return "API funcionando"
    }
}
