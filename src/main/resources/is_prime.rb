puts "Enter a number:"
n = gets.chomp.to_i

is_prime = true

if n <= 1
  is_prime = false
else
  i = 2
  while i * i <= n
    if n % i == 0
      is_prime = false
      break
    end
    i = i + 1
  end
end

if is_prime
  puts "#{n} is a prime number."
else
  puts "#{n} is not a prime number."
end
