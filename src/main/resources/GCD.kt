fun main() {
    print("Enter the first number: ")
    var num1 = readln().toInt()

    print("Enter the second number: ")
    var num2 = readln().toInt()

    if (num1 <= 0 || num2 <= 0) {
        println("Please enter positive numbers only.")
    } else {

        while (num1 != num2) {
            if (num1 > num2) {
                num1 -= num2
            } else {
                num2 -= num1
            }
        }

        println("The GCD is: $num1")
    }
}