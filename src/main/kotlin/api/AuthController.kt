package mozay.backend.api

import mozay.backend.domain.Role
import mozay.backend.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(
        @RequestParam("login") login: String,
        @RequestParam("password") password: String
    ): LoginResponse {
        val user = userService.create(login = login, password = password)
        return LoginResponse(true, Role.USER)
    }


    @PostMapping("/login")
    fun login(
        @RequestParam("login") login: String,
        @RequestParam("password") password: String
    ): LoginResponse {
        val user = userService.find(login = login)
        val success = user.isPresent && user.get().password == password

        return LoginResponse(success)
    }

    /**
     * Объект отражает результат запроса логина
     */
    class LoginResponse(val success: Boolean, val role: Role? = null)

}