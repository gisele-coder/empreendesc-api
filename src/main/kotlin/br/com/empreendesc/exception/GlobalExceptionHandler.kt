package br.com.empreendesc.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.OffsetDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElement(
        ex: NoSuchElementException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> =
        buildError(HttpStatus.NOT_FOUND, ex, request)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> =
        buildError(HttpStatus.BAD_REQUEST, ex, request)

    @ExceptionHandler(Exception::class)
    fun handleGeneric(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> =
        buildError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request)

    private fun buildError(
        status: HttpStatus,
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val body = ApiError(
            timestamp = OffsetDateTime.now(),
            status = status.value(),
            error = status.reasonPhrase,
            message = ex.message,
            path = request.requestURI
        )
        return ResponseEntity.status(status).body(body)
    }
}

