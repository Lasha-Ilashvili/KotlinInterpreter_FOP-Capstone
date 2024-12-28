import java.util.Scanner

fun main() {
  val scanner = Scanner(System.`in`)

  print("Enter a number to generate its multiplication table: ")
  val number = scanner.nextInt()

  var i = 1

  // Generate and print the multiplication table using a loop
  while (i <= 10) {
    val result = number * i
    println(result)
    i++
  }
}