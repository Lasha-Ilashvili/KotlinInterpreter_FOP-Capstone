fun main() {
    print("Enter a number: ")
    val n = readln().toInt()

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
        println("$n is a prime number.")
    } else {
        println("$n is not a prime number.")
    }
}