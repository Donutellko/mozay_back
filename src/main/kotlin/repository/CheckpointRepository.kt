package mozay.backend.repository

import mozay.backend.domain.Checkpoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CheckpointRepository: JpaRepository<Checkpoint, Int> {

}