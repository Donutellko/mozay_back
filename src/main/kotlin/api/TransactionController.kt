package mozay.backend.api

import mozay.backend.service.ProjectService
import mozay.backend.service.TransactionService
import mozay.backend.service.UserService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.math.BigDecimal

class TransactionController(
    private val transactionService: TransactionService,
    private val projectService: ProjectService,
    private val userService: UserService
) {

    /**
     * Непрямой перевод средств кешбеком
     */
    @PostMapping("/simulate-payment")
    fun payment(
        @RequestBody payment: Payment
    ) {
        val cacheback = BigDecimal(3).divide(BigDecimal(100)) // размер кешбека
        val user = userService.find(payment.login!!).get()
        val projectsCount = BigDecimal(user.passive.size)

        val part = BigDecimal(payment.sum).multiply(cacheback).divide(projectsCount)

        for (project in user.passive) {
            transactionService.pay(user = user, project = project, sum = part, isDirect = false)
        }
    }

    /**
     * Прямой перевод на проект
     */
    @PostMapping("/project/{id}/donate")
    fun donate(
        @PathVariable("id") id: Int,
        @RequestBody payment: Payment
    ) {
        val user = userService.find(payment.login!!).get()
        val project = projectService.findById(id).get()
        val sum = BigDecimal(payment.sum)
        transactionService.pay(user = user, project = project, sum = sum, isDirect = true)
    }

    class Payment(
        var login: String? = null,
        var sum: Int = 0,
        var direct: Boolean = true
    )

}