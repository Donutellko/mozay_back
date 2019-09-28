package mozay.backend.service

import mozay.backend.domain.Checkpoint
import mozay.backend.repository.CheckpointRepository
import org.springframework.stereotype.Service

@Service
class CheckpointService(private val checkpointRepository: CheckpointRepository) {

    fun save(checkpoint: Checkpoint): Checkpoint = checkpointRepository.save(checkpoint)

}