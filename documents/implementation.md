# Implementation document

# Structure

TODO: Class diagram here

## AI
* AI package contains class implementing the MCTS AI

## ConsoleUI
* ConsoleUI package contains class for console ui. UI is used mainly for performance testing.

## Game2048
* Package containing the 2048 game implementation including board class. The Game interface could and should be moved to AI or dedicated interfaces package as it is, or should be, only an interface for any two player, turn based game for MCTS AI.

## Utils
* Package for utilities, such as data structures etc.