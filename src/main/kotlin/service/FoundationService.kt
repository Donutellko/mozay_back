package mozay.backend.service

import mozay.backend.domain.Foundation
import mozay.backend.repository.FoundationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FoundationService(private val foundationRepository: FoundationRepository) {

    fun save(foundation: Foundation): Foundation = foundationRepository.save(foundation)

    fun find(foundation: Int): Optional<Foundation> = foundationRepository.findById(foundation)

}