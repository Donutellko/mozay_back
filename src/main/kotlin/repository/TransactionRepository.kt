package mozay.backend.repository

import mozay.backend.domain.Foundation
import mozay.backend.domain.Project
import mozay.backend.domain.Transaction
import org.omg.CORBA.Object
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TransactionRepository: JpaRepository<Transaction, Int> {

    @Query("""
        select p, sum(t.sum), count(t)
        from Transaction t
            join t.user u
            join t.project p
            join p.foundation f 
        where (:login is null or :login = u.login)
            and (:begin is null or :begin <= t.date)
            and (:end is null or :end >= t.date)
            and (:foundation is null or :foundation = f.id)
        group by p
    """)
    fun filter(
        login: String? = null,
        begin: LocalDate? = null,
        end: LocalDate? = null,
        foundation: Int? = null
    ): Set<Array<Object>>

}