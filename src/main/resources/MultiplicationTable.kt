fun main() {
    print("Enter a number to generate its multiplication table: ")
    val number = readln().toInt()

    var i = 1

    while (i <= 10) {
        println(number * i)
        i++
    }
}