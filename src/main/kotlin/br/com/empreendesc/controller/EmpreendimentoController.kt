package br.com.empreendesc.controller

import br.com.empreendesc.dto.EmpreendimentoRequest
import br.com.empreendesc.dto.EmpreendimentoResponse
import br.com.empreendesc.mapper.EmpreendimentoMapper
import br.com.empreendesc.service.EmpreendimentoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/empreendimentos")
class EmpreendimentoController(
    private val empreendimentoService: EmpreendimentoService
) {

    @PostMapping
    fun create(@RequestBody request: EmpreendimentoRequest): ResponseEntity<EmpreendimentoResponse> {
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
    fun findAll(): ResponseEntity<List<EmpreendimentoResponse>> {
        val empreendimentos = empreendimentoService.findAll()
        val responses = empreendimentos.map(EmpreendimentoMapper::toResponse)
        return ResponseEntity.ok(responses)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<EmpreendimentoResponse> {
        val empreendimento = empreendimentoService.findById(id)
            ?: return ResponseEntity.notFound().build()

        val response = EmpreendimentoMapper.toResponse(empreendimento)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val existing = empreendimentoService.findById(id)
            ?: return ResponseEntity.notFound().build()

        empreendimentoService.delete(existing.id!!)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: EmpreendimentoRequest
    ): ResponseEntity<EmpreendimentoResponse> {
        val existing = empreendimentoService.findById(id)
            ?: return ResponseEntity.notFound().build()

        val updatedEntity = existing.apply {
            nome = request.nome
            nomeEmpreendedor = request.nomeEmpreendedor
            municipio = request.municipio
            segmento = request.segmento
            contato = request.contato
            status = request.status
        }

        val saved = empreendimentoService.create(updatedEntity)
        val response = EmpreendimentoMapper.toResponse(saved)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/teste")
    fun teste(): String {
        return "API funcionando"
    }
}

