package mozay.backend

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*
import java.time.LocalDate
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Primary
import java.time.format.DateTimeFormatter.ofPattern
import java.io.IOException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import java.time.format.DateTimeFormatter


@SpringBootApplication
@EntityScan("mozay.backend.domain")
class MozayBack

private lateinit var context: ConfigurableApplicationContext

fun main(args: Array<String>) {

    context = runApplication<MozayBack>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}

@Configuration
@EnableWebMvc
class CorsConfig : WebMvcConfigurerAdapter() {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }

}
/*

@Configuration
class DateSerializationConfig {


    companion object {
        val FORMATTER: DateTimeFormatter = ofPattern("yyyy-MM-dd")
    }

    @Bean
    @Primary
    fun serializingObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDate::class.java, LocalDateSerializer())
        javaTimeModule.addDeserializer(LocalDate::class.java, LocalDateDeserializer())
        objectMapper.registerModule(javaTimeModule)
        return objectMapper
    }

    class LocalDateSerializer : JsonSerializer<LocalDate>() {
        override fun serialize(value: LocalDate?, gen: JsonGenerator?, serializers: SerializerProvider?) {
            gen!!.writeString(value?.format(FORMATTER) ?: "null")
        }
    }

    class LocalDateDeserializer : JsonDeserializer<LocalDate>() {

        @Throws(IOException::class)
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate {
            return LocalDate.parse(p.valueAsString, FORMATTER)
        }
    }
}*/
