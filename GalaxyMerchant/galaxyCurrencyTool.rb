load "CommandParser.rb"

parser = CommandParser.new

if ARGV.size > 0
	parser.parseBatchFile(ARGV[0])
else 
	parser.executeInteractivePrompt()
end
