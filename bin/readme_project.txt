/**********************************************************************
 *  readme template                                                   
 *  Project
 **********************************************************************/

Name: Kenny Hoang
PennKey: kthoang
Recitation: 220
Project choice: 2048



/**********************************************************************
 *  Have you entered all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/
Yes. Credits to Halal (TA) for helping with bug where new game would sometimes
    have more than 2 tiles. This was because of full keystroke queue prior
    to restarting would cause moves to be made unintentionally.




/**********************************************************************
 *  How do you execute your program? Which class do you run, are there
 *  any command line arguments?
 **********************************************************************/
There are no command line arguments. Simmply run the Game2048 class with 
    java Game2048






/**********************************************************************
 *  Did you add any additional features to your project beyond the
 *  specification that you added? If so, describe them here.
 **********************************************************************/
Added features are that user has ability to restart game easily with a click
    of a button after a loss or win as well as ability to continue in "endless"
    mode after a win (probably limited by computer's abilities). 





/**********************************************************************
 *  Explain how you went about approaching the problem using
 *  object oriented programming.
 **********************************************************************/
Board.java was a Board object, with a inner class for Tile object. Board object 
 was a 4x4 2D array of tiles, each with a position and value. This enabled me 
 to use my main class (Game2048.java) to support the visual game and controls,
where underlying methods and processing was done in Board.java such as the
board moves and checking if a game has been won or not.                           


/**********************************************************************
 *  List all the files in project.zip and explain their purpose.
 **********************************************************************/
Board.java purpose is to be the Board object. It holds the Tile inner class, 
which is itself an object. Also holds all the methods for Tile and Board class
to assist in making board moves, checking if game has been won, and restarting
the game. 
                          
Game2048.java purpose is to be the main class where the game itself is 
implemented and illustrated for usage. Here users will run this program to
play the game, including seeing the tiles and inputting controls.


/**********************************************************************
 *  Please explain how we should use your program.                    
 **********************************************************************/
Execute the Game2048.java file with java Game2048. Move board around with
keys w, s, a, d for directions up, down, left, and right. When game is over,
click play again button to restart the game. If you win, click play again 
button to restart the game or the keep going button to play in "endless" mode.                          


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
Time consuming but worthwhile, just hellish to do during finals season.
 
 
 
