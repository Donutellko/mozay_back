package mozay.backend.api

import mozay.backend.domain.*
import mozay.backend.service.*
import org.springframework.web.bind.annotation.*

@RestController
class ProjectController(
    private val projectService: ProjectService,
    private val tagService: TagService
) {

    @PostMapping("/project")
    fun addProject(@RequestBody project: Project): Project {
        return projectService.save(project)
    }

    @PutMapping("/project")
    fun editProject(
        @RequestParam("id") id: Int,
        @RequestBody project: Project
    ): Project {
        project.id = id
        return projectService.save(project)
    }

    @GetMapping("/projects")
    fun listProjects(
        @RequestParam("limit") limit: Int? = null
    ): List<Project> {
        return projectService.getProjects(limit)
    }

    @GetMapping("/project")
    fun getProject(id: Int): Project? {
        return projectService.findById(id).orElse(null)
    }

    @GetMapping("/tags")
    fun listTags(): Map<String?, List<Tag>> {
        return tagService.getAll().groupBy { t -> t.category }
    }

}