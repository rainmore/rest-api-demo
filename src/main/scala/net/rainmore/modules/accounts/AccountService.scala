package net.rainmore.modules.accounts

import javax.inject.Inject

import net.rainmore.domains.accounts.Account
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService @Inject()
(
    accountRepository: AccountRepository
) {

    def exists(id: Long): Boolean = accountRepository.exists(id)

    def findOne(id: Long): Account = accountRepository.findOne(id)

    def findAll(pageable: Pageable): Page[Account] = accountRepository.findAll(pageable)

    @Transactional
    def save(account: Account): Account = accountRepository.save(account)

    @Transactional
    def delete(account: Account): Unit = accountRepository.delete(account)
}
