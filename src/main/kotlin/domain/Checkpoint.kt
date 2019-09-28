package mozay.backend.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import java.time.LocalDate
import javax.persistence.*

@Entity
class Checkpoint(

    @ManyToOne
    var project: Project? = null,

    var sum: Int? = null,

    var content: String? = null,

    @JsonSerialize(using = LocalDateSerializer::class)
    var date: LocalDate = LocalDate.now()

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}