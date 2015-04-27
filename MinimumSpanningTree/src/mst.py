'''
Constructs the minimum spanning tree using both
Prim's and Kruskal's algorithms.

Usage:
    python mst.py [input file] [prims | kruskals]

[Charles Bailey]

[11/19/14]
'''

import sys
import heapq
from DisjointSet import DisjointSet
'''
An implementation of Prim's algorithm for finding
the MST of a connnected, weighted, undirected graph.
'''
def prims(inputFile):
    '''
    input file containing weighted graph
    
    file contents is
    [vertex 1] [vertex 2] [weight]
    '''
    graph = open(inputFile)

    '''
    an initially empty dictionary containing mapping
    [vertex]:[adjacency list]
    '''
    adjacency = { }

    '''
    the collection of vertices (there may be duplicates)
    '''
    nodes = [ ]
    
    '''
    The following reads in the input file
    and constructs an adjacency list of
    the graph.
    '''
    for line in graph:
        entry = line.split()
        
        if entry[0] not in adjacency:
            adjacency[entry[0]] = []
           
        # construct an edge for the adjacency list
        edge = (entry[1], int(entry[2]))
        adjacency[entry[0]].append(edge)

        # add the nodes
        nodes.append(entry[0])
        nodes.append(entry[1])

    '''
    output the adjacency list 
    '''
    for v in adjacency:
        print v, adjacency[v]

    '''
    Now construct the MST using Prim's algorithm:
    '''
    'The list representing vertexes already visited'    
    visitedEdges = []
    'The Minimum Spanning Tree'
    mst = []
    
    'The total cost of the minimum spanning tree'
    totalCost = 0
    
    'An empty list that will represent the priority queue'
    pqueue = []
    
    startNode = nodes[0]
    '''
    We populate the priority queue with the starting vertex and it's adjacency list
    '''
    for  v, cost in adjacency[startNode]: 
        edge = (cost, startNode, v)
        heapq.heappush(pqueue, edge)
        
    visitedEdges.append(startNode)
    '''
    While the priority queue is not empty, we pop our current edge off of the priority queue and set that equal to 
    a current edge tuple. We then check if the second vertex is in the visitedEdges list. If it is not in that list, we add
    the vertex to the vistitedEdges list. We also add the current edge tuple to the mst list, as well as add the cost here.
    We go through another for loop and check the adjacency list for the next vertex and cost. We construct another tuple to 
    represent the next edge. We also check to see if the next vertex is not in the visitedEgdes list. If it is not the list,
    we push the nextEdge on the priority queue and the return the cost and mst list.
    '''
    while pqueue:
        currentEdge = heapq.heappop(pqueue)
        if currentEdge[2] not in visitedEdges:
            visitedEdges.append(currentEdge[2])
            mst.append(currentEdge)
            totalCost = currentEdge[0] + totalCost
            for nextVertex, cost in adjacency[currentEdge[2]]:
                newEdge = (cost, currentEdge[2], nextVertex)
                if nextVertex not in visitedEdges:
                    heapq.heappush(pqueue, newEdge)
    'Returns the cost and minimal spanning tree'      
    return totalCost, mst,
            

    return 0

'''
An implementation of Kruskal's algorithm for finding
the MST of a connnected, weighted, undirected graph.
'''
def kruskals(inputFile):

    graph = open(inputFile)
    
    pqueue = [] 
    
    mst = []
    
    totalCost = 0
    
    for line in graph:
        entry = line.split()
           
        # construct an edge for the adjacency list
        'We construct this edge and use the cost as the starting element for this tuple to use in the priority queue'
        edge = (int(entry[2]), entry[0], entry[1])
        'we push the edge onto the priority queue'
        heapq.heappush(pqueue, edge)

    '''
    We convert the length of the priority queue into an integer to use with a disjointset
    '''      
    size = len(pqueue)
    frontier = DisjointSet(size)
    
    '''
    Going through each edge in the priority queue, we set the newEdge equal to the current tuple in the priority queue
    and then perform the find function from disjointset on the two vertexes. Since the vertexes are in string form from 
    the tuple, the ord function is used to convert the vertexes into integers for use in the find function. 96 is subtracted from the 
    tuple vertexes to set the unicode code pointer equal to the integer value of the letter in the alphabet (a = 97 with the ord function, and subtracting 97 gives us a = 1).
    We then check if the two vertexes through integer values are not equal to each other. If they are not, we add the the nextEdge tuple to the 
    minimum spanning tree list, and perform a union of the two vertices using the find function from Disjoin set. We also calculate the cost in this 
    if statement.
    '''
    for newEdge in sorted(pqueue):
        newEdge = heapq.heappop(pqueue)
        v1 = frontier.find(ord(newEdge[1])-96)
        v2 = frontier.find(ord(newEdge[2])-96)
        if v1 != v2:
            totalCost = newEdge[0] + totalCost
            mst.append(newEdge)
            frontier.union(v1, v2)
    'Returns the total cost of the mst and the mst list'        
    return totalCost,mst
        
        
    return 0

'''
The main function
'''

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print 'Usage python mst.py [input file] [prims | kruskals]'
        quit()
       
    if sys.argv[2] == 'prims': 
        print prims(sys.argv[1])
    elif sys.argv[2] == 'kruskals':
        print kruskals(sys.argv[1])
    else:
        print 'Illegal algorithm. Must be either \'prims\' or \'kruskals\' '
