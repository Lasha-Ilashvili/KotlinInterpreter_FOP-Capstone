
 def reverseNumber(n)
  reversed = 0
  while n != 0
    last_digit = n % 10          
    reversed = reversed * 10 + last_digit   
    n = n / 10                  
  end
  return reversed
end


 puts "Enter a number:"
 n = gets.chomp.to_i  
 result = reverseNumber(n)
 puts "The reversed number is: #{result}"