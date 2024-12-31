import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number to find the largest digit: ")
    var number = scanner.nextInt()

    var largest = 0

    while (number > 0) {
        val digit = number % 10
        if (digit > largest) {
            largest = digit
        }
        number /= 10
    }

    println("The largest digit is: $largest")
}