Battleship with Java
A console-based implementation of the classic board game "Battleship" for two players. The game supports ship placement, rule validation, and turn-based combat (Hotseat mode).

📋 Features
Game Field: 10x10 grid.

Game Mode: 2 players (taking turns on the same computer).

Fleet: Standard set of 5 ships.

Mechanics:

Coordinate validation checks.

Restriction against placing ships adjacent to each other.

Fog of war (hiding enemy ships during a turn).

Display of hits (X) and misses (M).

🚀 How to Run
Ensure you have the Java Development Kit (JDK) installed.

Download the source code.

Open a terminal (command line) in the source folder.

Compile the file:

Bash

javac battleship/Main.java
Run the game:

Bash

java battleship.Main
🎮 How to Play
The game consists of two phases: Placement and Battle.

1. Ship Placement
Each player takes turns placing their ships on the field. Ships are placed by entering the starting and ending coordinates separated by a space (e.g., A1 A5).

List of Ships:

Aircraft Carrier — 5 cells

Battleship — 4 cells

Submarine — 3 cells

Cruiser — 3 cells

Destroyer — 2 cells

Important: Ships cannot touch each other by sides or corners.

2. Battle
Players take turns shooting at the opponent's field. To fire, enter a target coordinate (e.g., F7 or J10).

After each turn, the game asks you to press Enter to pass the move to the other player, clearing the screen/hiding the view.

Legend
~ — Fog (Unknown water)

O — Your ship (intact)

X — Hit (Ship damaged or sunk)

M — Miss

🛠 Requirements
Java 8 or higher.
