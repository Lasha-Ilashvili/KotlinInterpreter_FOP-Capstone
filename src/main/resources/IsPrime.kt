import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number: ")
    val n = scanner.nextInt()

    var isPrime = true

    if (n <= 1) {
        isPrime = false
    } else {
        var i = 2
        while (i * i <= n) {
            if (n % i == 0) {
                isPrime = false
                break
            }
            i++
        }
    }

    if (isPrime) {
        println("Is a prime number.")
    } else {
        println("Is not a prime number.")
    }
}