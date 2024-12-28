import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter the first number: ")
    var num1 = scanner.nextInt()

    print("Enter the second number: ")
    var num2 = scanner.nextInt()

    // Ensure both numbers are positive
    if (num1 <= 0 || num2 <= 0) {
        println("Please enter positive numbers only.")
    } else {
        // Find GCD using subtraction and loop (no built-in methods allowed)
        while (num1 != num2) {
            if (num1 > num2) {
                num1 -= num2
            } else {
                num2 -= num1
            }
        }

        // At this point, num1 and num2 are equal and represent the GCD
        println("The GCD is: " + num1)
    }
}