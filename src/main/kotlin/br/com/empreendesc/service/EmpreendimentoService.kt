package br.com.empreendesc.service

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.repository.EmpreendimentoRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmpreendimentoService(
    private val empreendimentoRepository: EmpreendimentoRepository
) {

    private val log = LoggerFactory.getLogger(EmpreendimentoService::class.java)

    @Transactional
    fun create(empreendimento: Empreendimento): Empreendimento {
        log.info("Creating empreendimento: {}", empreendimento.nome)
        return empreendimentoRepository.save(empreendimento)
    }

    @Transactional
    fun update(empreendimento: Empreendimento): Empreendimento {
        log.info("Updating empreendimento: {}", empreendimento.nome)
        return empreendimentoRepository.save(empreendimento)
    }

    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<Empreendimento> =
        empreendimentoRepository.findAll(pageable)

    @Transactional(readOnly = true)
    fun buscarPorMunicipio(municipio: String, pageable: Pageable): Page<Empreendimento> =
        empreendimentoRepository.findByMunicipioIgnoreCase(municipio, pageable)

    @Transactional(readOnly = true)
    fun buscarPorSegmento(segmento: Segmento, pageable: Pageable): Page<Empreendimento> =
        empreendimentoRepository.findBySegmento(segmento, pageable)

    @Transactional(readOnly = true)
    fun findById(id: Long): Empreendimento? {
        log.info("Searching empreendimento id {}", id)
        return empreendimentoRepository.findById(id).orElse(null)
    }

    @Transactional
    fun delete(id: Long) {
        log.info("Deleting empreendimento id {}", id)
        if (empreendimentoRepository.existsById(id)) {
            empreendimentoRepository.deleteById(id)
        }
    }
}

