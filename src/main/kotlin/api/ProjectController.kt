package mozay.backend.api

import mozay.backend.domain.*
import mozay.backend.service.*
import org.springframework.web.bind.annotation.*

@RestController
class ProjectController(
    private val projectService: ProjectService,
    private val tagService: TagService,
    private val checkpointService: CheckpointService
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
    ): List<Project> {
        return projectService.getProjects(limit)
    }

    @GetMapping("/project/{id}")
    fun getProject(@PathVariable("id") id: Int): Project? {
        return projectService.findById(id).orElse(null)
    }

    @PostMapping("/project/{id}/checkpoint")
    fun addCheckpoint(
        @PathVariable("id") id: Int,
        @RequestBody checkpoint: Checkpoint
    ): Checkpoint {
        val project = projectService.findById(id)

        checkpoint.project = project.get()
        project.get().checkpoints.add(checkpoint)

        return checkpointService.save(checkpoint)
    }

    @GetMapping("/tags")
    fun listTags(): Map<String?, List<Tag>> {
        return tagService.getAll().groupBy { t -> t.category }
    }

}