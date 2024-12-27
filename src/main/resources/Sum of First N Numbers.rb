puts "Enter a number (N):"
n = gets.chomp.to_i

if n < 0
  puts "Please enter a non-negative number."
else
  sum = 0
  i = 1

  # Calculate the sum of the first N numbers using a loop
  while i <= n
    sum = sum + i
    i = i + 1
  end

  puts "The sum of the first #{n} numbers is: #{sum}"
end

