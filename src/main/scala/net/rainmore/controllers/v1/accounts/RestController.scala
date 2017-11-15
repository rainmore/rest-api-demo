package net.rainmore.controllers.v1.accounts

import javax.inject.Inject
import javax.validation.Valid

import net.rainmore.controllers.{BaseRestController, JsonResponse}
import net.rainmore.controllers.v1.accounts.dto.{AccountDto, AccountDtoValidator}
import net.rainmore.domains.accounts.Account
import net.rainmore.modules.accounts.AccountService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation._

@org.springframework.web.bind.annotation.RestController("accounts.RestController")
@RequestMapping(Array("/api/v1/accounts"))
class RestController @Inject()
(
    accountService: AccountService,
    accountDtoValidator: AccountDtoValidator
) extends BaseRestController {

    final private val logger = LoggerFactory.getLogger(getClass)

    @InitBinder
    protected def initPackageBinder(binder: WebDataBinder) {
        binder.addValidators(accountDtoValidator)
    }

    @GetMapping
    def list(pageable: Pageable, @AuthenticationPrincipal userDetails: UserDetails): ResponseEntity[JsonResponse] = {
        if (Option(userDetails).isEmpty) {
            unauthorized
        }
        else {
            ok(accountService.findAll(pageable))
        }
    }

    @GetMapping(Array("/{id}"))
    def get(@PathVariable id: Long, @AuthenticationPrincipal userDetails: UserDetails): ResponseEntity[JsonResponse] = {
        if (Option(userDetails).isEmpty) {
            unauthorized
        }
        else if (!accountService.exists(id)) {
            notFound
        }
        else {
            ok(AccountDto(accountService.findOne(id)))
        }
    }

    @PostMapping
    def add(@Valid @RequestBody accountDto: AccountDto,
            bindingResult: BindingResult,
            @AuthenticationPrincipal userDetails: UserDetails): ResponseEntity[JsonResponse] = {
        if (Option(userDetails).isEmpty) {
            unauthorized
        }
        else if (bindingResult.hasFieldErrors) {
            unprocessableEntity(bindingResult)
        }
        else {
            val account = accountService.save(accountDto.to(new Account))

            ok(account.getId)
        }
    }

    @PutMapping(Array("/{id}"))
    def update(@PathVariable id: Long,
               @RequestBody accountDto: AccountDto,
               @AuthenticationPrincipal userDetails: UserDetails): ResponseEntity[JsonResponse] = {
        if (Option(userDetails).isEmpty) {
            unauthorized
        }
        else if (!accountService.exists(id)) {
            notFound
        }
        else {
            accountService.save(accountDto.to(accountService.findOne(id)))
            ok(None)
        }
    }

    @DeleteMapping(Array("/{id}"))
    def delete(@PathVariable id: Long, @AuthenticationPrincipal userDetails: UserDetails): ResponseEntity[JsonResponse] = {
        if (Option(userDetails).isEmpty) {
            unauthorized
        }
        else if (!accountService.exists(id)) {
            notFound
        }
        else {
            accountService.delete(accountService.findOne(id))
            ok(None)
        }
    }

}
