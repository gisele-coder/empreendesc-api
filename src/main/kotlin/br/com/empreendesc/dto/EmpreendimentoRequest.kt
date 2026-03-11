package br.com.empreendesc.dto

import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.domain.Status
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class EmpreendimentoRequest(

    @field:NotBlank
    @field:Size(max = 255)
    val nome: String,

    @field:NotBlank
    val nomeEmpreendedor: String,

    @field:NotBlank
    val municipio: String,

    val segmento: Segmento,

    @field:NotBlank
    val contato: String,

    val status: Status
)

