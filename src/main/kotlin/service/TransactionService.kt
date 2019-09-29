package mozay.backend.service

import mozay.backend.domain.*
import mozay.backend.domain.Transaction.TransactionType.*
import mozay.backend.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
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

    fun projectSum(project: Project): Int {
        return transactionRepository.projectSum(project) ?: 0
    }

    fun participants(project: Project): Int {
        return transactionRepository.participants(project) ?: 0
    }

}