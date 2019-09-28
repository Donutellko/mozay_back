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
        @RequestBody authRequest: AuthRequest
    ): AuthResponse {
        if (authRequest.isIncorrect() || userService.find(login = authRequest.login!!).isPresent) {
            return AuthResponse(false)
        }

        val user = userService.create(login = authRequest.login!!, password = authRequest.password!!)
        return AuthResponse(true, Role.USER, user.name)
    }


    @PostMapping("/login")
    fun login(
        @RequestBody authRequest: AuthRequest
    ): AuthResponse {

        if (authRequest.isIncorrect()) {
            return AuthResponse(false)
        }

        val user = userService.find(login = authRequest.login!!)

        return if (user.isPresent && user.get().password == authRequest.password) {
            AuthResponse(true, user.get().role, user.get().name)
        } else {
            AuthResponse(false)
        }
    }


    class AuthRequest(var login: String? = null, var password: String? = null) {
        fun isIncorrect() = login.isNullOrBlank() || password.isNullOrEmpty()
    }

    /**
     * Объект отражает результат запроса логина
     */
    class AuthResponse(val success: Boolean, val role: Role = Role.ANON, val name: String? = null)

}