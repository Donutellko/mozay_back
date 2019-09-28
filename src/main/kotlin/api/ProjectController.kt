package mozay.backend.api

import mozay.backend.domain.Project
import mozay.backend.service.ProjectService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/project")
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping("/")
    fun create(project: Project): Project {
        return projectService.save(project)
    }

    @PutMapping("/")
    fun edit(project: Project): Project {
        return projectService.save(project)
    }

    @GetMapping("/")
    fun list(limit: Int? = null): List<Project> {
        return projectService.getProjects(limit)
    }

}