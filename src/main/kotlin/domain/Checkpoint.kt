package mozay.backend.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
class Checkpoint(

    @ManyToOne
    var project: Project? = null,

    var sum: Int? = null,

    var content: String? = null,

    var date: LocalDate = LocalDate.now()

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}