package chucknorris

fun encode(inputString: String): String {
    val binaryList = mutableListOf(' ')
    for (char in inputString.toMutableList()) {
        val binary = Integer.toBinaryString(char.code).toInt()
        binaryList.addAll(String.format("%07d", binary).toMutableList())
    }
    var binaryString = when (binaryList[1]) {
        '1' -> "0 0"
        '0' -> "00 0"
        else -> ""
    }
    for (i in 2..binaryList.lastIndex) {
        binaryString += when {
            binaryList[i] == binaryList[i - 1] -> "0"
            binaryList[i] == '1' -> " 0 0"
            else -> " 00 0"
        }
    }
    return binaryString
}

fun decode(unaryString: String): String {
    val zeroList =  unaryString.split(" ")
    if (zeroList.size % 2 == 1) {
        return ""
    }
    var binaryString = ""
    for (i in 0 until zeroList.size / 2) {
        binaryString += when (zeroList[i * 2]) {
            "0" -> "1".repeat(zeroList[i * 2 + 1].length)
            "00" -> "0".repeat(zeroList[i * 2 + 1].length)
            else -> ""
        }
    }
    if (binaryString.length % 7 != 0){
        return ""
    }
    val result: MutableList<Char> = mutableListOf()
    for (i in 0 until binaryString.length / 7) {
        val code = binaryString.slice(i * 7..i * 7 + 6).toInt(2)
        result.add(code.toChar())

    }

    return result.joinToString("")
}


fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        val op = readln()
        when (op) {
            "encode" -> {
                println("Input string:")
                val inputString = readln()
                println("Encoded string:")
                println(encode(inputString))
            }
            "decode" -> {
                println("Input encoded string:")
                val decoded = decode(readln())
                if (decoded != "") {
                    println("Decoded string:")
                    println(decoded)
                } else {
                    println("Encoded string is not valid.")
                }
            }
            "exit" -> break
            else -> println("There is no '$op' operation")
        }
    }
    println("Bye!")
}