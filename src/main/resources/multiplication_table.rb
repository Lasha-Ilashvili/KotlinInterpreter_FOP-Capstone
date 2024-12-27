puts "Enter a number to generate its multiplication table:"
number = gets.chomp.to_i

i = 1

# Generate and print the multiplication table using a loop
while i <= 10
  result = number * i
  puts result
  i = i + 1
end

