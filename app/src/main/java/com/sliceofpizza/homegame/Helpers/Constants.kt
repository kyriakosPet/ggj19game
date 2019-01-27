package com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers

class Constants {
    companion object Constants {
        val alienLife = 8
        val alienSecondHalf = 4
        val alienDamage = 10

        val width1 = 33
        val width2 = 66
        val width3 = 100

        val height1 = 44
        val height2 = 74
        val height3 = 100


        var wasteAmount: Double = 0.0
        const val wasteMin: Double = 0.0
        const val wasteMax: Double = 100.0
        const val wasteReduction: Double = 20.0
        const val wasteFillAmount: Double = 2.0
        var hasWaste = false
        const val wasteRepeat: Long = 1250
        const val maxLapForValves = 3

        var hasBlackOut = false
        const val blackOutRepeat: Long = 5000
        const val blackOutChance = 6
        const val alienspawnRepeat: Long =10000
    }
}