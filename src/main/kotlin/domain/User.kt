package mozay.backend.domain

import mozay.backend.mozay.backend.domain.Requisite
import javax.persistence.*

@Entity
class User(

    var name: String? = null

) {

    @Id
    @GeneratedValue
    var id: Int? = null

    /**
     * Список проектов, добавленных в избранное
     */
    @ManyToMany
    var favorite: List<Project> = listOf()

    /**
     * Список проектов, на которые перечисляются бонусы
     * */
    @ManyToMany
    var passive: List<Project> = listOf()

    /**
     * Платёжные данные
     */
    @OneToMany
    var requisites: List<Requisite> = listOf()

}