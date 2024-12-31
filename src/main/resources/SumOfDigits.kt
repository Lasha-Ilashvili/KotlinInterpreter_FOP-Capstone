import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number to calculate the sum of its digits: ")
    var number = scanner.nextInt()

    var sum = 0

    while (number > 0) {
        val digit = number % 10
        sum += digit
        number /= 10
    }

    println("The sum of the digits is: $sum")
}