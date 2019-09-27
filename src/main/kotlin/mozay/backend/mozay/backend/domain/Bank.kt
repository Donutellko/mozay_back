package mozay.backend.mozay.backend.mozay.backend.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Bank(

    /**
     * Название банка
     */
    var name: String? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}
