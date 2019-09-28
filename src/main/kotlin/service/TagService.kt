package mozay.backend.service

import mozay.backend.domain.Tag
import mozay.backend.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService (private val tagRepository: TagRepository) {

    fun getAll(): MutableList<Tag> = tagRepository.findAll()

}