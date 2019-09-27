package mozay.backend.service

import mozay.backend.domain.Role
import mozay.backend.domain.User
import mozay.backend.repository.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun create(login: String, password: String): User {
        val user = User(login = login, password = password)
        return userRepository.save(user)
    }

    fun find(login: String): Optional<User> {
        return userRepository.findById(login)
    }

}