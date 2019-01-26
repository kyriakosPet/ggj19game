package com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers

import java.util.*

class AlienHelper{
    companion object AlienHelper {
        fun getPositionInSquare(square : Square) : Coordinate{
            when(square){
                Square.A -> {
                    return Coordinate((0..Constants.width1).random(),(0..44).random())
                }
                Square.B -> {
                    return Coordinate((Constants.width1..Constants.width2).random(),(0..44).random())

                }
                Square.C -> {
                    return Coordinate((Constants.width2..Constants.width3).random(),(0..44).random())

                }
                Square.D -> {
                    return Coordinate((0..Constants.width1).random(),(45..74).random())

                }
                Square.E -> {
                    return Coordinate((Constants.width1..Constants.width2).random(),(45..74).random())

                }
                Square.F -> {
                    return Coordinate((Constants.width2..Constants.width3).random(),(45..74).random())

                }
                Square.G -> {
                    return Coordinate((0..Constants.width1).random(),(75..99).random())

                }
                Square.H -> {
                    return Coordinate((Constants.width1..Constants.width2).random(),(75..99).random())
                }
                Square.I -> {
                    return Coordinate((Constants.width2..Constants.width3).random(),(75..99).random())
                }
            }
        }
        fun getAliensMovement() : ArrayList<Coordinate>{
            var array = ArrayList<Coordinate>()
            val standard = (0..Constants.alienLife).random()
            var standard2 = (Constants.alienSecondHalf..Constants.alienLife).random()
            for (i in (0..Constants.alienLife)){
                array.add(getPositionInSquare(getRandomSquare()))
            }
            array[standard] = getPositionInSquare(Square.E)
            array[standard2] = getPositionInSquare(Square.E)
            return array
        }
        fun getRandomSquare(): Square {
            val rand = (0..Constants.alienLife).random()
            when(rand){
                0 -> { return Square.A
                }
                1 -> { return Square.B
                }
                2 -> { return Square.C
                }
                3 -> { return Square.D
                }
                4 -> { return Square.E
                }
                5 -> { return Square.F
                }
                6 -> { return Square.G
                }
                7 -> { return Square.H
                }
                8 -> { return Square.I
                }
            }
            return Square.A
        }

    }
}

enum class Square {
    A,B,C,D,E,F,G,H,I;


}
class Coordinate{
    val x = 0
    val y = 0

    constructor(x : Int,y: Int) {
        println("Constructor")
    }
}

