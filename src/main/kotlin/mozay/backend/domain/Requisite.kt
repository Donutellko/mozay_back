package mozay.backend.mozay.backend.domain

import mozay.backend.domain.User
import mozay.backend.mozay.backend.mozay.backend.domain.Bank
import javax.persistence.*

@Entity
class Requisite(

    var user: User? = null,

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
