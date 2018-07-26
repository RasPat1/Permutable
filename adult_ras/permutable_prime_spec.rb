require './permutable_prime'
require 'ruby-prof'

class PermutablePrimeSpec
  attr_accessor :limit

  def initialize(limit = 10**4)
    @limit = limit
  end

  def call

    # RubyProf.start
    # pps_1 = PermutablePrime.new(Permuter.new).call(limit)
    # result1 = RubyProf.stop

    RubyProf.start
    pps_2 = PermutablePrime.new(FastPermuter.new).call(limit)
    result2 = RubyProf.stop

    finish(pps_2, result2)
  end

  def finish(pps, result)
    printer = RubyProf::FlatPrinter.new(result)
    printer.print(STDOUT)
    puts "Is correct: #{check_validity(pps)}"
  end

  def check_validity(pps)
    known = [
      2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97,
      113, 131, 199, 311, 337, 373, 733, 919, 991
    ]

    applicable_known = known.select { |val| val < limit }
    pps.sort == applicable_known.sort
  end
end

pps = PermutablePrimeSpec.new(10**6)
pps.call