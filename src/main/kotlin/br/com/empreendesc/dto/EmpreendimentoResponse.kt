package br.com.empreendesc.dto

import br.com.empreendesc.domain.Segmento

data class EmpreendimentoResponse(
    val id: Long,
    val nome: String,
    val municipio: String,
    val segmento: Segmento
)

