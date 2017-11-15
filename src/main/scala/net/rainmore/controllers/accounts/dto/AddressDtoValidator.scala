package net.rainmore.controllers.accounts.dto

import javax.inject.Inject

import net.rainmore.domains.common.addresses.Address
import net.rainmore.modules.common.addresses.AddressService
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.validation.{Errors, Validator}

@Component("accounts.addressDtoValidator")
class AddressDtoValidator @Inject()
(
    addressService: AddressService
) extends Validator {

    override def supports(clazz: Class[_]): Boolean = classOf[AddressDto] == clazz

    override def validate(target: scala.Any, errors: Errors): Unit = {
        val dto = target.asInstanceOf[AddressDto]

        Option(dto.id).foreach(id =>
            if (!addressService.exists(id)) {
                errors.rejectValue("id", "address.id.invalid", "Invalid Address Id")
            }
        )

        Option(StringUtils.trimToNull(dto.address1)) match {
            case None => errors.rejectValue("address1", "address.address1.empty", "Empty Address1")
            case Some(address1) =>
                if (address1.length > Address.ADDRESS_MAX_LENGTH) {
                    errors.rejectValue("address1", "address.address1.maxLength", "Address1 length is too long")
                }
        }

        Option(StringUtils.trimToNull(dto.address2)) foreach { address2 =>
            if (address2.length > Address.ADDRESS_MAX_LENGTH) {
                errors.rejectValue("address2", "address.address2.maxLength", "Address2 length is too long")
            }
        }

        Option(StringUtils.trimToNull(dto.suburb)) foreach { suburb =>
            if (suburb.length > Address.SUBURB_MAX_LENGTH) {
                errors.rejectValue("suburb", "address.suburb.maxLength", "Suburb length is too long")
            }
        }

        Option(StringUtils.trimToNull(dto.state)) foreach { state =>
            if (state.length > Address.STATE_MAX_LENGTH) {
                errors.rejectValue("state", "address.state.maxLength", "State length is too long")
            }
        }

        Option(StringUtils.trimToNull(dto.country)) foreach { country =>
            if (country.length > Address.COUNTRY_MAX_LENGTH) {
                errors.rejectValue("country", "address.country.maxLength", "Country length is too long")
            }
        }

        if (Option(dto.`type`).isEmpty) {
            errors.rejectValue("type", "address.type.empty", "Empty Address Type")
        }
    }
}
