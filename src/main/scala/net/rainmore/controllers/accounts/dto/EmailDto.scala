package net.rainmore.controllers.accounts.dto

import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonCreator, JsonProperty}
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import net.rainmore.domains.common.emails.{Email, Type}

object EmailDto {

    def apply(email: Email): EmailDto = new EmailDto(email.getId, email.getType, email.getEmail)
}

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
case class EmailDto @JsonCreator()
(
    @JsonProperty id: Long,
    @JsonProperty `type`: Type,
    @JsonProperty email: String
) {
    def to(domain: Email): Email = {
        domain.setEmail(email)
            .setType(`type`)
            .setId(id)
    }
}
