import kotlin.time.times

val diceColors = listOf("red", "green", "blue")

fun main() {
    readInput("Day02").map {
        var sum = 0
        var powerSum = 0

        it.split(":", ";").let {
            val id = it.first().replace("Game", "").trim().toIntOrNull()
            val hands = it.subList(1, it.size)

            var impossible = false
            var maxMap = mutableMapOf("blue" to 0, "red" to 0, "green" to 0)

            hands.forEach { hand ->
                val diceSumOfColor = hand.split(",").map { it.trim() }
                val colorMap = mutableMapOf<String, Int>()

                diceSumOfColor.map { colorSum ->
                    colorSum.findAnyOf(diceColors)?.let {
                        val color = it.second
                        colorSum.replace(color, "").trim().toIntOrNull()?.let {
                            colorMap.put(color, it)
                        }
                    }
                }

                colorMap.getOrDefault("blue", 0).let {
                    if (it > maxMap.getOrDefault("blue", 0)) {
                        maxMap.put("blue", it)
                    }

                    if (it > 14) impossible = true
                }
                colorMap.getOrDefault("green", 0).let {
                    if (it > maxMap.getOrDefault("green", 0)) {
                        maxMap.put("green", it)
                    }
                    if (it > 13) impossible = true
                }
                colorMap.getOrDefault("red", 0).let {
                    if (it > maxMap.getOrDefault("red", 0)) {
                        maxMap.put("red", it)
                    }
                    if (it > 12) impossible = true

                }
            }
            var power = 1
            maxMap.values.forEach {
                power *= it
            }
            powerSum += power

            if (!impossible) {
                id?.let {
                    sum += it
                }
            }
        }

        Pair(sum, powerSum)
    }.let {
        println("Sum of possible game ids = ${it.map { it.first }.sum()}")
        println("Output of powers = ${it.map { it.second }.sum()}")
    }
}