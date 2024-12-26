
puts "Enter the first number:"
num1 = gets.chomp.to_i
puts "Enter the second number:"
num2 = gets.chomp.to_i

gcd = num1.gcd(num2) 
puts "The GCD of #{num1} and #{num2} is: #{gcd}"
