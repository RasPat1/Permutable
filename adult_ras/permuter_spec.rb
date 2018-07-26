require './permutable_prime'

class PermuterSpec
  attr_accessor :permuter

  def initialize(permuter)
    @permuter = permuter
  end

  def call(debug: false)
    test_cases = {
      123 => [123, 132, 213, 231, 321, 312],
      111 => [111],
      101 => [101, 110, 11],
    }

    test_cases.each do |k, v|
      result = permuter.call(k)
      correct = correct?(result, v)
      if !correct && debug
        puts "Expected #{v} but received #{result}"
      end

      puts "#{k}: #{correct?(result, v)}"
    end
  end

  private

  def correct?(given, expected)
    (given - expected).size == 0 && given.size == expected.size
  end

end

permuter = Permuter.new
fast_permuter = FastPermuter.new

PermuterSpec.new(permuter).call(debug: true)
PermuterSpec.new(fast_permuter).call(debug: true)