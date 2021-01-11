# Tetris
Hello, this is my Java version of Tetris. I created this program just for fun and I am not a
software engineer.

I created it in Eclipse and used the Processing core for basic 
functions like displaying the window, colors, I/O and draw shapes.
To play the game, you need the latest version of the Java Development Kit 
(see here: https://www.oracle.com/de/java/technologies/javase-downloads.html). The Java
Runtime Environment 8 does not work!

For more informations about Processing, look here: https://processing.org/
Processing is Open Source Software. The PDE (Processing Development Environment) is released 
under the GNU GPL (General Public License). The export libraries (also known as 'core') are 
released under the GNU LGPL (Lesser General Public License).

At the first start, the Tetris.jar file creates a folder named: "data". If the folder "data" will not created automatically,
you can create it by yourself in the same path where the "Tetris.jar" file exists.
This file includes some .txt files for saving the minimum and maximum level, saving the users name, saving the background color, 
saving the scores and the window size. There are 4 different screen sizes availlable.

There is a menu to navigate in the project. Important control keys and buttons are: DOWN, LEFT and RIGHT to set
the tetrominos in the required direction. The UP key rotates the tetrominos clockwise and SPACE counterclockwise.
with R, P and S you can restart, pause and start your game.
To select your username, go to settings -> name and click in the namefield (default: Unknown). After clicking, write
your name and confirm with ENTER.

Under Settings -> Color you can change the background color with the RGB controllers (slider). Under Settings -> General
you can change the screen size and the start and maximum level.

If you detect any bugs, please contact me.

For importing the Tetris game in your project, please consider the following.
After importing the file-system "Tetris" into your Project, you have to add the "core.jar" to your Build Path! You are not allowed to change the "core.jar" file (GNU LGPL).
But you are welcome to add new functions in the other files.

The Tetris.jar file is the actual game.

Have fun!
