package mozay.backend.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
class Project(
    var title: String? = null,

    @Column(length = 4000)
    var content: String? = null,

    var date: LocalDate = LocalDate.now()

) {

    @Id
    @GeneratedValue
    var id: Int? = null

    @ManyToMany
    var tags: Set<Tag> = setOf()
}