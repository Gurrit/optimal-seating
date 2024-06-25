package com.gurrit.model

data class SeatingPair(
    val person1: String,
    val person2: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SeatingPair

        return this.hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        val hash1 = person1.hashCode() * person2.hashCode()
        val hash2 = person1.hashCode() + person2.hashCode()
        return hash2 * hash1
    }
}