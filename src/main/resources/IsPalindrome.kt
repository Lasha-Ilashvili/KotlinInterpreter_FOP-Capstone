import java.util.Scanner

fun main() {
  val scanner = Scanner(System.`in`)

  print("Enter a number: ")
  var n = scanner.nextInt()

  val original = n
  var reversed = 0

  // Reverse the number using a loop
  while (n > 0) {
    val digit = n % 10
    reversed = reversed * 10 + digit
    n /= 10
  }

  // Check if the original number is the same as the reversed number
  if (original == reversed) {
    println("Is a palindrome.")
  } else {
    println("Is not a palindrome.")
  }
}