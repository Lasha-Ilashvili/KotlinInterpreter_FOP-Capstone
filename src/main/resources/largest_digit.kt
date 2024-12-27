import java.util.Scanner

fun main() {
  val scanner = Scanner(System.`in`)

  print("Enter a number to find the largest digit: ")
  var number = scanner.nextInt()

  var largest = 0

  // Find the largest digit using a loop
  while (number > 0) {
    val digit = number % 10
    if (digit > largest) {
      largest = digit
    }
    number /= 10
  }

  // Print the largest digit
  println("The largest digit is: " + largest)
}