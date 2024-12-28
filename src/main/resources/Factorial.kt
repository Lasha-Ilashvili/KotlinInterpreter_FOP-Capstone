import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter a number (N): ")
    val n = scanner.nextInt()

    if (n < 0) {
        println("Please enter a non-negative number.")
    } else {
        var fact = 1
        var i = 1

        while (i <= n) {
            fact *= i
            i++
        }

        println("The factorial of " + n + "is: " + "fact")
    }
}