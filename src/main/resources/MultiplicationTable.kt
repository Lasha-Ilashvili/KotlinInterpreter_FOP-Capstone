import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number to generate its multiplication table: ")
    val number = scanner.nextInt()

    var i = 1

    while (i <= 10) {
        val result = number * i
        println(result)
        i += 1
    }
}