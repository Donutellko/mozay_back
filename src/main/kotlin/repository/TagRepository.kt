package mozay.backend.repository

import mozay.backend.domain.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<Tag, String> {

}