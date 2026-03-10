package br.com.empreendesc.repository

import br.com.empreendesc.domain.Empreendimento
import org.springframework.data.jpa.repository.JpaRepository

interface EmpreendimentoRepository : JpaRepository<Empreendimento, Long>

