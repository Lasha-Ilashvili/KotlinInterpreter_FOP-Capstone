puts "Enter a number:"
n = gets.chomp.to_i

original = n
reversed = 0

# Reverse the number using a loop
while n > 0
  digit = n % 10
  reversed = reversed * 10 + digit
  n = n / 10
end

# Check if the original number is the same as the reversed number
if original == reversed
  puts "#{original} is a palindrome."
else
  puts "#{original} is not a palindrome."
end