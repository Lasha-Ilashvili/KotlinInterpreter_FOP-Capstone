
puts "Enter a number:"
n = gets.chomp.to_i

reversed = 0

# Reverse the number using a loop
while n != 0
  last_digit = n % 10
  reversed = reversed * 10 + last_digit
  n = n / 10
end

# Print the reversed number
puts "The reversed number is: #{reversed}"
