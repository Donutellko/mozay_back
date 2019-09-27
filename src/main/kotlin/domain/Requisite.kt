package mozay.backend.domain

import mozay.backend.domain.User
import mozay.backend.domain.Bank
import javax.persistence.*

@Entity
class Requisite(

    @ManyToOne
    var bank: Bank? = null,

    /**
     * Номер карты
     */
    var number: String? = null,

    /**
     * CVV
     */
    var cvv: Int? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}
