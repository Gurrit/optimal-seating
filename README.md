# Implementation
This repository is a solution to the optimal seating problem as described in the pdf in the root directory.
This implementation is done in 4 steps,
 - First parsing the input
 - Secondly summing the happiness values of each neighbor to get the cumulative happiness of seating two persons next to each other. 
 - Third, transforming this problem into a graph where each person is a node and each happiness value is an edge. 
 - Finally, calculating the happiness value of each possible combination of seatings and finding the one with largest happiness value.


# Improvements
This implementation was done in quite a short amount of time, below are detailed
some possible improvements. 

### Algorithm improvements
The naive solution to the TSP problem should either be re-implemented
using of dynamic programming.
This solution has a time-complexity of ``O(n!)``, which means that while 
it works fine for a table with 8 people sitting at it, the feasibility of 
the solution will quickly deteriorate. 

### Parsing and error checking
this implementation relies on correctly formated data, if the input file is malformed, 
either textually, or logically, e.g.
``George would gain 12 happiness units by sitting next to George.``
the calculation will fail. 
Furthermore, no real handling is done.