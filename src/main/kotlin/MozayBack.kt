package mozay.backend

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
@EntityScan("domain")
class MozayBack

private lateinit var context: ConfigurableApplicationContext

fun main(args: Array<String>) {

    context = runApplication<MozayBack>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}


