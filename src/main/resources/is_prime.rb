
def isPrime(n)
  return false if n <= 1

  for i in 2..Math.sqrt(n).to_i
    if n % i == 0
      return false  
    end
  end

  return true
end

 puts "Enter a number:"
 n = gets.chomp.to_i  

 if isPrime(n)
   puts "#{n} is a prime number."
 else
  puts "#{n} is not a prime number."
end
