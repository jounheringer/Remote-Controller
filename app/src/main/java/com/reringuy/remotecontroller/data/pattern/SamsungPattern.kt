package com.reringuy.remotecontroller.data.pattern

import com.reringuy.remotecontroller.model.IrCode

fun samsungPatternConverter(irCode: IrCode): IntArray {
    val pattern = mutableListOf<Int>()

    pattern.addAll(listOf(4500, 4500))

    for (i in 0 until irCode.bits) {
        pattern += 560

        val bit = (irCode.data.toLong() shr i) and 1

        pattern += if (bit == 1L) 1600 else 560
    }

    pattern += 560

    return pattern.toIntArray()
}