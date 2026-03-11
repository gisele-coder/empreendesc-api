package br.com.empreendesc.exception

import java.time.OffsetDateTime

data class ApiError(
    val timestamp: OffsetDateTime,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)

