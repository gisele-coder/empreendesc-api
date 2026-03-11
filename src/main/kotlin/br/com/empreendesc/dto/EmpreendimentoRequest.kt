package br.com.empreendesc.dto

import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.domain.Status
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class EmpreendimentoRequest(

    @Schema(
        description = "Name of the business",
        example = "Startup Tech"
    )
    @field:NotBlank
    @field:Size(max = 255)
    val nome: String,

    @Schema(
        description = "Name of the entrepreneur",
        example = "Ana Silva"
    )
    @field:NotBlank
    val nomeEmpreendedor: String,

    @Schema(
        description = "City where the business is located",
        example = "Florianopolis"
    )
    @field:NotBlank
    val municipio: String,

    @Schema(
        description = "Business segment",
        example = "TECNOLOGIA"
    )
    val segmento: Segmento,

    @Schema(
        description = "Contact information for the business",
        example = "[ana@startup.com](mailto:ana@startup.com)"
    )
    @field:NotBlank
    val contato: String,

    @Schema(
        description = "Current status of the business",
        example = "ATIVO"
    )
    val status: Status
)

