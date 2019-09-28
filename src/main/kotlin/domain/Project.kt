package mozay.backend.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
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
    @JsonSerialize(using = LocalDateSerializer::class)
    var beginDate: LocalDate = LocalDate.now(),

    /**
     * Дата окончания сбора
     */
    @JsonSerialize(using = LocalDateSerializer::class)
    var endDate: LocalDate? = null,

    /**
     * Путь к изображению заставки
     */
    var image: String? = null,

    /**
     * Фонд и его реквизиты
     */
    @ManyToOne
    var foundation: Foundation? = null,

    /**
     * Статус проекта: на модерации, ...
     */
    @Enumerated(EnumType.STRING)
    var status: ProjectStatus? = ProjectStatus.DRAFT,

    /**
     * Цель по сбору средств
     */
    var goal: Int? = null,

    /**
     * Динамическая цель
     */
    var isDynamicGoal: Boolean = false

) {

    @Id
    @GeneratedValue
    var id: Int? = null

    @ManyToMany
    var tags: MutableSet<Tag> = mutableSetOf()

    @OneToMany(mappedBy = "project")
    var checkpoints: MutableSet<Checkpoint> = mutableSetOf()

    enum class ProjectStatus {
        DRAFT, ONLINE, ARCHIVE
    }

}