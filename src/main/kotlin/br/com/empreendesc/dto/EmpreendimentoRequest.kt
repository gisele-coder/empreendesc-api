package br.com.empreendesc.dto

import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.domain.Status

data class EmpreendimentoRequest(
    val nome: String,
    val nomeEmpreendedor: String,
    val municipio: String,
    val segmento: Segmento,
    val contato: String,
    val status: Status
)

