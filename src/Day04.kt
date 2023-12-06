import kotlin.math.pow

fun main() {
    readInput("Day04").let { document ->

        var sum = 0

        val map = mutableMapOf<Int, Int>()
        document.map {
            val split = it.split(":")
            val cardNumber = split.first().split("Card")[1].trim()
            map.put(cardNumber.toInt(), 1)
        }


        println(map)

        val docs = document.map {
            val split = it.split(":")
            val cardNumber = split.first().split("Card")[1].trim()

            split[1].split("|").map {
                it.trim().split("  ", " ").map { it.trim() }
            }.let {
                val yourNumber = it[0]
                val winningNumbers = it[1]

                var iter = 0
                yourNumber.forEach {
                    if (winningNumbers.contains(it)) iter++
                }
                var result = 2.0.pow(iter - 1)
                if (result >= 1) {
                    sum += result.toInt()
                }

                val multiplier = map[cardNumber.toInt()]

                for (j in (cardNumber.toInt() + 1)..(cardNumber.toInt()) + iter) {
                    //println("cardnumber $i ")
                    try {
                        println("cardnumber $j ${map[j]}")

                        map[j] = map[j]!! + (multiplier!!)

                        println("cardnumber $j ${map[j]}")

                    } catch (e: Exception) {
                    }
                }



                println("Card number $cardNumber = $result")
            }
        }

        println(map.map { it.value }.sum())
        println(sum)

    }.let {
        println("Sum = $it")
    }
}