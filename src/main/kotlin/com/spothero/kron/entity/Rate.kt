package com.spothero.kron.entity

import com.spothero.kron.util.toBase64String
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Entity
class Rate(
        @Id
        @Column(length = 24)
        var id: String? = null,
        val rateGroup: String,
        @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
        val startTime: Instant,
        @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
        val endTime: Instant,
        val timeZone: ZoneId,
        var price: Long
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