puts "Enter a number (N):"
n = gets.chomp.to_i

if n < 0
  puts "Please enter a non-negative number."
else
  fact = 1
  i = 1

  while i <= n
    fact = fact * i
    i = i + 1
  end

  puts "The factorial of #{n} is: #{fact}"
end