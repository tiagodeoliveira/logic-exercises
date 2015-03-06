class NodeCreator:
	def __init__(self, defaultList):
		self.default = defaultList
	
	def buildGraph(self):
		graphInput = raw_input('Inform the graph set ("AB1, CD2" = {city}{city}{distance}): ')

		if not graphInput:
			graphInput = self.default

		graphInput = graphInput.upper()
		rawGraphList = graphInput.split(',')
		nodes = {}
		
		# creates a matrix representing the graph structure, with the node value, linked with, and link value
		for rawGraph in rawGraphList:
			graph = rawGraph.strip()
			cityFrom = graph[0]
			cityTo = graph[1]
			distance = graph[2]
			if cityFrom in nodes:
				nodes[cityFrom].update({cityTo: distance})
			else:
				nodes[cityFrom] = {cityTo: distance}
		
		return nodes