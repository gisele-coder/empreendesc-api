package br.com.empreendesc.exception

import java.time.LocalDateTime

data class ApiError(
    val timestamp: LocalDateTime?,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)

