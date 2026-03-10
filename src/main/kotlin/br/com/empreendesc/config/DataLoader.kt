package br.com.empreendesc.config

import br.com.empreendesc.domain.Empreendimento
import br.com.empreendesc.domain.Segmento
import br.com.empreendesc.domain.Status
import br.com.empreendesc.repository.EmpreendimentoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader {

    @Bean
    fun loadData(repository: EmpreendimentoRepository) = CommandLineRunner {

        val segmentos = Segmento.values()

        for (i in 1..150) {
            val emp = Empreendimento(
                nome = "Empresa $i",
                nomeEmpreendedor = "Empreendedor $i",
                municipio = if (i % 2 == 0) "Florianopolis" else "Joinville",
                segmento = segmentos[i % segmentos.size],
                contato = "contato$i@email.com",
                status = Status.ATIVO
            )

            repository.save(emp)
        }

        println("150 empreendimentos criados para teste")
    }
}
