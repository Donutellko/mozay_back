package mozay.backend.domain

import javax.persistence.*

@Entity
class User(

    @Id
    var login: String? = null,

    var name: String? = null,

    /**
     * Пароль в открытом виде, ага
     */
    var password: String? = null,

    /**
     * Роль пользователя
     */
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER
) {

    /**
     * Список проектов, добавленных в избранное
     */
    @ManyToMany
    var favorite: Set<Project> = setOf()

    /**
     * Список проектов, на которые перечисляются бонусы
     * */
    @ManyToMany
    var passive: Set<Project> = setOf()

    /**
     * Платёжные данные
     */
    @OneToMany
    var requisites: Set<Requisite> = setOf()

}
