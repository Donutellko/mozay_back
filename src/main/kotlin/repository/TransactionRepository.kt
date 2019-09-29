package mozay.backend.repository

import mozay.backend.domain.Foundation
import mozay.backend.domain.Project
import mozay.backend.domain.Transaction
import org.omg.CORBA.Object
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface TransactionRepository: JpaRepository<Transaction, Int> {

    @Query("""
        select p, sum(t.sum), count(t)
        from Transaction t
            join t.user u
            join t.project p
            join p.foundation f 
        where (:login is null or :login = u.login)
            and (:beginDate is null or :beginDate <= t.date)
            and (:endDate is null or :endDate >= t.date)
            and (:foundation is null or :foundation = f.id)
        group by p
    """)
    fun filter(
        login: String? = null,
        beginDate: LocalDate? = null,
        endDate: LocalDate? = null,
        foundation: Int? = null
    ): Set<Array<Object>>

    @Query("""
        select sum(t.sum)
        from Transaction t 
        where t.project = :p 
    """)
    fun projectSum(p: Project): Int?

    @Query("""
        select count(distinct t.user)
        from Transaction t 
        where t.project = :p
    """)
    fun participants(p: Project): Int?

    fun findByProject(project: Project): Set<Transaction>

}