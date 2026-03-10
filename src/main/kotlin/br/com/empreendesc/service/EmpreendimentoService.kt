package br.com.empreendesc.service

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.repository.EmpreendimentoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EmpreendimentoService(
    private val empreendimentoRepository: EmpreendimentoRepository
) {
    fun create(empreendimento: Empreendimento): Empreendimento =
        empreendimentoRepository.save(empreendimento)

    fun findAll(pageable: Pageable): Page<Empreendimento> =
        empreendimentoRepository.findAll(pageable)

    fun buscarPorMunicipio(municipio: String, pageable: Pageable): Page<Empreendimento> =
        empreendimentoRepository.findByMunicipioIgnoreCase(municipio, pageable)

    fun buscarPorSegmento(segmento: Segmento, pageable: Pageable): Page<Empreendimento> =
        empreendimentoRepository.findBySegmento(segmento, pageable)

    fun findById(id: Long): Empreendimento? =
        empreendimentoRepository.findById(id).orElse(null)

    fun delete(id: Long) {
        if (empreendimentoRepository.existsById(id)) {
            empreendimentoRepository.deleteById(id)
        }
    }
}

