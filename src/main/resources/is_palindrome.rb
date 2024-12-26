def isPalindrome(n)
  return n.to_s == n.to_s.reverse
end

puts "Enter a number:"
n = gets.chomp.to_i  


if isPalindrome(n)
  puts "#{n} is a palindrome."
else
  puts "#{n} is not a palindrome."
end