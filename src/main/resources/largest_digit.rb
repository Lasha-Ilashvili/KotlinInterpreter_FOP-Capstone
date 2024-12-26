# Function to find the largest digit in a number
def largest_digit(number)
  number.to_s.chars.map(&:to_i).max
end

# Get user input
puts "Enter a number to find the largest digit:"
number = gets.chomp.to_i

# Find and print the largest digit
puts "The largest digit is: #{largest_digit(number)}"
