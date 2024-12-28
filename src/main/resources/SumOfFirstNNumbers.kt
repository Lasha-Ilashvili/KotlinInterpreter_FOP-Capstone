import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number (N): ")
    val n = scanner.nextInt()

    if (n < 0) {
        println("Please enter a non-negative number.")
    } else {
        var sum = 0
        var i = 1

        while (i <= n) {
            sum += i
            i++
        }

        println("The sum of the first " + n + " numbers is: " + sum)
    }
}