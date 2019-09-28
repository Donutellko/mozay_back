package mozay.backend.repository

import mozay.backend.domain.Foundation
import org.springframework.data.jpa.repository.JpaRepository

interface FoundationRepository: JpaRepository<Foundation, Int> {
}