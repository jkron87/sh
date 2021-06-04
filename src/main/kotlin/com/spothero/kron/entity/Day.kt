package com.spothero.kron.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Day(val name: String, @Id val id: Long? = null) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Day) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }
}
