package net.rainmore.controllers.accounts.dto

import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonCreator, JsonProperty}
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import net.rainmore.domains.accounts.Account
import net.rainmore.domains.common.addresses.Address
import net.rainmore.domains.common.emails.Email

import scala.collection.JavaConverters._

object AccountDto {

    def apply(account: Account): AccountDto = AccountDto(
        account.getId,
        account.getFirstName,
        account.getLastName,
        account.getAddresses.asScala.map(AddressDto(_)),
        account.getEmails.asScala.map(EmailDto(_))
    )
}

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
case class AccountDto @JsonCreator()
(
    @JsonProperty id: Long,
    @JsonProperty firstName: String,
    @JsonProperty lastName: String,
    @JsonProperty addresses: Seq[AddressDto],
    @JsonProperty emails: Seq[EmailDto]
) {
    def to(domain: Account): Account = {
        domain.setFirstName(firstName)
            .setLastName(lastName)
            .setAddresses(addresses.map(_.to(new Address())).asJava)
            .setEmails(emails.map(_.to(new Email())).asJava)
            .setId(id)

    }
}
