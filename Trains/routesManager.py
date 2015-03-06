import sys

class RoutesManager:
	def __init__(self, nodes):
		self.nodes = nodes
	
	# parse the nodes matrix based on given parameters
	def routeTracer(self, begin, nodes, end, path, tracker, dist):
		for insideNode in self.nodes[begin]:
			dist += int(self.nodes[begin][insideNode])
			if insideNode != end:
				# these rule should be conditional for problem 10, although I think that these is the correct implementation since some routes given on example are not actually different routes, they are just variations with unnecessary steps
				if (path.find(begin + '-') == -1 ):
					self.routeTracer(insideNode, self.nodes, end, path + '-' + insideNode, tracker, dist)
			else:
				route = path + '-' + insideNode
				tracker += [{'tracker': route, 'distance': dist}]
		return tracker		
		
	# calcule a given route between edges
	def calculateRoute(self, route):
		steps = route.split('-')
		distance = 0
		for i in range(0, len(steps) - 1):
			if steps[i] in self.nodes:
				if steps[i+1] in self.nodes[steps[i]]:
					distance += int(self.nodes[steps[i]][steps[i + 1]])
				else:
					return 'No such route'
			else:
				return 'No such route'
		return distance

	# amount of possible routes from a given source to a given destination
	def calculateAmountOfRoutes(self, begin, end, stops):
		tracker = []
		for node in self.nodes[begin]:
			tracker += self.routeTracer(node, self.nodes, end, begin + '-' + node, [], 0)

		result = ''
		amount = 0
		for track in tracker:
			founded = track['tracker']
			insideLimit = (eval(str(len(founded.split('-')) - 1) + stops))
			if insideLimit:
				amount += 1
				result += '[' + founded + ']'
		return str(amount) + ' (' +  result + ')'

	# extracts the shortest route between two points
	def calculateShortestRoute(self, begin, end):
		tracker = []
		for node in self.nodes[begin]:
			tracker += self.routeTracer(node, self.nodes, end, begin + '-' + node, [], int(self.nodes[begin][node]))
		
		smallDistance = sys.maxint
		smallRoute = ''
		for track in tracker:
			if (int(track['distance']) < smallDistance):
				smallDistance = int(track['distance'])
				smallRoute = track['tracker']
				
		return 'Small distance is: ' + str(smallDistance) + ' through [' + smallRoute + ']'
		
	# calculates the possible routes using a given distance as a criteria
	def calculateRoutesByDistance(self, begin, end, distance):
		tracker = []
		for node in self.nodes[begin]:
			tracker += self.routeTracer(node, self.nodes, end, begin + '-' + node, [], int(self.nodes[begin][node]))
		
		result = ''
		amount = 0
		for track in tracker:
			founded = track['tracker']
			insideLimit = (eval(str(track['distance']) + distance))
			if insideLimit:
				amount += 1
				result += '[' + founded + ']'
		return str(amount) + ' (' +  result + ')'
