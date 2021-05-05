# Discussion

**Describe how `Explore.run` works. Describe (briefly) the strategy (algorithm) behind 
the `run` method. Elaborate on its time and space complexity (in asymptotic big Oh notation). 
Make sure to elaborate on the role of the input parameter `limit`.**

Explore.run uses a depth first search algorithm. The exploration process uses a visit method 
that first considers the outgoing edges or links leading to children of a vertex before that 
vertex’s siblings. First, the cell is explored. Then, a for loop iterates over the neighbors 
of the cell, or the cells that are linked with the cell. If c is an unexplored neighbor, then 
the distance of c is the distance of the cell, its parent cell, plus 1. The neighbor of cell, 
which is c,  is then added to the trail. After c is added at the end of the trail, visit 
is called again to visit all the other cells, or neighboring cells of c, before finally 
adding the cell to the trail. In summary, all the children of the children of a are visited 
before visiting the cell’s sibling. The time complexity for dfs is linear or O(n) since the 
time complexity is based on the number of neighbors of a cell, N,  and the number of unexplored 
neighbors in each vertex, M. So time complexity is O(N + M). The space complexity is also 
linear O(n) because the siblings of each vertex have to be stored. These are used when 
seeing which vertices to explore next. The input parameter limit is used to control 
how deep or how long a path can be. If this dfs program goes to a depth greater 
than limit, then another vertex is visited, meaning that instead of going the full depth of a vertex
and visiting all the vertex's children before visiting siblings, if the current depth exceeds the limit, 
the siblings have to be visited. 












---

**Notice the `next` cell (to move to) is removed (dequeued) out of the "trail" but after 
`ladyBug.moveTo(next)`, it is added back to the trail. 
Why is the `next` cell added back to the trail? 
Is this a mistake? Does this (or could this) result in erroneous behavior?**
This does not seem to be a mistake and rather something needed in the program.
When I commented out ladyBugTrails.get(ladyBug).add(next);, there was a null
pointer exception thrown and I believe this was thrown in instances where the ladybug
is at a bad dead end where it can't determine where to go next and it must know that it
can always go back the same way, which is why the cell previously dequed is pushed back
in for it to be a cell the lady bug can still go to.








