package chucknorris

fun main() {
    println("Input string:")
    val inputChars = readln().split("")
    for (char in inputChars) {
        print("$char ")
    }
}