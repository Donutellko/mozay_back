package mozay.backend

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*

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