import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number: ")
    var n = scanner.nextInt()

    var reversed = 0

    while (n != 0) {
        val lastDigit = n % 10
        reversed = reversed * 10 + lastDigit
        n /= 10
    }

    println("The reversed number is: $reversed")
}