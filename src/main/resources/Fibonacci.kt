import java.util.Scanner

fun main() {
  val scanner = Scanner(System.`in`)
  print("Enter the value of N to find the Nth Fibonacci number: ")
  val n = scanner.nextInt()

  if (n < 0) {
    println("Fibonacci numbers are not defined for negative indices.")
  } else {
    var a = 0
    var b = 1
    var fib = 0

    if (n == 0) {
      fib = a
    } else if (n == 1) {
      fib = b
    } else {
      var i = 2
      while (i <= n) {
        fib = a + b
        a = b
        b = fib
        i++
      }
    }

    println("The " + n + "th Fibonacci number is: " + fib)
  }
}