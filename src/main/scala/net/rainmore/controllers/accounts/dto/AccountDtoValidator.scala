package net.rainmore.controllers.accounts.dto

import javax.inject.Inject

import net.rainmore.domains.accounts.Account
import net.rainmore.modules.accounts.AccountService
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.validation.{Errors, ValidationUtils, Validator}

@Component("accounts.accountDtoValidator")
class AccountDtoValidator @Inject()
(
    accountService: AccountService,
    addressDtoValidator: AddressDtoValidator,
    emailDtoValidator: EmailDtoValidator
) extends Validator  {

    override def supports(clazz: Class[_]): Boolean = classOf[AccountDto] == clazz

    override def validate(target: scala.Any, errors: Errors): Unit = {
        val dto = target.asInstanceOf[AccountDto]

        Option(dto.id).foreach(id =>
            if (!accountService.exists(id)) {
                errors.rejectValue("id", "account.id.invalid", "Invalid Account Id")
            }
        )

        Option(StringUtils.trimToNull(dto.firstName)) match {
            case None => errors.rejectValue("firstName", "account.firstName.empty", "Empty First Name")
            case Some(firstName) =>
                if (firstName.length > Account.FIRST_NAME_MAX_LENGTH) {
                    errors.rejectValue("firstName", "account.firstName.maxLength", "First name length is too long")
                }
        }

        Option(StringUtils.trimToNull(dto.lastName)) match {
            case None => errors.rejectValue("lastName", "account.lastName.empty", "Empty Last Name")
            case Some(lastName) =>
                if (lastName.length > Account.LAST_NAME_MAX_LENGTH) {
                    errors.rejectValue("lastName", "account.lastName.maxLength", "Last name length is too long")
                }
        }

        for((addressDto, index) <- dto.addresses.zipWithIndex) {
            try {
                // TODO to double check path is correct or not in a collection
                errors.pushNestedPath("addresses.%s".format(index))
                ValidationUtils.invokeValidator(addressDtoValidator, addressDto, errors)
            }
            finally {
                errors.popNestedPath()
            }
        }

        for((emailDto, index) <- dto.emails.zipWithIndex) {
            try {
                // TODO to double check path is correct or not in a collection
                errors.pushNestedPath("emails.%s".format(index))
                ValidationUtils.invokeValidator(emailDtoValidator, emailDto, errors)
            }
            finally {
                errors.popNestedPath()
            }
        }
    }
}
