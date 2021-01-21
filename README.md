# Battleships
Stage one - Objectives
In this stage, you should arrange your ships on the game field. Before you start, let's discuss the conventions of the game:

On a 10x10 field, the first row should contain numbers from 1 to 10 indicating the column, and the first column should contain letters from A to J indicating the row.
The symbol ~ denotes the fog of war: the unknown area on the opponent's field and the yet untouched area on your field.
The symbol O denotes a cell with your ship, X denotes that the ship was hit, and M signifies a miss.
You have 5 ships: Aircraft Carrier is 5 cells, Battleship is 4 cells, Submarine is 3 cells, Cruiser is also 3 cells, and Destroyer is 2 cells. Start placing your ships with the largest one.
To place a ship, enter two coordinates: the beginning and the end of the ship.
If an error occurs in the input coordinates, your program should report it. The message should contain the word Error.

Stage Two - Objectives
Take a shot at a prepared game field. You need to indicate the coordinates of the target, and the program should then display a message about a hit or a miss. If the shell misses the target and falls in the water, this cell should be marked with an M, and a successful strike is marked by an X. After this shot, the game should be stopped.

If the player managed to hit a ship, the game should display a message You hit a ship!; otherwise, the message is You missed!

Stage Three - Objectives
In this stage, you need to implement the "fog of war" feature in your game. First, place all the ships on the game field, and then hide them with the symbol ~. Take a shot like in the previous step, and after your attempt, the program should print a message along with two versions of the field: one covered with the fog of war and another one open

Stage Four - Objectives
To complete this step, you should add a check that all the ships were successfully sunk. The game is supposed to go on until all ships go down. The program should print an extra message You sank a ship! when all the cells of a particular ship have been hit. Take a look at the examples below!

Stage Five - Objectives
Both players add the ships to their fields one by one (no peeking!), and then start shelling each other until one of them succeeds. To make the game fair and prevent the players from peeping at each other's fields, after each move add the message "Press Enter and pass the move to another player", which will clear the screen.
To complete this stage and the entire project, add a PvP component to your game. Now the player will see not only the opponent's screen but their own as well. Place the opponent's screen at the top and your field at the bottom.
