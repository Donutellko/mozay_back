package mozay.backend.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import mozay.backend.domain.*
import mozay.backend.service.*
import org.springframework.web.bind.annotation.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.springframework.boot.convert.ApplicationConversionService.configure
import com.fasterxml.jackson.datatype.jsr310.JSR310Module
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import mozay.backend.repository.TransactionRepository
import org.springframework.boot.convert.ApplicationConversionService.configure
import mozay.backend.domain.Transaction.TransactionType.*
import java.time.LocalDate


@RestController
class ProjectController(
    private val projectService: ProjectService,
    private val tagService: TagService,
    private val checkpointService: CheckpointService,
    private val userService: UserService,
    private val transactionService: TransactionService,
    private val transactionRepository: TransactionRepository
) {

    @PostMapping("/project")
    fun addProject(@RequestBody project: Project): Project {
        return projectService.save(project)
    }

    @PutMapping("/project/{id}")
    fun editProject(
        @PathVariable("id") id: Int,
        @RequestBody project: Project
    ): Project {
        project.id = id
        return projectService.save(project)
    }

    @GetMapping("/projects")
    fun listProjects(
        @RequestParam("limit") limit: Int? = null
    ): List<Map<String, Any?>> {
        val projects = projectService.getProjects(limit)
        return projects.map { p -> getProject(p.id!!) }
    }

    @GetMapping("/project/{id}")
    fun getProject(@PathVariable("id") id: Int): Map<String, Any?> {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JSR310Module())
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        val project: Project = projectService.findById(id).orElse(null)
        val current = transactionService.projectSum(project)  // собрано
        val participants = transactionService.participants(project)

        project.checkpoints.forEach { c -> c.isChallenge = c.sum!! > current }

        val projectAsMap: MutableMap<String, Any?> = objectMapper.convertValue(project, object :
            TypeReference<MutableMap<String, Any?>>() {})
        projectAsMap.putAll(
            mapOf<String, Any?>(
                "goal" to project.nextGoal(current),
                "participants" to participants,
                "current" to current
            )
        )

        return projectAsMap
    }

    @PostMapping("/project/{id}/checkpoint")
    fun addCheckpoint(
        @PathVariable("id") id: Int,
        @RequestBody checkpoint: Checkpoint
    ): Checkpoint {
        val project = projectService.findById(id).get()

        checkpoint.project = project
        project.checkpoints.add(checkpoint)

        return checkpointService.save(checkpoint)
    }

    @GetMapping("/project/{id}/updates")
    fun getProjectUpdates(
        @PathVariable("id") id: Int
    ): MutableList<Update> {
        val project = projectService.findById(id).get()

        val tmp = transactionRepository.findByProject(project)

        val money: List<Update> = tmp.sortedBy { t -> t.date }.map { t ->
            Update(
                t.date,
                if (t.type == DONATE)
                    "Пожертвовано ${t.sum}Р"
                else
                    "Добавлено ${t.sum} в виде кешбека с покупки.",
                t.sum!!.toDouble()
            )
        }
        val checkpoints = project.checkpoints.sortedBy { c -> c.sum }
        val result = mutableListOf<Update>()
        var i = 0
        var sum = 0.0
        for (m in money) {
            result.add(m)
            sum += m.sum!!
            if (i < checkpoints.size && checkpoints[i].sum!! <= sum) {
                result.add(
                    Update(
                        m.date,
                        "Достигнут чекпоинт '${checkpoints[i].title}'! Собрано: ${"%0.2d"} рублей."
                    )
                )
                i++
            }
        }
        return result
    }

    @GetMapping("/tags")
    fun listTags(): Map<String?, List<Tag>> {
        return tagService.getAll().groupBy { t -> t.category }
    }

    class Update(
        @JsonSerialize(using = LocalDateSerializer::class) val date: LocalDate,
        val text: String,
        var sum: Double? = null
    )
}