package mozay.backend.repository

import mozay.backend.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProjectRepository: JpaRepository<Project, Int> {

}