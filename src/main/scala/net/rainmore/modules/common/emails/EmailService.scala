package net.rainmore.modules.common.emails

import javax.inject.Inject

import net.rainmore.domains.common.emails.Email
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmailService @Inject()
(
    emailRepository: EmailRepository
) {

    def exists(id: Long): Boolean = emailRepository.exists(id)

    def findOne(id: Long): Email = emailRepository.findOne(id)

    @Transactional
    def save(email: Email): Email = emailRepository.save(email)

    @Transactional
    def delete(email: Email): Unit = emailRepository.delete(email)

}
