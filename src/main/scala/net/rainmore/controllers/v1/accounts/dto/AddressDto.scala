package net.rainmore.controllers.v1.accounts.dto

import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonCreator, JsonProperty}
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import net.rainmore.domains.common.addresses.{Address, Type}

object AddressDto {

    def apply(address: Address): AddressDto = new AddressDto(
        address.getId,
        address.getAddress1,
        address.getAddress2,
        address.getPostcode,
        address.getSuburb,
        address.getState,
        address.getCountry,
        address.getType
    )
}

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
case class AddressDto @JsonCreator()
(
    @JsonProperty id: Long,
    @JsonProperty address1: String,
    @JsonProperty address2: String,
    @JsonProperty postcode: String,
    @JsonProperty suburb: String,
    @JsonProperty state: String,
    @JsonProperty country: String,
    @JsonProperty `type`: Type
) {
    def to(domain: Address): Address = {
        domain.setAddress1(address1)
            .setAddress2(address2)
            .setPostcode(postcode)
            .setSuburb(suburb)
            .setState(state)
            .setCountry(country)
            .setType(`type`)
            .setId(id)
    }
}
