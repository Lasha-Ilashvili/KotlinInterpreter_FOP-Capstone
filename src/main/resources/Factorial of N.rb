puts "Enter a number (N):"
n = gets.chomp.to_i
fact = (1..n).inject(1, :*) 
puts "The factorial of #{n} is: #{fact}"