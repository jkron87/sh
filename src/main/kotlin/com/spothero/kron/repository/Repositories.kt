package com.spothero.kron.repository

import com.spothero.kron.entity.Rate
import com.spothero.kron.entity.RateDAO
import org.springframework.data.repository.CrudRepository

/**
 * Tells spring to wire up a CrudRepository to back each DAO
 *
 * This could also be accomplished by annotating the DAOs with spring's
 * @Repository but the approach used here keeps the domain module very
 * simple, allowing it to depend only on javax.persistence without
 * pulling in all of spring-core or spring-data
 *
 * Allows addition of custom @Query annotations
 */
interface RateRepository : CrudRepository<Rate, Long>, RateDAO
