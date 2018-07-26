require 'prime'

class PermutablePrime
  attr_accessor :permuter

  def self.build
    new(Permuter.new)
  end

  def initialize(permuter)
    @permuter = permuter
  end

  def call(max)
    all_pps = []
    flag = true
    increment = 3

    Prime.each(max) do |prime|
      if flag && Time.now.sec % increment == 0
        puts "Trying #{prime}"
        flag = false
      elsif !flag && Time.now.sec % increment == 1
        flag = true
      end

      # skip if there is an even number a 0 or a 5
      # those numbers will be at the end in some permutation
      # if they are at the end the nuber cannot be prime (except for the trivial cases)
      next if has_bad_digit(prime) # 20x perf lol

      # Get all permuations
      permutations = permuter.call(prime)

      # Check primality
      next if permutations.any? { |permutation| !Prime.prime?(permutation.to_i) }

      puts prime
      all_pps << prime
    end

    all_pps
  end

  private

  def has_bad_digit(prime)
    prime > 10 && !prime.to_s.match(/0|5|2|4|6|8/).nil?
  end
end

# Perf improvement
# check primality as permutations are being generated
# Do a DFS with checks at the bottom nodes rather than a BFS generation
# with checks at last layer or after last layer

# Generate all numbers created by permuting the digits of a given number
class Permuter
  def initialize
  end

  def call(number)
    perms = ['']
    chars = number.to_s.split('')

    chars.size.times do |iteration|
      new_perms = []

      perms.each do |perm|
        chars.each do |char|
          next if perm.count(char) == chars.count(char)
          new_perms << perm + char
        end
      end

      perms = new_perms
    end

    perms
  end
end

puts Time.now
all_pps = PermutablePrime.build.call(10**7)
puts all_pps.join(', ')
puts Time.now
