package br.com.empreendesc.mapper

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.dto.EmpreendimentoRequest
import br.com.empreendesc.dto.EmpreendimentoResponse

object EmpreendimentoMapper {

    fun toEntity(request: EmpreendimentoRequest): Empreendimento =
        Empreendimento(
            nome = request.nome,
            nomeEmpreendedor = request.nomeEmpreendedor,
            municipio = request.municipio,
            segmento = request.segmento,
            contato = request.contato,
            status = request.status
        )

    fun toResponse(entity: Empreendimento): EmpreendimentoResponse =
        EmpreendimentoResponse(
            id = requireNotNull(entity.id) { "Empreendimento id must not be null" },
            nome = entity.nome,
            municipio = entity.municipio,
            segmento = entity.segmento
        )
}

