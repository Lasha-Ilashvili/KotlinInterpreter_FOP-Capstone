puts "Enter a number to calculate the sum of its digits:"
number = gets.chomp.to_i

sum = 0

# Calculate the sum of the digits using a loop
while number > 0
  digit = number % 10
  sum = sum + digit
  number = number / 10
end

# Print the sum of the digits
puts "The sum of the digits is: #{sum}"
