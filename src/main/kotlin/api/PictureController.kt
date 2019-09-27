package mozay.backend.api

import mozay.backend.service.PictureService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class PictureController(private val pictureService: PictureService) {

    /**
     * Сохранить фото в ФС и вернуть путь к нему
     */

    @PostMapping
    fun upload(file: MultipartFile): String {
        return pictureService.save(file)
    }

}