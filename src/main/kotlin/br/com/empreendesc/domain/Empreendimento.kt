package br.com.empreendesc.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "empreendimentos")
class Empreendimento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 255)
    var nome: String,

    @Column(name = "nome_empreendedor", nullable = false, length = 255)
    var nomeEmpreendedor: String,

    @Column(nullable = false, length = 255)
    var municipio: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    var segmento: Segmento,

    @Column(nullable = false, length = 255)
    var contato: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    var status: Status
) {
    // JPA requires a no-arg constructor
    protected constructor() : this(
        id = null,
        nome = "",
        nomeEmpreendedor = "",
        municipio = "",
        segmento = Segmento.SERVICOS,
        contato = "",
        status = Status.ATIVO
    )
}

