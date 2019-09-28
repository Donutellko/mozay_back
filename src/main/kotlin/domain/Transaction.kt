package mozay.backend.domain

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
class Transaction(

    /**
     * Пользователь, совершивший транзакцию
     */
    @ManyToOne
    var user: User? = null,

    /**
     * В пользу какого проекта
     */
    @ManyToOne
    var project: Project? = null,

    /**
     * Сумма пополнения в рублях
     */
    var sum: BigDecimal? = null,

    @Enumerated(EnumType.STRING)
    var type: TransactionType? = null,

    var date: LocalDate = LocalDate.now()

) {

    @Id
    @GeneratedValue
    var id: Int? = null


    enum class TransactionType {
        /** Средства, перечисленные вручную через кнопку Donate */
        DONATE,
        /** Средства, перечисленные автоматически из бонусов (кешбек, проенты на остаток) */
        PASSIVE
    }

}