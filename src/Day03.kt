fun main() {
    readInput("Day03_test").let { input ->
        val arrayRow = arrayListOf<ArrayList<Char>>()
        var sum = 0

        input.map {
            val row = arrayListOf<Char>()
            it.forEach {
                row.add(it)
            }
            row
        }.map {
            arrayRow.add(it)
        }

        arrayRow.forEach {
            println(it)
        }
       // println(arrayRow)

        arrayRow.forEachIndexed { index, chars ->
            var charBuffer: StringBuilder = StringBuilder()
            var pointMap: MutableList<Pair<Int, Int>> = mutableListOf()
            chars.forEachIndexed { position, character ->

                if (character.isDigit()) {
                    charBuffer.append(character)
                    pointMap.add(Pair(index, position))
                } else {
                    if (charBuffer.isNotEmpty()) {
                        println(charBuffer)
                        val formattedMap = pointMap.map { "[${it.first},${it.second}]" }.joinToString(",")
                        val currentValue = charBuffer.toString().toIntOrNull()

                        // brute force
                        var isPart = false
                        pointMap.forEach {
                            val x = it.first
                            val y = it.second

                            val topLeft = Pair(x - 1, y - 1)
                            val centerLeft = Pair(x - 1, y)
                            val bottomLeft = Pair(x - 1, y + 1)

                            val topCenter = Pair(x, y - 1)
                            val bottomCenter = Pair(x, y + 1)

                            val topRight = Pair(x + 1, y - 1)
                            val centerRight = Pair(x + 1, y)
                            val bottomRight = Pair(x + 1, y + 1)

                            val pointsToCheck = listOf(
                                topLeft,
                                centerLeft,
                                bottomLeft,
                                topCenter,
                                bottomCenter,
                                topRight,
                                centerRight,
                                bottomRight
                            )

                            println("point [$x,$y] checking: ${pointsToCheck.map { "[${it.first},${it.second}]" }}")

                            pointsToCheck.forEach {
                                val xPointCheck = it.first
                                val yPointCheck = it.second
                                try {
                                    val charToCheck = arrayRow[xPointCheck][yPointCheck]
                                    if (!charToCheck.isDigit() && !charToCheck.equals('.')) {
                                        println("point [${xPointCheck},${yPointCheck}] == $charToCheck")
                                        isPart = true
                                    }
                                } catch (e: Exception) {
                                }
                            }


                        }

                        println("Is it a part: $currentValue $isPart")
                        if (isPart) {
                            currentValue?.let {
                                sum += it
                            }
                        }

                        charBuffer = StringBuilder()
                        pointMap = mutableListOf()
                    }
                }
            }

        }

        println("Sum = $sum")
    }
}