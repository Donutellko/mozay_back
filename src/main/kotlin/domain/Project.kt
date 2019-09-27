package mozay.backend.domain

import javax.persistence.*

@Entity
class Project(
    var title: String? = null,

    @Column(length = 4000)
    var content: String? = null
) {

    @Id
    @GeneratedValue
    var id: Int? = null

    @ManyToMany
    var tags: Set<Tag> = setOf()
}