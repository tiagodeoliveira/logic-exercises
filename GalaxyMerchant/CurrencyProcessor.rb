require_relative "RomanNumberProcessor"

class CurrencyProcessor
	@@romanProcessor = RomanNumberProcessor.new

	def getPainstakinglyValue(command, valuesMap, ignoreErrors)
		valueExpression = ""
		romanValue = ""
		command.each do |value|
			romanDigit = valuesMap[value]
			if romanDigit
				valueExpression += value + " "
				romanValue << romanDigit
			elsif !ignoreErrors
				raise "What you're talking about?"
			end
		end
		return [valueExpression, @@romanProcessor.getIntegerValue(romanValue)]
	end
	
	def getPainstakinglyWithMetalValue(mapCommand, valuesMap, metalValue) 
		painstakinglyReturn = getPainstakinglyValue(mapCommand, valuesMap, true)
		expressionValue = painstakinglyReturn[1] * metalValue
		return [painstakinglyReturn[0], expressionValue]
	end
end