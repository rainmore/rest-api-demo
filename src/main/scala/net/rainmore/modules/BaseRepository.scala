package net.rainmore.modules

import net.rainmore.domains.Model
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
trait BaseRepository[T <: Model, ID <: java.io.Serializable]
    extends JpaRepository[T, ID]
        with QueryDslPredicateExecutor[T]
