package mozay.backend.repository

import mozay.backend.domain.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository: JpaRepository<Project, Int> {

}