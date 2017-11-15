package net.rainmore.controllers

import java.util
import java.util.{Map => JMap}

import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonProperty}
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility

object JsonResponse {
    def apply(data: Serializable): JsonResponse = JsonResponse(data, new util.HashMap[String, String]())
}

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
case class JsonResponse (@JsonProperty data: Any, @JsonProperty messages: JMap[String, String])