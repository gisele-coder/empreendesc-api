package br.com.empreendesc.repository

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.domain.Segmento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface EmpreendimentoRepository : JpaRepository<Empreendimento, Long> {

    fun findByMunicipioIgnoreCase(municipio: String, pageable: Pageable): Page<Empreendimento>

    fun findBySegmento(segmento: Segmento, pageable: Pageable): Page<Empreendimento>
}

