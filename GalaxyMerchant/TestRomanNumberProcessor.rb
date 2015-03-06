require_relative "RomanNumberProcessor"
require "test/unit"
 
class TestRomanNumberProcessor < Test::Unit::TestCase

	def setup
		@processor = RomanNumberProcessor.new
	end

	@@referenceValues = {
		"I" => 1,
		"III" => 3,
		"IV" => 4,
		"VIII" => 8,
		"CCXXXIV" => 234,
		"MCMIII" => 1903,
		"MCMXLIV" => 1944,
		"MMMMDCLXXII" => 4672,
		"MMMMCDLXXII" => 4472,
		"MMMMCMXCIX" => 4999,
	}
	
	def test_getInteger
		@@referenceValues.each {|romanValue, integerValue|
			assert_equal(integerValue, @processor.getIntegerValue(romanValue))
		}
	end
 
end