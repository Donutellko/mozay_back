package mozay.backend.service

import mozay.backend.domain.Project
import mozay.backend.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {

    fun save(project: Project): Project {
        return projectRepository.save(project)
    }

    fun getProjects(limit: Int? = null): List<Project> {
        return projectRepository.findAll().filterIndexed { i, p -> i <= limit ?: i }
    }

}