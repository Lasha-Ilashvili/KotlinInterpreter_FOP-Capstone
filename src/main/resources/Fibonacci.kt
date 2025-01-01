fun main() {
    print("Enter the value of N to find the Nth Fibonacci number: ")
    val n = readln().toInt()

    if (n <= 0) {
        println("Please enter a positive integer.")
    } else {
        var a = 0
        var b = 1
        var fib = 0

        if (n == 1) {
            fib = a
        } else if (n == 2) {
            fib = b
        } else {
            var i = 3
            while (i <= n) {
                fib = a + b
                a = b
                b = fib
                i++
            }
        }

        println("The ${n}th Fibonacci number is: $fib")
    }
}