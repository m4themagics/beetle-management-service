package com.bugcollection.beetle.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(message: String) : RuntimeException(message) {
        // Если нужно, добавьте дополнительные конструкторы для разных типов сообщений или ошибок
}