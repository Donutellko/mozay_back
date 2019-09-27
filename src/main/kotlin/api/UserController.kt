package mozay.backend.api

import mozay.backend.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/user")
    fun info(
        @RequestParam("login") login: String
    ) = userService.find(login = login)


}