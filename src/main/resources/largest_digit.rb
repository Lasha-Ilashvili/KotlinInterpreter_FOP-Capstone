puts "Enter a number to find the largest digit:"
number = gets.chomp.to_i

largest = 0

# Find the largest digit using a loop
while number > 0
  digit = number % 10
  if digit > largest
    largest = digit
  end
  number = number / 10
end

# Print the largest digit
puts "The largest digit is: #{largest}"