package mozay.backend.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Foundation(
    var title: String? = null,

    /**
     * HTML of foundation description
     */
    var content: String? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}