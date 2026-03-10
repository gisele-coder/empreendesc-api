package br.com.empreendesc.service

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.repository.EmpreendimentoRepository
import org.springframework.stereotype.Service

@Service
class EmpreendimentoService(
    private val empreendimentoRepository: EmpreendimentoRepository
) {
    fun create(empreendimento: Empreendimento): Empreendimento =
        empreendimentoRepository.save(empreendimento)

    fun findAll(): List<Empreendimento> =
        empreendimentoRepository.findAll()

    fun findById(id: Long): Empreendimento? =
        empreendimentoRepository.findById(id).orElse(null)

    fun delete(id: Long) {
        if (empreendimentoRepository.existsById(id)) {
            empreendimentoRepository.deleteById(id)
        }
    }
}

