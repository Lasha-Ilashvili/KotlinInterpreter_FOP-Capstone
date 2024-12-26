# Function to calculate the sum of digits
def sum_of_digits(number)
  number.to_s.chars.map(&:to_i).sum
end

# Get user input
puts "Enter a number to calculate the sum of its digits:"
number = gets.chomp.to_i

# Calculate and print the sum of digits
puts "The sum of the digits is: #{sum_of_digits(number)}"
