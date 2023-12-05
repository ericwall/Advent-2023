fun main() {
   readInput("Day01").sumOf { line ->
        var baseLine = line
        while (true) {
            baseLine.NumberStringReplace()?.let {
                baseLine = it
            } ?: break
        }

        baseLine.getNumbers()
    }.let {
        println("Sum = $it")
   }
}

fun String.getNumbers(): Int =
    this.filter { it.digitToIntOrNull() != null }.let {
        "${it.first()}${it.last()}".toInt()
    }

fun String.NumberStringReplace(): String? =
    this.findAnyOf(stringMap.keys)?.let {
        val matchingValue = it.second

        stringMap[it.second]?.let {
            this.replace(matchingValue, it)
        }
    }

val stringNumbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val stringMap = mapOf(
    "one" to "1ne",
    "two" to "2wo",
    "three" to "3hree",
    "four" to "4our",
    "five" to "5ive",
    "six" to "6ix",
    "seven" to "7even",
    "eight" to "8ight",
    "nine" to "9ine"
)
