fun main() {
    print("Enter a number to find the largest digit: ")
    var number = readln().toInt()

    var largest = 0

    while (number > 0) {
        val digit = number % 10
        if (digit > largest) {
            largest = digit
        }
        number /= 10
    }

    println("The largest digit is: $largest")
}