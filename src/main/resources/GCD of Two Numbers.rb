puts "Enter the first number:"
num1 = gets.chomp.to_i

puts "Enter the second number:"
num2 = gets.chomp.to_i

# Ensure both numbers are positive
if num1 <= 0 || num2 <= 0
  puts "Please enter positive numbers only."
else
  # Find GCD using subtraction and loop (no built-in methods allowed)
  while num1 != num2
    if num1 > num2
      num1 = num1 - num2
    else
      num2 = num2 - num1
    end
  end

  # At this point, num1 and num2 are equal and represent the GCD
  puts "The GCD is: #{num1}"
end
