fun main() {
    print("Enter a number to calculate the sum of its digits: ")
    var number = readln().toInt()

    var sum = 0

    while (number > 0) {
        val digit = number % 10
        sum += digit
        number /= 10
    }

    println("The sum of the digits is: $sum")
}