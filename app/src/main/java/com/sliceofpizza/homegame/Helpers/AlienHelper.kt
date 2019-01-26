package com.betheres.krsreporting.com.sliceofpizza.homegame.Helpers

import kotlin.random.Random


class AlienHelper{
    companion object AlienHelper {
        fun getPositionInSquare(square : Square) : Coordinate{
            when(square){
                Square.A -> {
                    return Coordinate(Random.nextInt(0,Constants.width1),Random.nextInt(0,Constants.height1))
                }
                Square.B -> {
                    return Coordinate(Random.nextInt(Constants.width1,Constants.width2),Random.nextInt(0,Constants.height1))

                }
                Square.C -> {
                    return Coordinate(Random.nextInt(Constants.width2,Constants.width3),Random.nextInt(0,Constants.height1))

                }
                Square.D -> {
                    return Coordinate(Random.nextInt(0,Constants.width1),Random.nextInt(Constants.height1,Constants.height2))

                }
                Square.E -> {
                    return Coordinate(Random.nextInt(Constants.width1,Constants.width2),Random.nextInt(Constants.height1,Constants.height2))

                }
                Square.F -> {
                    return Coordinate(Random.nextInt(Constants.width2,Constants.width3),Random.nextInt(Constants.height1,Constants.height2))

                }
                Square.G -> {
                    return Coordinate(Random.nextInt(0,Constants.width1),Random.nextInt(Constants.height2,Constants.height3))

                }
                Square.H -> {
                    return Coordinate(Random.nextInt(Constants.width1,Constants.width2),Random.nextInt(Constants.height2,Constants.height3))
                }
                Square.I -> {
                    return Coordinate(Random.nextInt(Constants.width2,Constants.width3),Random.nextInt(Constants.height2,Constants.height3))
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

