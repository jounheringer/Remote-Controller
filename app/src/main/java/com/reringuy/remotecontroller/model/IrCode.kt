package com.reringuy.remotecontroller.model

data class IrCode(
    val frequency: Int,
    val pattern: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IrCode

        if (frequency != other.frequency) return false
        if (!pattern.contentEquals(other.pattern)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = frequency
        result = 31 * result + pattern.contentHashCode()
        return result
    }
}
