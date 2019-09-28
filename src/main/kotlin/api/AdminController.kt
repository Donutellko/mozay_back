package mozay.backend.api

import mozay.backend.domain.Foundation
import mozay.backend.service.FoundationService
import mozay.backend.service.PictureService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/admin")
class AdminController(
    private val foundationService: FoundationService,
    private val pictureService: PictureService
) {

    /**
     * Создать фонд
     */
    @PostMapping("/foundation")
    fun createFoundation(@RequestBody foundation: Foundation): Foundation {
        return foundationService.save(foundation)
    }

    /**
     * Править фонды
     */
    @PutMapping("/foundation/{id}")
    fun editFoundation(
        @PathVariable("id") id: Int,
        @RequestBody foundation: Foundation
    ): Foundation {
        foundation.id = id
        return foundationService.save(foundation)
    }

    /**
     * Изменить логотип фонда
     */
    @PutMapping("/foundation/picture")
    fun changePicture(@RequestBody pictureUpdate: FoundationPictureUpdate): Map<String, Any> {
        val url = pictureService.save(pictureUpdate.picture!!)
        val foundation = foundationService.find(pictureUpdate.foundation!!)
        return if (foundation.isPresent) {
            foundationService.save(foundation.get().apply {  })
            mapOf("success" to true, "url" to url)
        } else {
            mapOf("success" to false)
        }
    }

    class FoundationPictureUpdate(
        var foundation: Int? = null,
        var picture: MultipartFile? = null
    )


}