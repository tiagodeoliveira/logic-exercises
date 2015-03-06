import routesManager 
import nodeCreator

# Create the graph nodes based on informed matrix, use the default value if none specified
nodeCreator = nodeCreator.NodeCreator('AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7')
nodes = nodeCreator.buildGraph()

# Routes processor
routesManager = routesManager.RoutesManager(nodes)

print '1. The distance of the route A-B-C: ', routesManager.calculateRoute("A-B-C")
print '2. The distance of the route A-D: ', routesManager.calculateRoute('A-D')
print '3. The distance of the route A-D-C: ', routesManager.calculateRoute('A-D-C')
print '4. The distance of the route A-E-B-C-D: ', routesManager.calculateRoute('A-E-B-C-D')
print '5. The distance of the route A-E-D: ', routesManager.calculateRoute('A-E-D')
print '6. The number of trips starting at C and ending at C with a maximum of 3 stops: ', routesManager.calculateAmountOfRoutes('C', 'C', '<= 3')
print '7. The number of trips starting at A and ending at C with exactly 4 stops: ', routesManager.calculateAmountOfRoutes('A', 'C', '== 4'), ' (I believe that given answer for the question on email is wrong, and these is the true value)'
print '8. The length of the shortest route (in terms of distance to travel) from A to C: ', routesManager.calculateShortestRoute('A', 'C')
print '9. The length of the shortest route (in terms of distance to travel) from B to B: ', routesManager.calculateShortestRoute('B', 'B')
print '10. The number of different routes from C to C with a distance of less than 30: ', routesManager.calculateRoutesByDistance('C', 'C', '< 30'), '(I disagree with the proposed answer given on email)'