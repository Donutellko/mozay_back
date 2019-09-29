package mozay.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import java.time.LocalDate
import javax.persistence.*

@Entity
class Checkpoint(

    @JsonIgnore
    @ManyToOne
    var project: Project? = null,

    var sum: Int? = null,

    var title: String? = null,

//    @JsonSerialize(using = DateSerializationConfig.LocalDateSerializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    var date: LocalDate = LocalDate.now(),

    /**
     * Достигнут ли этот чекпоинт
     */
    var isChallenge: Boolean? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

}