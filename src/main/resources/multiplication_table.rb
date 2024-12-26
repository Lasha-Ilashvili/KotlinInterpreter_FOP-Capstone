# Function to generate and print the multiplication table
def multiplication_table(number)
  (1..10).each do |i|
    puts number * i
  end
end

# Get user input
puts "Enter a number to generate its multiplication table:"
number = gets.chomp.to_i

# Generate and print the multiplication table
multiplication_table(number)
