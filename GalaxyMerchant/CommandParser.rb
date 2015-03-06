require_relative "CurrencyProcessor"

class CommandParser

	@@valuesMap = Hash.new
	@@currencyProcessor = CurrencyProcessor.new
	@@metalsMap = Hash.new
	
	def initialize()
	end

	def processAssertion(commandMap)
		if commandMap.size == 3
			@@valuesMap[commandMap[0].strip] = commandMap[2].strip
		else 
			integerValue = @@currencyProcessor.getPainstakinglyValue(commandMap, @@valuesMap, true)[1]
			operatorPos = commandMap.index("is")
			metalName = commandMap[operatorPos - 1]
			metalValue = commandMap[operatorPos + 1]
			metalValue = metalValue.to_f / integerValue.to_f
			@@metalsMap[metalName] = metalValue
		end
	end

	def parseBatchFile(fileName)
		file = File.open(fileName, "r")
		file.each_line do |line|
			commandParser(line)
		end
	end

	def executeInteractivePrompt()
		puts "Enter a command (quit to finish)"
		strCommand = ""

		while strCommand != "quit"
			strCommand = gets.chomp.strip
			commandParser(strCommand)
		end 
	end

	def commandParser(strCommand)
		cleannedCommand = strCommand.delete("?!")
		mapCommand = cleannedCommand.split(" ")
		if mapCommand[0] == "how"
			proccessQuery(mapCommand)
		elsif strCommand.include? "is"
			processAssertion(mapCommand)
		elsif mapCommand[0] == "print" 
			print "Values table: ", @@valuesMap
			print "\nMetals table: ", @@metalsMap
		end
	end
	
	def proccessQuery(mapCommand) 
		begin
			metalValue = @@metalsMap.select { |key, val| mapCommand.include?(key) }
			convertedValue = nil
			if (metalValue.size > 0)
				convertedValue = @@currencyProcessor.getPainstakinglyWithMetalValue(mapCommand, @@valuesMap, metalValue.values[0])
			else
				mappedValues = mapCommand.reject! {|e| ['how', 'much', 'is', 'could', 'be'].include? e }
				convertedValue = @@currencyProcessor.getPainstakinglyValue(mapCommand, @@valuesMap, false)
			end
			
			if(convertedValue)
				print "\n", convertedValue[0], " is ", convertedValue[1]
			end
		rescue => exception
			print "\n", exception
		end
	end
end