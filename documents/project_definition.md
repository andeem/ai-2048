# Project definition
AI2048 will be a project including artificial intelligence for the game 2048 based on Monte Carlo tree search(MCTS) and a GUI for demonstrating the AI. There will be also some benchmarking between the different approaches of MCTS, and possibility to inspect the effects of certain parameters used in MCTS.

2048 is a game where numbers 2 and 4 are randomly added to a 4*4 board and player moves all blocks in one direction(up, down, left or right) at the time, combining two adjacent blocks to a new block, if the adjacent blocks have the same value. The new block will then have a value of the sums of blocks that were combined. The goal is to reach 2048 in highest block, however the game can continue far beyound that.

Monte Carlo tree search has been proven to be a powerful algorithm for different game AIs, probably most notable game being Go. 
## Algortithms and data structures
AI will be based on the Monte Carlo tree search and different variants will be used, namely “pure” MCTS and MCTS with Upper Confidence bound applied to Trees(UCT). As the name suggests, MCTS is based on a search tree. The implementation, however, does not require explicit tree data structure, instead nodes are represented by objects implementing a class defining the game state, and edges by associations between objects. For optimizing the algorithm game state objects need to be hashable so that equal game states results in equal hashes, and data structure is needed for storing hashes and corresponding objects. The actual structure is yet to be decided. For backpropagation(traveling back the path to root) there needs to be a suitable data structure for storing the nodes, most probably stack will be used.

2048 game itself does not require complex data structures but there is possibility that linked list will be implemented for simplifying some operations needed to handle player moves.
## Complexity
Monte Carlo tree search performs *n* iterations for deciding the next move, each iteration consisting of the following steps:
1. **Selection** where the tree is travelled from root, root being current state of the game, to incomplete node, where incomplete node is a node which has one or more undiscovered childrens. Possible game states that can be achieved by legal play from the current node are represented as the child nodes of the current node. Each node also stores wins to total playouts ratio.
2. **Expansion** where a child node is added to the search tree.
3. **Simulation** where a random playout is completed.
4. **Backpropagation** where the wins to total playouts of all nodes on the path from the expanded node to the root are updated.

Selection phase can travel down to leaf node of the search, so it takes O(d) time, where d is the depth of the search tree. Selection needs also consider to which child node to travel next, and this takes O(b) time, where b is the count of possible branches emerging from the node. Hereby the total time complexity for the selection phase is O(db). In this implementation the branching factor is constant(4 for player moves and 30 for the addition of 2 or 4 to an empty space on the board), so the time complexity can be simplified to O(d).

Expansion should happen in constant time O(1)

Simulation travels down to leaf of the game tree and next node is chosen randomly, so time complexity is O(d´) where d´ is the depth of the game tree.

Backpropagation can travel from the leaf of the search tree to the root of the search tree, so the time complexity is O(d).

As the depth of the search tree at its highest is the depth of the game tree, the depth of the game tree can be used instead of the depth of the search tree for simulation and backprogation, resulting in time complexity O(d´) for both. The total time complexity for one iteration is then O(d´) + O(1) + O(d´) + O(d´) equalling to O(d´). For simplicity d is used to represent the depth of the game tree from here on, the time complexity, thus being O(d) for one iteration and O(nd) for n iterations.

In conclusion the time complexity for MCTS, in this implementation, for one move is O(nd) where n is the count of iterations and d is the depth of the game tree

As the search tree is kept in the memory during the whole gameplay, and it grows towards the whole game tree which has b^(d-1)/(b-1) nodes the space complexity is O(b^(d-1)/(b-1)), b being the maximum number of branches per node and d the depth of the game tree. There can be 30 or 4 branches per node as shown earlier, so the space complexity is O(30^(d-1)/(30-1)). In practice the whole game tree does not exist at any time, but with high count of iterations n substantial part of the game tree is generated, and this can have undesired effects on the size of the memory required.

## Input/output
The 2048 game does not require any input, as the initial state is generated randomly but possibility to start from pre-determined state might be added.

Output will be the final state of the game.

## References
[Wikipedia entry for MCTS](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search)  
[2048 game](https://gabrielecirulli.github.io/2048/)  
[Introduction to Monte Carlo Tree Search](https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/)
