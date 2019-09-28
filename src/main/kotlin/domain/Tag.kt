package mozay.backend.domain

import javax.persistence.*

@Entity
class Tag(
    @Id var title: String? = null,
    var category: String? = null
)
