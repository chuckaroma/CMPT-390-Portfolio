'''
Class representing a disjoint set data structure.
    
Usage:
    
    dj = DisjointSet( [size] )
     
    dj.find( [integer value] )
    
    dj.union( [integer value 1], [integer value 2] )

@author Greg Gagne

@date  November 2014
'''

class DisjointSet:
    
    set = [ ]

    size = 0

    '''
    The constructor

    construct a disjoint set of specified size
    '''
    def __init__(self, size):
        if size < 0:
            raise ValueError("size must be >= 0")

        self.size = size

        for i in range(0,size+1):
            self.set.append(-1)

    '''
    Perform a union of root1 and root2
    whereby root2 joins the disjoint set
    of root1.
    '''
    def union(self, root1, root2):
        if root1 < 0 or root1 > self.size or root2 < 0 or root2 > self.size:
            raise ValueError("Illegal value")

        self.set[root2] = root1

    '''
    Perform a find of the specified root
        
    Returns the integer value of the specified root.
    '''
    def find(self, root):
        if root < 0 or root > self.size:
            raise ValueError("Illegal value")

        if self.set[root] < 0:
            return root
        else:
            return self.find( self.set[root] )
