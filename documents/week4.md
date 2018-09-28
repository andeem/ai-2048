# Weekly report, week 4
## Progression
* Debugging, studying MCTS and possible ways to optimize "random" gameplay on 2048
## Learning experience
* Further understanding of MCTS
* It is quite clear what data structures are needed
## Problems or challenging topics
* Still behind schedule
* Simulation with random playouts is poor solution, slight improvement was achieved by selecting one primary direction and one secondary if primary is not possible. If both are impossible, then select random. This was still not enough to achieve "win" from initialized board. I need to find more efficient way to select moves inside simulation. This should not be that difficult as there is quite simple and efficient strategy, that can be used by human while playing 2048.
## Next week
Same as last week: Get the AI done, start with own datastructures, clean code(!!) and write tests
Start writing implementation and testing documents
## Hours
* 27.9 2h: Debugging, studying MCTS
* 28.9 4h: Debugging, testing different ways to implement selecting moves inside simulation
