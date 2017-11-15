package net.rainmore.controllers

import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.validation.BindingResult

import scala.collection.JavaConverters._

abstract class BaseRestController {

    protected def respond[T <: Any](httpStatus: HttpStatus, data: T, messages: Map[String, String] = Map()): ResponseEntity[JsonResponse] = {
        new ResponseEntity[JsonResponse](JsonResponse(data, messages.asJava), httpStatus)
    }

    protected def ok[T <: Any](data: T): ResponseEntity[JsonResponse] = {
        respond(HttpStatus.OK, data)
    }

    protected def unauthorized: ResponseEntity[JsonResponse] = {
        respond(HttpStatus.UNAUTHORIZED, None)
    }

    protected def notFound: ResponseEntity[JsonResponse] = {
        respond(HttpStatus.NOT_FOUND, None)
    }

    protected def badRequest: ResponseEntity[JsonResponse] = {
        respond(HttpStatus.BAD_REQUEST, None)
    }

    protected def forbidden: ResponseEntity[JsonResponse] = {
        respond(HttpStatus.FORBIDDEN, None)
    }

    protected def unprocessableEntity(result: BindingResult): ResponseEntity[JsonResponse] = {
        val errors = result.getFieldErrors.asScala.map(error => error.getField -> error.getDefaultMessage)
        respond(HttpStatus.UNPROCESSABLE_ENTITY, None, errors.toMap)
    }

}
