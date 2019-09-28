package mozay.backend.service

import mozay.backend.domain.*
import mozay.backend.domain.Transaction.TransactionType.*
import mozay.backend.repository.TransactionRepository
import java.math.BigDecimal

class TransactionService(
    private val transactionRepository: TransactionRepository
) {

    /**
     * Перечислить проекту сумму
     */
    fun pay(user: User, project: Project, sum: BigDecimal, isDirect: Boolean = false) {
        transactionRepository.save(
            Transaction(
                user = user,
                project = project,
                sum = sum,
                type = if (isDirect) DONATE else PASSIVE
            )
        )
    }

}