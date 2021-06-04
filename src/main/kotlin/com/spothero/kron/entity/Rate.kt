package com.spothero.kron.entity

import com.spothero.kron.util.toBase64String
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Entity
class Rate(
        @Id
        @Column(length = 24)
        var id: String? = null,
        @ManyToMany
        var days: MutableList<Day> = mutableListOf(),
        val startTime: LocalTime,
        val endTime: LocalTime,
        val timeZone: ZoneId,
        val price: Long
) {

    var dateCreated: ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)
    var lastModified: ZonedDateTime? = dateCreated

    @PreUpdate
    fun markLastModified() {
        this.lastModified = ZonedDateTime.now(ZoneOffset.UTC)
    }

    @PrePersist
    fun ensureId() {
        if (id == null) {
            id = UUID.randomUUID().toBase64String()
        }
    }
}