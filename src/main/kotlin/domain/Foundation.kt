package mozay.backend.domain

import javax.persistence.*

/**
 * Объект фонда создаётся и изменяется только супер-администратором.
 */
@Entity
class Foundation(
    var title: String? = null,

    /**
     * HTML страницы описания фонда, редактируется в TinyEMC
     */
    var content: String? = null,

    /**
     * URL логотипа фонда
     */
    var picture: String? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}