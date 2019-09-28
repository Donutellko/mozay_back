package mozay.backend.api

import mozay.backend.repository.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("stats")
class StatisticsController(
    private val projectRepository: ProjectRepository,
    private val transactionRepository: TransactionRepository
) {

    /**
     * Возвращает три самых профинансированных проекта
     */
    @GetMapping("/projects")
    fun projectsStatistics(@RequestBody request: StatisticsFilter): Any {
        val kek = transactionRepository.filter(
            login = request.login,
            begin = request.begin,
            end = request.end,
            foundation = request.foundation
        )
        return kek.map { k -> mapOf("project" to k[0], "sum" to k[1], "count" to k[2]) }
    }

    class StatisticsFilter(
        var login: String? = null,
        var begin: LocalDate? = null,
        var end: LocalDate? = null,
        var foundation: Int? = null
    )

}