package mozay.backend.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
class Project(
    var title: String? = null,

    /**
     * HTML страницы, редактируемый в TinyMCE
     */
    @Column(length = 4000)
    var content: String? = null,

    /**
     * Дата размещения
     */
    var date: LocalDate = LocalDate.now(),

    /**
     * Путь к изображению заставки
     */
    var image: String? = null,

    /**
     * Фонд и его реквизиты
     */
    @ManyToOne
    var foundation: Foundation? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

    @ManyToMany
    var tags: Set<Tag> = setOf()
}