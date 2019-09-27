package mozay.backend.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

@Service
class PictureService(
    @Value("\${mozay.images-path}") val imagesPath: String = "images"
) {

    /**
     * Сохраняет картинку в ФС с UUID в качестве названия и возвращает путь
     * /images/00000000-0000-0000-00000000
     */
    fun save(img: BufferedImage): String {
        val format = "jpg"
        val id = UUID.randomUUID()
        val url = "$imagesPath/$id"

        val folder = File(imagesPath)
        if (!folder.exists()) folder.mkdir()

        val output = File(folder.absolutePath + File.separator + "$id.$format")

        ImageIO.write(img, format, output)

        return url
    }

    fun save(img: MultipartFile) = save(read(img))

    /**
     * Переводит MultipartFile в BufferedImage
     */
    fun read(file: MultipartFile): BufferedImage = ImageIO.read(file.inputStream)
}