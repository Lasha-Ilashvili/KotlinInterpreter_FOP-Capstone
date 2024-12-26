# Function to compute Nth Fibonacci number iteratively
def fibonacci(n)
  return n if n <= 1

  a, b = 0, 1
  (2..n).each do
    a, b = b, a + b
  end
  b
end

# Get user input
puts "Enter the value of N to find the Nth Fibonacci number:"
n = gets.chomp.to_i

if n < 0
  puts "Fibonacci numbers are not defined for negative indices."
else
  puts "The #{n}th Fibonacci number is: #{fibonacci(n)}"
end