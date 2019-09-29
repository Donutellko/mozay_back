package mozay.backend.service

import mozay.backend.domain.Project
import mozay.backend.repository.ProjectRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val checkpointService: CheckpointService,
    private val foundationService: FoundationService
) {

    fun save(project: Project): Project {
        for (c in project.checkpoints)
            checkpointService.save(c)

        if (project.foundation != null)
            foundationService.save(project.foundation!!)

        return projectRepository.save(project)
    }

    fun getProjects(limit: Int? = null): List<Project> {
        return projectRepository.findAll().filterIndexed { i, p -> i <= limit ?: i }
    }

    fun findById(id: Int): Optional<Project> = projectRepository.findById(id)

}