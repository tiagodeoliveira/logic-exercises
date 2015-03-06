class RomanNumberProcessor
	@@digitsTable = {'I' => 1, 'V' => 5, 'X' => 10, 'L' => 50, 'C' => 100, 'D' => 500, 'M' => 1000}
	
	 def initialize()
	 end
	 
	 def getIntegerValue(expression)
		intArray = []
		expression.each_char do |digit|
			intArray << @@digitsTable[digit]
		end
		
		totalValue = 0
		index = intArray.size - 1
		intArray.reverse_each {|value|
			
			previousValue = 0
			if index != intArray.size - 1
				previousValue = intArray[index + 1]
			end 
			
			if index == intArray.size - 1
				totalValue = value
			elsif (value >= previousValue)
				operation = "+"
				totalValue += value
			elsif (value < previousValue)
				operation = "-"
				totalValue -= value
			end
			
			index -= 1
		}
		
		return totalValue
	 end
end