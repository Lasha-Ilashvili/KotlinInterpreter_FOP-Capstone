puts "Enter the value of N to find the Nth Fibonacci number:"
n = gets.chomp.to_i

if n < 0
  puts "Fibonacci numbers are not defined for negative indices."
else
  a = 0
  b = 1
  i = 2

  if n == 0
    fib = a
  elsif n == 1
    fib = b
  else
    while i <= n
      fib = a + b
      a = b
      b = fib
      i = i + 1
    end
  end

  puts "The #{n}th Fibonacci number is: #{fib}"
end
