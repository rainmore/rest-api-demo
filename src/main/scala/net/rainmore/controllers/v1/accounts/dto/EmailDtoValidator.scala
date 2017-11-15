package net.rainmore.controllers.v1.accounts.dto

import javax.inject.Inject

import net.rainmore.domains.common.emails.Email
import org.apache.commons.lang3.StringUtils
import org.apache.commons.validator.routines.EmailValidator
import net.rainmore.modules.common.emails.EmailService
import org.springframework.stereotype.Component
import org.springframework.validation.{Errors, Validator}

@Component("accounts.emailDtoValidator")
class EmailDtoValidator @Inject()
(
    emailService: EmailService
) extends Validator {

    private val emailValidator = EmailValidator.getInstance()

    override def supports(clazz: Class[_]): Boolean = classOf[EmailDto] == clazz

    override def validate(target: scala.Any, errors: Errors): Unit = {
        val dto = target.asInstanceOf[EmailDto]

        Option(dto.id).foreach(id =>
            if (!emailService.exists(id)) {
                errors.rejectValue("id", "email.id.invalid", "Invalid Email Id")
            }
        )

        Option(StringUtils.trimToNull(dto.email)) match {
            case None => errors.rejectValue("email", "email.email.empty", "Empty Email")
            case Some(email) =>
                if (email.length > Email.EMAIL_MAX_LENGTH) {
                    errors.rejectValue("email", "email.email.maxLength", "Email length is too long")
                }
                if (emailValidator.isValid(email)) {
                    errors.rejectValue("email", "email.email.invalid", "Invalid email format")
                }
        }

        if (Option(dto.`type`).isEmpty) {
            errors.rejectValue("type", "email.type.empty", "Empty Email Type")
        }
    }
}
