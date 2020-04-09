/*
Alan Li
ConcentrationGame
Mrs. Krasteva
January 10, 2019
The purpose of this program is to program a classic game of Concentration played by two players and automatically keep score based on
the number of matches made by the players. The game can also detect if a win has been made.

Screen movement
SplashScreen runs first, without anything else and the goes to main menu
mainMenu can go to askData, highScore, instructions or goodbye.
askData can go back to main menu or go to display
high score will run and then go back to main menu
instructions will run, wait for user to continue and then go back to menu
goodbye will exit the program.
display will allow the user to play the game, and then once someone has won it will go to winScreen
winScreen will finish and then go back to main menu.

Variable Dictionary
NAME                            TYPE                    DESCRIPTION
c                               Console                 Everything that has to draw graphics
blue                            Color                   main background colour
userMenuChoice                  char                    Stores the user's choice from the menu page
userDifficulty                  char                    Stores the difficulty the user would like to play on
highScoreNames                  String[]                Stores high score names for easy difficulty
highScoreNames1                 String[]                Stores high score names for medium difficulty
highScoreNames2                 String[]                Stores high score names for hard difficulty
memoryCard                      int[][]                 Stores the data for all of the cards
numbersPicked                   int[][]                 Makes sure that the random generation works
highScore, highScore1, highScore2 int[][]               Stores the top 10 scores for each difficulty
player1Score, player2Score      int                     Keeps track of each player's score during the game
matched                         boolean[][]             Keeps track of which cards have been matched already
*/
//importing libraries
import java.io.*;
import hsa.*;
import java.awt.*;
import javax.swing.JOptionPane;
public class ConcentrationGame //new class
{
    Console c; //initializing new console
    final Color blue = new Color (30, 144, 225); //constant value, used mainly for background
    char userMenuChoice, userDifficulty; //userMenuChoice is used to track which choice is chosen in menu
    //userDifficulty is used to keep track of what difficulty the two players are playing the game on
    String[] highScoreNames = new String [10], highScoreNames1 = new String [10], highScoreNames2 = new String [10];
    //highScoreNames track the names of the top 10 people for easy, medium and hard difficulties respectively
    int player1Score = 0, player2Score = 0, numbersPicked[], memoryCard[] [], highScore[] = new int [10], highScore1[] = new int [10], highScore2[] = new int [10];
    //player1Score and player2Score will keep track of the two player's scores, numbersPicked[] is used to make sure that the randomly generated data ensures 2 of the same pairs
    //memoryCard[][] stores each card's designated number, and is used to draw the corresponding graphic to each number
    //the three highScore[] arrays keep track of the top 10 highest scores on easy, medium and hard difficulties respectively
    boolean[] [] matched; //this keeps track of which pairs have already been matched. All values are originally false, but if matched they are set to true.
    public ConcentrationGame ()  //constructor
    {
	c = new Console (30, 120, "Two Player Concentration Game"); //setting console size with a title
	//960 by 600
    }

    /*Void method to display the splash screen
    Variable List
    NAME                    TYPE                    DESCRIPTION
    letters                 String[]                Array of letters that can be printed on each card
    lastMessage             String[]                Array of letter to write start game in the bottom
    a                       Card                    First leter to animate
    b                       Card                    Second letter
    d                       Card                    Third letter
    f                       Card                    Fourth letter
    g                       Card                    Fifth letter
    h                       Card                    Sixth letter
    k                       Card                    Seventh letter
    l                       Card                    Eigth letter
    m                       Card                    Ninth letter
    n                       Card                    Tenth letter
    o                       Card                    Eleventh letter
    p                       Card                    Twelfth letter
    q                       Card                    Thirteenth letter to animate
    cur                     int                     location variable that is relative to animate the black bar
    
    for loop to show each card one at a time in the splash screen
    try catch to animate screens
    */
    public void splashScreen () 
    {
	String[] letters = {"C", "O", "N", "C", "E", "N", "T", "R", "A", "T", "I", "O", "N"}, lastMessage = {"S", "T", "A", "R", "T", " ", "G", "A", "M", "E"};
	//list of letters, one card for each letter
	c.setColor (blue); //draw background
	c.fillRect (0, 0, 960, 600);
	int cur = 7; //current x position
	Card a = new Card (c, cur, -10); //new Cards to animate splashscreen
	a.start (); //start each Card after declaring it
	Card b = new Card (c, cur + 70, -50);
	b.start ();
	Card d = new Card (c, cur + 140, -90);
	d.start ();
	Card f = new Card (c, cur + 210, -130);
	f.start ();
	Card g = new Card (c, cur + 280, -170);
	g.start ();
	Card h = new Card (c, cur + 350, -210);
	h.start ();
	Card k = new Card (c, cur + 420, -250);
	k.start ();
	Card l = new Card (c, cur + 490, -290);
	l.start ();
	Card m = new Card (c, cur + 560, -330);
	m.start ();
	Card n = new Card (c, cur + 630, -370);
	n.start ();
	Card o = new Card (c, cur + 700, -410);
	o.start ();
	Card p = new Card (c, cur + 770, -450);
	p.start ();
	Card q = new Card (c, cur + 840, -490);
	q.start ();
	try
	{
	    Thread.sleep (4000); //delay for 4 seconds, then allow the black screen to come in
	}
	catch (Exception e)
	{
	}
	for (int i = -100 ; i <= 300 ; i += 2)
	{
	    c.setColor (new Color (0, 0, 0)); //slide the black rectangle in from left to right
	    c.fillRect (i, 350, 250, 50);
	    try
	    {
		Thread.sleep (2); //delay by 0.002 seconds each time
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (blue);
	    c.fillRect (i - 100, 350, 100, 50); //fill the previous stuff behind it so it moves properly
	}
	int curx = 320, cury = 390; //set the current x and y
	for (int i = 0 ; i < 3 ; i++)
	{
	    c.setColor (new Color (255, 255, 255)); //set colour to white
	    c.fillRect (curx, cury, 30, 5); //draw a flashing underscore to represent where the character is
	    try
	    {
		Thread.sleep (250);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (new Color (0, 0, 0));
	    c.fillRect (curx, cury, 30, 5); //draw a black rectangle over it each time to make it flash
	    try
	    {
		Thread.sleep (250); //delay by 0.25 seconds to make it flash at an appropriate pace
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setColor (new Color (255, 255, 255));
	c.setFont (new Font ("Serial", Font.PLAIN, 30)); //set font
	c.drawString ("A", curx, cury); //draw an 'A' to the screen
	try
	{
	    Thread.sleep (250);
	}
	catch (Exception e)
	{
	}
	Color white = new Color (255, 255, 255);
	c.drawString ("1", curx + 20, cury); //draw a '1' to the screen
	for (int i = 0 ; i <= 255 ; i++)
	{
	    c.setColor (blue); //set colour to blue
	    c.fillRect (560, cury - 40, 60, 50);
	    c.setColor (new Color (255, 255, 255, i)); //animates the enter key fading into the screen
	    c.fillRect (560, cury - 40, 60, 50);
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setColor (new Color (0, 0, 0)); //set colour to black
	c.setFont (new Font ("Serif", Font.PLAIN, 20));
	c.drawString ("Enter", 560, cury - 10); //label the box as the enter key
	c.setColor (new Color (192, 192, 192));
	c.fillRect (560, cury - 40, 60, 50); //draw another different coloured rectangle over it so that it represents key has been pressed
	c.setColor (new Color (0, 0, 0));
	c.setFont (new Font ("Serif", Font.PLAIN, 20));
	c.drawString ("Enter", 570, cury - 10); //redraw the string
	try
	{
	    Thread.sleep (300);
	}
	catch (Exception e)
	{
	}
	c.setColor (new Color (0, 0, 0));
	c.setFont (new Font ("Serif", Font.PLAIN, 20));
	c.drawString ("Enter", 570, cury - 10);
	c.setColor (new Color (255, 255, 255));
	c.fillRect (560, cury - 40, 60, 50);
	c.setColor (new Color (0, 0, 0));
	c.fillRect (300, 350, 250, 50);
	c.setFont (new Font ("Serif", Font.PLAIN, 50));
	for (int i = 7 ; i <= 890 ; i += 70) //for loop to draw every single letter
	{
	    c.setColor (new Color (255, 255, 255)); //set the color to white
	    c.fillRoundRect (i, 170, 70, 70, 20, 20); //draw a white card over the original card, look like card has been flipped
	    c.setColor (new Color (0, 0, 0)); //set Color to black
	    c.drawRoundRect (i, 170, 70, 70, 20, 20); //draw outline of each card
	    c.drawString (letters [i / 70], i + 20, 220); //draw in the letter for that card
	    try
	    {
		Thread.sleep (250); //space
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setFont (new Font ("Serial", Font.PLAIN, 30));
	c.setColor (new Color (255, 255, 255));
	for (int i = 0 ; i < lastMessage.length ; i++)
	{
	    c.drawString (lastMessage [i], curx + i * 20, cury); //for loop to draw start game
	    try
	    {
		Thread.sleep (150); //spacing in between each letter showing in the black bar
	    }
	    catch (Exception e)
	    {
	    }
	}
	try
	{
	    Thread.sleep (3000); //wait 3 seconds before proceeding to the menu
	}
	catch (Exception e)
	{
	}
    }


    /*Void method to display the main menu screen
    Variable Dictionary
    NAME                    TYPE                
    */
    public void mainMenu ()
    {
	//Setting up the game, giving array values, reading from the files etc.
	BufferedReader input; //declare a BufferedReader to read the files
	int easyLineCount = 0; //line counter for how many high scores there currently are in the file
	try
	{
	    input = new BufferedReader (new FileReader ("EasyHighScores.txt")); //initialize the bufferedreader to read
	    String line = " "; //line used to keep storing the input, and reading the next line continuously
	    while (line != null) //if the line is not null then there is something there
	    {
		line = input.readLine (); //use the BufferedReader to read the next line
		if (line == null) //if the line is null then we break
		    break;
		String[] split = line.split (" "); //otherwise, split it by a space, seen by formatting in the winScreen() method
		highScoreNames [easyLineCount] = split [1]; //the name is the first part, so we store it in the corresponding String array
		highScore [easyLineCount] = Integer.parseInt (split [2]); //that person's score is the second part so we store it in the corresponding integer array so it can be used when displaying the high scores
		easyLineCount++; //one more valid line, so add 1
	    }
	}
	catch (IOException e)
	{
	}
	for (int i = easyLineCount ; i < 10 ; i++) //since all the previous values are actual high scores, set the rest of them to -1 so they will not be printed
	{
	    highScore [i] = -1;
	}
	int mediumLineCount = 0;  //count the number of valid high scores for the medium highscore file
	try
	{
	    input = new BufferedReader (new FileReader ("MediumHighScores.txt")); //re-intialize the BufferedReader to read the medium high score file
	    String line = " "; //line is used to store each line during the while loop
	    while (line != null) //while there are still lines to be read
	    {
		line = input.readLine (); //read that line and store it to the 'line' variable
		if (line == null) //if the input is null then break out of the while loop
		    break;
		String[] split = line.split (" "); //otherwise, split it into two parts by a space
		highScoreNames1 [mediumLineCount] = split [1]; //name is the first part, other than the placement on the high score list
		highScore1 [mediumLineCount] = Integer.parseInt (split [2]); //score is the second part so put it into corresponding integer array
		mediumLineCount++;
	    }
	}
	catch (IOException e)
	{
	}
	for (int i = mediumLineCount ; i < 10 ; i++) //all other scores are set to -1 in the high score integer array so they will not be printed
	{
	    highScore1 [i] = -1;
	}
	int hardLineCount = 0; //count number of valid high scores in the hard high score section
	try
	{
	    input = new BufferedReader (new FileReader ("HardHighScores.txt")); //re-intialize BufferedReader to read the hard high score file
	    String line = " "; //line continues to store the lines of input from the file
	    while (line != null) //while there is still high scores to be read
	    {
		line = input.readLine (); //read the input
		if (line == null) //if the line is null then we break
		    break;
		String[] split = line.split (" "); //otherwise
		highScoreNames2 [hardLineCount] = split [1];
		highScore2 [hardLineCount] = Integer.parseInt (split [2]);
		hardLineCount++;
	    }
	}
	catch (IOException e)
	{
	}
	for (int i = hardLineCount ; i < 10 ; i++)
	{
	    highScore2 [i] = -1;
	}
	c.setColor (blue); //draw the menu screen
	c.fillRect (0, 0, 960, 600);
	c.setColor (new Color (255, 255, 102));
	c.setFont (new Font ("Serif", Font.BOLD, 60));
	c.drawString ("Concentration", 300, 40);
	c.setFont (new Font ("Serif", Font.PLAIN, 30));
	c.drawString ("Enter 'i' or ' I ' for the instructions page", 260, 100); //prompting for input
	c.drawString ("Enter 'h' or 'H' for a list of high scores", 260, 140);
	c.drawString ("Enter 'c' or 'C' to continue and begin to play the game", 200, 180);
	c.drawString ("Enter anything else if you would like to exit the game", 205, 220);
	userMenuChoice = c.getChar (); //get the choice of the user to go to whichever screen
    }


    /*Void method to ask the user for the difficulty they would like to play
    Variable Dictionary
    NAME            TYPE                DESCRIPTION
    goToDisplay     boolean             checks if the user has entered one of the difficulties or not
    */
    public void askData ()  //method to ask for the difficulty
    {
	c.clear (); //clear the console
	c.setColor (blue);
	c.fillRect (0, 0, 960, 600); //background
	c.setFont (new Font ("Serif", Font.BOLD, 60));
	c.setColor (new Color (255, 255, 102));
	c.drawString ("Difficulty", 350, 40); //draw out the screen
	c.setFont (new Font ("Serif", Font.PLAIN, 30));
	c.drawString ("To play on easy mode, please enter 'e' or 'E'.", 150, 100);
	c.drawString ("To play on medium mode, please enter 'm' or 'M'.", 140, 130); //prompt user for difficulty of the game
	c.drawString ("To play on hard mode, please enter 'h' or 'H'.", 150, 160);
	c.drawString ("Otherwise, anything else that you print will go back to the main menu.", 70, 190);
	boolean goToDisplay = true; //if they do not pick one of the difficulties, this will be false and then go to menu.
	userDifficulty = c.getChar (); //get the character input
	if (userDifficulty != 'e' && userDifficulty != 'E' && userDifficulty != 'm' && userDifficulty != 'M' && userDifficulty != 'h' && userDifficulty != 'H')
	{
	    goToDisplay = false; //if it is not equal to any of these difficulties, print false
	}
	if (goToDisplay) //if difficulty chosen
	{
	    display (); //go to the display method
	}
    }


    /*Void method to display the instructions to the user
    no local variables
    no try statements
    no loops
    */
    public void instructions ()  //instructions method
    {
	c.clear ();
	c.setColor (blue);
	c.fillRect (0, 0, 960, 600); //draw background
	c.setFont (new Font ("Serif", Font.BOLD, 60)); //written instructions below
	c.setColor (new Color (255, 255, 102));
	c.drawString ("Instructions", 325, 50);
	c.setFont (new Font ("Serif", Font.PLAIN, 30));
	c.drawString ("This is a two player game that is based on matching.", 10, 90);
	c.drawString ("At each  player's turn, they will pick two cards and try to match them.", 10, 140);
	c.drawString ("If you match two cards, then you will get one point.", 10, 190);
	c.drawString ("The player with the most points in the end will win!", 10, 240);
	c.drawString ("To flip over a card, you can enter a point like a chessboard to flip it.", 10, 290);
	c.drawString ("For example, entering 'A1' and 'D4' will flip over the bottom left and ", 10, 340);
	c.drawString ("top right cards, respectively, in easy mode.", 10, 390);
	c.drawString ("Press any key to continue:", 440, 550);
	c.getChar ();
    }

    /*Void method to display the playing screen of the program
    Variable Dictionary
    NAME                        TYPE                            DESCRIPTION
    grey                        Color                           All 5 colours are variables that I set for efficiency because I use these colours a lot
    pink                        Color
    white                       Color
    red                         Color
    green                       Color
    gameWon                     boolean                         Checks if the game has been won or not, used in the while loop to run forever until someone wins
    column                      String[]                        Stores the labels on the sides of the grid
    row                         String[]                        Stores the labels on the sides of the grid
    max                         int                             maximum value for the random number generator so I can generate all the numbers
    min                         int                             minimum value for the random number generator so I can generate all the values
    range                       int                             Generate the random value in between the minimum and maximum
    playerTurn                  int                             decides whose turn it is
    firstChoice                 String                          stores the player's first choice of a card
    sub                         String                          stores the entire input of the line that the user wrote
    line                        String                          currently stores the input 1 character by character each type that the user types one
    curx                        int                             
    cury                        int
    curr                        int
    curh                        int
    */
    private void display ()
    {
	c.clear (); //clear console
	boolean gameWon = false; //check if the game has been won or not
	//local color variables that I use a lot
	Color grey = new Color (192, 192, 192), white = new Color (255, 255, 255), red = new Color (255, 0, 0);
	Color pink = new Color (255, 99, 71), green = new Color (0, 255, 0);
	//Two string arrays used to draw the row and column labels for easy use
	String[] column = {"A", "B", "C", "D", "E", "F", "G", "H"}, row = {"1", "2", "3", "4", "5", "6", "7", "8"};
	player1Score = 0; //every game, reset the score of player 1 and 2
	player2Score = 0;
	c.setColor (blue); //set color to blue
	c.fillRect (0, 0, 960, 600);
	c.setColor (red);
	for (int i = 0 ; i < 10 ; i++)
	{
	    c.drawRect (687 + i, i + 50, 140, 55); //draw the box around player 1 score
	}
	c.setColor (green);
	for (int i = 0 ; i < 10 ; i++)
	{
	    c.drawRect (687 + i, i + 450, 140, 55); //draw the box around player 2 score
	}
	if (userDifficulty == 'e' || userDifficulty == 'E')
	{
	    int max = 8, min = 1, range = max - min + 1, playerTurn = 0; //generate the random locations of the cards
	    memoryCard = new int [4] [4];
	    numbersPicked = new int [max];
	    matched = new boolean [4] [4]; //Represents which cards have already been matched
	    //we use a  nested for loop to put all the numbers into the 2d array, memoryCard
	    //This array will store all the locations so we can refer to it for future use. In easy mode, this will contain 2 of 8
	    //different cards, in total being 16 cards, which is a 4x4 square
	    //numbersPicked[] makes sure that if a card has already been put out there twice then it does not get put on another card.
	    for (int i = 0 ; i < 4 ; i++)
	    {
		for (int j = 0 ; j < 4 ; j++)
		{
		    int val = (int) (Math.random () * range) + min; //generate a random number between 1 and 8
		    if (numbersPicked [val - 1] < 2) //each number can only be picked twice, so make sure that it has not been picked twice yet
		    {
			numbersPicked [val - 1]++; //add 1 to that number
			memoryCard [i] [j] = val; //set that card to this number that corresponds to a specific graphic
		    }
		    else
			j--; //randomize again because the number has already been picked twice
		}
	    }
	    //code to cheat the game, prints out where all the matching things are with numbers

	    /*for (int k = 0 ; k < 4 ; k++)
	    {
		for (int j = 0 ; j < 4 ; j++)
		{
		    System.out.print (memoryCard [k] [j] + " ");
		}
		System.out.println ();
	    }*/
	    //draw out the cards
	    c.setFont (new Font ("Serif", Font.BOLD, 30));
	    for (int i = 0 ; i < 4 ; i++)
	    {
		c.setColor (new Color (0, 0, 0));
		c.drawString (row [i], i * 120 + 200, 40);
		for (int j = 0 ; j < 4 ; j++)
		{
		    //card design
		    c.setColor (new Color (0, 0, 0));
		    c.drawString (column [i], 120, i * 120 + 120);
		    c.setColor (grey);
		    c.fillRoundRect (j * 120 + 160, i * 120 + 50, 110, 110, 30, 30);
		    c.setColor (pink);
		    c.fillRoundRect (j * 120 + 165, i * 120 + 55, 100, 100, 30, 30);
		    c.setColor (white);
		    for (int k = 0 ; k < 30 ; k += 10)
		    {
			c.drawOval (j * 120 + 165 + k, i * 120 + 55 + k, 100 - k * 2, 100 - k * 2);
		    }
		}
	    }
	    String firstChoice = ""; //variable to track user's first choice
	    while (!gameWon) //keep having turns if the game hasn't been won yet
	    {
		c.setFont (new Font ("Serif", Font.PLAIN, 20));
		c.setColor (white);
		c.fillRect (697, 60, 130, 45); //Fill box over player 1 score
		c.fillRect (697, 260, 135, 45); //fill over player turn
		c.fillRect (697, 460, 130, 45); //fill over player 2 score
		c.setColor (blue);
		c.fillRect (0, 540, 600, 40); //Fill over bottom two lines of text so it can be written again
		c.setColor (new Color (0, 0, 0));
		playerTurn++; //player turn tracks who's turn it is
		if (playerTurn % 2 == 1) //player 1's turn
		{
		    c.drawString ("1", 755, 300); //draw 1 under the player turn
		    c.setColor (red);
		    for (int i = 0 ; i < 10 ; i++)
		    {
			c.drawRect (695 + i, i + 250, 130, 55); //border around player 1's turn is drawn as red
		    }
		}
		else //otherwise, it is player 2's turn
		{
		    c.drawString ("2", 755, 300);
		    c.setColor (green);
		    for (int i = 0 ; i < 10 ; i++)
		    {
			c.drawRect (695 + i, i + 250, 130, 55); //Box around player 2's turn is drawn as green
		    }
		}
		c.setColor (new Color (0, 0, 0)); //set color to black
		c.drawString (Integer.toString (player1Score), 755, 100); //draw each player's score to the screen
		c.drawString (Integer.toString (player2Score), 755, 500);
		int x = 0, y = 0, x1 = 0, y1 = 0; //track the indexes of the two choices
		for (int i = 0 ; i < 2 ; i++) //loop 2 times as during each turn the user has to pick 2 different cards
		{
		    c.setFont (new Font ("Serif", Font.PLAIN, 15));
		    c.setColor (new Color (0, 0, 0));
		    c.drawString ("Player turn:", 725, 273);
		    c.drawString ("Player 1 Score:", 715, 73);
		    c.drawString ("Player 2 Score:", 715, 473);
		    c.setFont (new Font ("Serif", Font.PLAIN, 20));
		    c.setColor (new Color (255, 255, 102));
		    String line = "", sub = "";
		    int curx = 310, cury = 555;
		    if (i == 1)
		    {
			c.setColor (blue);
			c.fillRect (0, cury - 20, 400, 35);
			c.setColor (new Color (255, 255, 102));
			c.drawString ("Enter the other card you would like to flip to match graphics: ", 10, cury);
			curx = 500; //set the curx to right beside the sentence as it is different length from the first one
		    }
		    else
			c.drawString ("Enter the card you would like to flip:", 10, cury);
		    c.drawString ("Note: You can only type from 'a' to 'd' and from '1' to '4' and your limit is 2 characters.", 10, cury + 25);
		    c.drawString ("Press enter to confirm your card choice and backspace to delete a character.", 10, cury - 20);
		    while (true)
		    {
			char val = c.getChar (); //get input of each character
			c.setColor (new Color (255, 255, 102));
			if (line.length () < 2)
			{
			    if (((int) val >= 48 && (int) val <= 57) || ((int) val >= 65 && (int) val <= 90) || ((int) val >= 97 && (int) val <= 122))
			    { //if it is a valid character
				line += val; //line stores the line of data from the user(2 characters)
				c.drawString (Character.toString (val), curx, cury); //draw the string to the screen to act as input
				curx += 15; //increase the current x position by 15 so u can write next character
			    }
			}
			if (val == 8) //if character pressed is backspace(ascii)
			{
			    if (line.length () != 0) //if the length is not 0, otherwise nothign to print
			    {
				line = line.substring (0, line.length () - 1); //remove the last character by using substring
				c.setColor (blue); //set colour to blue
				c.fillRect (curx - 20, cury - 20, 20, 20); //draw over it so it is removed
				curx -= 15; //go back to the previous x position of a character
			    }
			}
			else if (val == '\n') //if equal to enter
			{
			    sub = line; //set substitute variable to the current line
			    c.setColor (blue);
			    if (i == 0) //if first turn
			    {
				c.fillRect (305, cury - 15, 40, 20); //cover input
				curx = 310; //reset the current x point so you can start from the beginning again
			    }
			    else
			    {
				c.fillRect (490, cury - 15, 40, 20); //clear input from the user
				curx = 500; //reset current x point
			    }
			    try
			    {
				int curh = Integer.parseInt (Character.toString (line.charAt (1))); //second character has to be a number so we can try to parse it, if not then there will be exception and we know that it is not valid coordinate
				int curr = convert (line.charAt (0)); //use the private return method to convert the character to a number that can be used as index
				if (matched [curr - 1] [curh - 1]) //if this card has been matched already
				{
				    throw new NullPointerException (); //throw random exception to be caught
				}
				if (curh != 1 && curh != 2 && curh != 3 && curh != 4)
				    throw new ArithmeticException (); //throw random exception

				if (((int) line.charAt (0) >= 65 && (int) line.charAt (0) <= 68) || ((int) line.charAt (0) >= 97 && (int) line.charAt (0) <= 100))
				{
				    //first character is fine
				}
				else
				    throw new IndexOutOfBoundsException (); //otherwise throw another exception
				if (i == 1) //if i==1 then it is the second card so we can reveal both
				{
				    if (sub.equals (firstChoice))
					throw new Exception (); //can't have the same two cards to errortrap that
				}
				else
				{
				    firstChoice = sub; //store the first card to the variable so it can be used to compare later
				}
				break; //if reached here, all input is valid
			    }
			    //catch all the previous exceptions that could have been thrown
			    catch (IndexOutOfBoundsException e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, the letter/first character of your point must be from 'A' to 'D'. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (NumberFormatException e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, your point must have a number as the second digit. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (ArithmeticException a)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, the number of your point must be between 1 to 4. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (NullPointerException n)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, that card has already been matched. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";

			    }
			    catch (Exception e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, please pick a different card! ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			}
		    } //proper input has been taken for that specific card coordinate
		    if (i == 1)
		    {
			x1 = convert (line.charAt (0)); //convert to usable indexes for the array
			y1 = Integer.parseInt (Character.toString (line.charAt (1)));
			c.setColor (white); //set color to white
			c.fillRoundRect ((y1 - 1) * 120 + 160, (x1 - 1) * 120 + 50, 110, 110, 30, 30); //"flip" the two cards
			c.fillRoundRect ((y - 1) * 120 + 160, (x - 1) * 120 + 50, 110, 110, 30, 30);
			drawGraphic (memoryCard [x - 1] [y - 1], (y - 1) * 120 + 180, (x - 1) * 120 + 65); //draw the actual graphics
			drawGraphic (memoryCard [x1 - 1] [y1 - 1], (y1 - 1) * 120 + 180, (x1 - 1) * 120 + 65);
			try
			{
			    Thread.sleep (1000); //delay for 1 second
			}
			catch (Exception e)
			{
			}
			c.setColor (grey);
			if (memoryCard [x1 - 1] [y1 - 1] == memoryCard [x - 1] [y - 1]) //if they are the same values, then they have been matched
			{
			    if (playerTurn % 2 == 0) //if it is player 2's turn
				player2Score++; //add 1 to player 2's score
			    else
				player1Score++; //otherwise add 1 to player 1's score
			    matched [x1 - 1] [y1 - 1] = true; //set both of them to true so they can't be flipped back or matched again
			    matched [x - 1] [y - 1] = true;
			}
			else //cover card since they aren't matching by drawing the same graphic as above
			{
			    c.setColor (grey);
			    c.fillRoundRect ((y1 - 1) * 120 + 160, (x1 - 1) * 120 + 50, 110, 110, 30, 30);
			    c.setColor (pink);
			    c.fillRoundRect ((y1 - 1) * 120 + 165, (x1 - 1) * 120 + 55, 100, 100, 30, 30);
			    c.setColor (white);
			    for (int k = 0 ; k < 30 ; k += 10)
			    {
				c.drawOval ((y1 - 1) * 120 + 165 + k, (x1 - 1) * 120 + 55 + k, 100 - k * 2, 100 - k * 2);
			    }
			    c.setColor (grey);
			    c.fillRoundRect ((y - 1) * 120 + 160, (x - 1) * 120 + 50, 110, 110, 30, 30);
			    c.setColor (pink);
			    c.fillRoundRect ((y - 1) * 120 + 165, (x - 1) * 120 + 55, 100, 100, 30, 30);
			    c.setColor (white);
			    for (int k = 0 ; k < 30 ; k += 10)
			    {
				c.drawOval ((y - 1) * 120 + 165 + k, (x - 1) * 120 + 55 + k, 100 - k * 2, 100 - k * 2);
			    }
			}
		    }
		    else
		    {
			x = convert (line.charAt (0)); //just convert only and store to the variable to be used later
			y = Integer.parseInt (Character.toString (line.charAt (1))); //cast to integer as well and store to y to be used once both cards are picked
		    }
		} //for loop is done, someone's turn has ended
		if (player1Score + player2Score == 8) //if the scores add up to 8 that means that all matches have been  made
		{
		    gameWon = true; //game has been won
		    break; //break out of the while loop so that game ends
		}
	    } //all possible matches have been made
	}
	else if (userDifficulty == 'm' || userDifficulty == 'M') //game difficulty has been picked as medium
	{
	    int max = 18, min = 1, range = max - min + 1, playerTurn = 0; //generate the random locations of the cards
	    memoryCard = new int [6] [6];
	    numbersPicked = new int [max];
	    matched = new boolean [6] [6]; //Represents which cards have already been matched
	    //we use a  nested for loop to put all the numbers into the 2d array, memoryCard
	    //This array will store all the locations so we can refer to it for future use. In easy mode, this will contain 2 of 8
	    //different cards, in total being 16 cards, which is a 4x4 square
	    //numbersPicked[] makes sure that if a card has already been put out there twice then it does not get put on another card.
	    for (int i = 0 ; i < 6 ; i++)
	    {
		for (int j = 0 ; j < 6 ; j++) //generate random placements of the numbers
		{
		    int val = (int) (Math.random () * range) + min; //generate random number
		    if (numbersPicked [val - 1] < 2) //if it hasn't been picked twice yet, store it into the place
		    {
			numbersPicked [val - 1]++;
			memoryCard [i] [j] = val;
		    }
		    else //otherwise, restart by subtracting j as it will be added again
			j--;
		}
	    }
	    //code to cheat the game
	    /*for (int k = 0 ; k < 6 ; k++)
	    {
		for (int j = 0 ; j < 6 ; j++)
		{
		    System.out.print (memoryCard [k] [j] + " ");

		}
		System.out.println ();
	    }*/
	    c.setFont (new Font ("Serial", Font.BOLD, 30));
	    for (int i = 0 ; i < 6 ; i++) //draw the back of every single card
	    {
		c.setColor (new Color (0, 0, 0));
		c.drawString (column [i], 50, i * 80 + 95);
		for (int j = 0 ; j < 6 ; j++)
		{
		    c.setColor (new Color (0, 0, 0));
		    c.drawString (row [j], j * 80 + 105, 40);
		    c.setColor (grey);
		    c.fillRoundRect (j * 80 + 80, i * 80 + 50, 70, 70, 30, 30);
		    c.setColor (pink);
		    int x = j * 80 + 82, y = i * 80 + 52;
		    c.fillRoundRect (x, y, 66, 66, 30, 30);
		    c.setColor (white);
		    for (int k = 0 ; k < 3 ; k++)
		    {
			c.drawOval (x + k, y + k, 66 - k * 2, 66 - k * 2);
		    }
		    c.setColor (white);
		    int[] xPoints = {x + 3, x + 32, x + 65, x + 32}, yPoints = {y + 32, y + 2, y + 32, y + 65};
		    c.fillPolygon (xPoints, yPoints, 4);
		    c.fillRect (x + 10, y + 10, 46, 46);
		}
	    }
	    String firstChoice = ""; //store the first input from the user
	    while (!gameWon) //while game has not ended yet
	    {
		c.setFont (new Font ("Serif", Font.PLAIN, 20));
		c.setColor (white);
		c.fillRect (697, 60, 130, 45); //Fill box over player 1 score
		c.fillRect (697, 260, 135, 45); //fill over player turn
		c.fillRect (697, 460, 130, 45); //fill over player 2 score
		c.setColor (blue);
		c.fillRect (0, 540, 600, 40); //Fill over bottom two lines of text so it can be written again
		c.setColor (new Color (0, 0, 0));
		playerTurn++;
		if (playerTurn % 2 == 1)
		{
		    c.drawString ("1", 755, 300);
		    c.setColor (red);
		    for (int i = 0 ; i < 10 ; i++)
		    {
			c.drawRect (695 + i, i + 250, 130, 55); //Box around player turn
		    }
		}
		else
		{
		    c.drawString ("2", 755, 300);
		    c.setColor (green);
		    for (int i = 0 ; i < 10 ; i++)
		    {
			c.drawRect (695 + i, i + 250, 130, 55); //Box around player turn
		    }
		}
		c.setColor (new Color (0, 0, 0));
		c.drawString (Integer.toString (player1Score), 755, 100); //display scores of both players
		c.drawString (Integer.toString (player2Score), 755, 500);
		int x = 0, y = 0, x1 = 0, y1 = 0;
		for (int i = 0 ; i < 2 ; i++)
		{
		    c.setFont (new Font ("Serif", Font.PLAIN, 15));
		    c.setColor (new Color (0, 0, 0));
		    c.drawString ("Player turn:", 725, 273);
		    c.drawString ("Player 1 Score:", 715, 73); //draw the text above the scores
		    c.drawString ("Player 2 Score:", 715, 473);
		    c.setFont (new Font ("Serif", Font.PLAIN, 20));
		    c.setColor (new Color (255, 255, 102));
		    String line = "", sub = ""; //line is used to store current input, sub stores it for the first card input
		    int curx = 310, cury = 555; //set curx and cury for drawing the input at that point
		    if (i == 1) //if first card has been taken already
		    {
			c.setColor (blue);
			c.fillRect (0, cury - 20, 400, 35);
			c.setColor (new Color (255, 255, 102));
			c.drawString ("Enter the other card you would like to flip to match graphics: ", 10, cury); //ask for second one
			curx = 500; //set cur to right beside the sentence as they are different lengths
		    }
		    else
			c.drawString ("Enter the card you would like to flip:", 10, cury); //otherwise, ask for first one
		    c.drawString ("Note: You can only type from 'a' to 'f' and from '1' to '6' and your limit is 2 characters.", 10, cury + 25);
		    c.drawString ("Press enter to confirm your card choice and backspace to delete a character.", 10, cury - 20);
		    while (true)
		    {
			char val = c.getChar (); //get input
			c.setColor (new Color (255, 255, 102));
			if (line.length () < 2)
			{
			    if (((int) val >= 48 && (int) val <= 57) || ((int) val >= 65 && (int) val <= 90) || ((int) val >= 97 && (int) val <= 122))
			    { //if input is a valid character
				line += val; //add the character to line
				c.drawString (Character.toString (val), curx, cury); //draw the string to the screen
				curx += 15; //add 15 so you can print the next char to that point
			    }
			}
			if (val == 8) //if it is equal to backspace
			{
			    if (line.length () != 0)
			    {
				line = line.substring (0, line.length () - 1); //delete the last character by using substring
				c.setColor (blue); //set color to blue
				c.fillRect (curx - 20, cury - 20, 20, 20); //use rect that is same color as background to cover it
				curx -= 15; //subtract 15 from the x so it can go back to previous character
			    }
			}
			else if (val == '\n') //equal to enter
			{
			    sub = line; //set sub to the line
			    c.setColor (blue); //set color to blue to clear the previous stuff
			    if (i == 0)
			    {
				c.fillRect (305, cury - 15, 40, 20);
				curx = 310;
			    }
			    else
			    {
				c.fillRect (490, cury - 15, 40, 20);
				curx = 500;
			    }
			    try
			    {
				int curh = Integer.parseInt (Character.toString (line.charAt (1))); //convert the x and y
				int curr = convert (line.charAt (0));
				if (matched [curr - 1] [curh - 1]) //if they have already been matched then throw an exception
				{
				    throw new NullPointerException (); //random exception thrown
				}
				if (curh != 1 && curh != 2 && curh != 3 && curh != 4 && curh != 5 && curh != 6)
				{
				    throw new ArithmeticException (); //throw random exception
				}
				if (((int) line.charAt (0) >= 65 && (int) line.charAt (0) <= 70) || ((int) line.charAt (0) >= 97 && (int) line.charAt (0) <= 102))
				{
				    //first character is fine
				}
				else
				    throw new IndexOutOfBoundsException (); //otherwise, it is not fine and throw and exception
				if (i == 1)
				{
				    if (sub.equals (firstChoice))
					throw new Exception ();
				    else
					break;
				}
				else
				{
				    firstChoice = sub;
				    break;
				}
			    }
			    //catch exceptions
			    catch (IndexOutOfBoundsException e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, the letter/first character of your point must be from 'A' to 'F'. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (NumberFormatException e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, your point must have a number as the second digit. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (ArithmeticException a)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, the number of your point must be between 1 to 6. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (NullPointerException n)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, that card has already been matched. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";

			    }
			    catch (Exception e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, please pick a different card! ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			}
		    } //proper input has been taken for that specific card coordinate
		    if (i == 1)
		    {
			x1 = convert (line.charAt (0));
			y1 = Integer.parseInt (Character.toString (line.charAt (1))); //convert both for the second card
			c.setColor (white);
			c.fillRoundRect ((y1 - 1) * 80 + 80, (x1 - 1) * 80 + 50, 70, 70, 30, 30); //flip the cards
			c.fillRoundRect ((y - 1) * 80 + 80, (x - 1) * 80 + 50, 70, 70, 30, 30);
			c.setColor (blue);
			drawGraphic (memoryCard [x1 - 1] [y1 - 1], (y1 - 1) * 80 + 95, (x1 - 1) * 80 + 55); //draw the corresponding graphic
			drawGraphic (memoryCard [x - 1] [y - 1], (y - 1) * 80 + 95, (x - 1) * 80 + 55);
			try
			{
			    Thread.sleep (1000); //pause for 1 second
			}
			catch (Exception e)
			{
			}
			c.setColor (grey);
			if (memoryCard [x1 - 1] [y1 - 1] == memoryCard [x - 1] [y - 1]) //if they are matching
			{
			    if (playerTurn % 2 == 0) //add score to whoever's turn it was
				player2Score++;
			    else
				player1Score++;
			    matched [x1 - 1] [y1 - 1] = true; //set both cards to true, meaning they have been matched
			    matched [x - 1] [y - 1] = true;
			}
			else //re-cover the cards
			{
			    c.setColor (grey);
			    c.fillRoundRect ((y1 - 1) * 80 + 80, (x1 - 1) * 80 + 50, 70, 70, 30, 30);
			    c.setColor (pink);
			    int x2 = (y1 - 1) * 80 + 82, y2 = (x1 - 1) * 80 + 52;
			    c.fillRoundRect (x2, y2, 66, 66, 30, 30);
			    c.setColor (white);
			    for (int k = 0 ; k < 3 ; k++)
			    {
				c.drawOval (x2 + k, y2 + k, 66 - k * 2, 66 - k * 2);
			    }
			    c.setColor (white);
			    int[] xPoints = {x2 + 3, x2 + 32, x2 + 65, x2 + 32}, yPoints = {y2 + 32, y2 + 2, y2 + 32, y2 + 65};
			    c.fillPolygon (xPoints, yPoints, 4);
			    c.fillRect (x2 + 10, y2 + 10, 46, 46);
			    c.setColor (grey);
			    c.fillRoundRect ((y - 1) * 80 + 80, (x - 1) * 80 + 50, 70, 70, 30, 30);
			    c.setColor (pink);
			    x2 = (y - 1) * 80 + 82;
			    y2 = (x - 1) * 80 + 52;
			    c.fillRoundRect (x2, y2, 66, 66, 30, 30);
			    c.setColor (white);
			    for (int k = 0 ; k < 3 ; k++)
			    {
				c.drawOval (x2 + k, y2 + k, 66 - k * 2, 66 - k * 2);
			    }
			    c.setColor (white);
			    int[] xPoints1 = {x2 + 3, x2 + 32, x2 + 65, x2 + 32}, yPoints1 = {y2 + 32, y2 + 2, y2 + 32, y2 + 65};
			    c.fillPolygon (xPoints1, yPoints1, 4);
			    c.fillRect (x2 + 10, y2 + 10, 46, 46);
			}
		    }
		    else
		    {
			//convert the first point
			x = convert (line.charAt (0));
			y = Integer.parseInt (Character.toString (line.charAt (1)));
		    }
		} //for loop is done, someone's turn has ended
		if (player1Score + player2Score == 18)
		{
		    gameWon = true;
		    break;
		}
	    } //all possible matches have been made
	}
	else
	{
	    int max = 32, min = 1, range = max - min + 1, playerTurn = 0; //generate the random locations of the cards
	    memoryCard = new int [8] [8];
	    numbersPicked = new int [max];
	    matched = new boolean [8] [8]; //Represents which cards have already been matched
	    //we use a  nested for loop to put all the numbers into the 2d array, memoryCard
	    //This array will store all the locations so we can refer to it for future use. In easy mode, this will contain 2 of 8
	    //different cards, in total being 16 cards, which is a 4x4 square
	    //numbersPicked[] makes sure that if a card has already been put out there twice then it does not get put on another card.
	    for (int i = 0 ; i < 8 ; i++) //use nested for loop to generate all of it
	    {
		for (int j = 0 ; j < 8 ; j++)
		{
		    int val = (int) (Math.random () * range) + min; //generate a value
		    if (numbersPicked [val - 1] < 2) //check if the value can be used or not
		    {
			numbersPicked [val - 1]++; //if it can then add it by 1 as it has been used one more time
			memoryCard [i] [j] = val; //set the current index to that value/that specific graphic
		    }
		    else
			j--; //restart the index if the card was not picked properly
		}
	    }
	    //code to cheat the game

	    /*for (int k = 0 ; k < 8 ; k++)
	    {
		for (int j = 0 ; j < 8 ; j++)
		{
		    System.out.print (memoryCard [k] [j] + " ");
		}
		System.out.println ();
	    }*/
	    //drawing out the cards
	    c.setFont (new Font ("Serial", Font.BOLD, 30));
	    for (int i = 0 ; i < 8 ; i++)
	    {
		c.setColor (new Color (0, 0, 0));
		c.drawString (column [i], 50, i * 62 + 85); //draw the row
		for (int j = 0 ; j < 8 ; j++) //draw the back of the card
		{
		    c.setColor (new Color (0, 0, 0));
		    c.drawString (row [j], j * 62 + 85, 45); //draw the column labels
		    c.setColor (grey);
		    c.fillRoundRect (j * 62 + 75, i * 62 + 50, 52, 52, 20, 20);
		    int x = j * 62, y = i * 62;
		    c.setColor (new Color (34, 139, 34));
		    c.fillOval (x + 80, y + 50, 15, 15);
		    c.fillOval (x + 80, y + 85, 15, 15);
		    c.fillOval (x + 105, y + 50, 15, 15);
		    c.fillOval (x + 105, y + 85, 15, 15);
		    c.setColor (red);
		    int[] xPoints = {x + 100, x + 90, x + 110}, yPoints = {y + 63, y + 83, y + 83};
		    c.fillPolygon (xPoints, yPoints, 3);
		}
	    }
	    String firstChoice = ""; //first choice of card from the player
	    while (!gameWon)
	    {
		c.setFont (new Font ("Serif", Font.PLAIN, 20));
		c.setColor (white);
		c.fillRect (697, 60, 130, 45); //Fill box over player 1 score
		c.fillRect (697, 260, 135, 45); //fill over player turn
		c.fillRect (697, 460, 130, 45); //fill over player 2 score
		c.setColor (blue);
		c.fillRect (0, 540, 600, 40); //Fill over bottom two lines of text so it can be written again
		c.setColor (new Color (0, 0, 0));
		playerTurn++;
		if (playerTurn % 2 == 1)
		{
		    c.drawString ("1", 755, 300); //display who's turn it is
		    c.setColor (red);
		    for (int i = 0 ; i < 10 ; i++)
		    {
			c.drawRect (695 + i, i + 250, 130, 55); //Box around player 1 turn
		    }
		}
		else
		{
		    c.drawString ("2", 755, 300);
		    c.setColor (green);
		    for (int i = 0 ; i < 10 ; i++)
		    {
			c.drawRect (695 + i, i + 250, 130, 55); //Box around player 2 turn
		    }
		}
		c.setColor (new Color (0, 0, 0));
		c.drawString (Integer.toString (player1Score), 755, 100); //display the two scores of the players
		c.drawString (Integer.toString (player2Score), 755, 500);
		int x = 0, y = 0, x1 = 0, y1 = 0;
		for (int i = 0 ; i < 2 ; i++) //two cards to flip by each player
		{
		    c.setFont (new Font ("Serif", Font.PLAIN, 15));
		    c.setColor (new Color (0, 0, 0));
		    c.drawString ("Player turn:", 725, 273);
		    c.drawString ("Player 1 Score:", 715, 73); //labels in the boxes drawn
		    c.drawString ("Player 2 Score:", 715, 473);
		    c.setFont (new Font ("Serif", Font.PLAIN, 20));
		    c.setColor (new Color (255, 255, 102));
		    String line = "", sub = ""; //line can store the current input, sub stores it for future use
		    int curx = 310, cury = 555; //set variables so the input from the user can be drawn at that poing
		    if (i == 1) //ask for the second card
		    {
			c.setColor (blue);
			c.fillRect (0, cury - 20, 400, 35);
			c.setColor (new Color (255, 255, 102));
			c.drawString ("Enter the other card you would like to flip to match graphics: ", 10, cury+15);
			curx = 500;
		    }
		    else
			c.drawString ("Enter the card you would like to flip:", 10, cury+15);
		    c.drawString ("Note: You can only type from 'a' to 'h' and from '1' to '8' and your limit is 2 characters.", 10, cury + 40);
		    c.drawString ("Press enter to confirm your card choice and backspace to delete a character.", 10, cury - 5);
		    while (true)
		    {
			char val = c.getChar (); //take in input
			c.setColor (new Color (255, 255, 102));
			if (line.length () < 2)
			{
			    if (((int) val >= 48 && (int) val <= 57) || ((int) val >= 65 && (int) val <= 90) || ((int) val >= 97 && (int) val <= 122))
			    { //valid input or not
				line += val; //add it to line variable
				c.drawString (Character.toString (val), curx, cury+15); //draw it to screen
				curx += 15; //prepare for next character
			    }
			}
			if (val == 8) //backspace
			{
			    if (line.length () != 0) //if there is an existing character
			    {
				line = line.substring (0, line.length () - 1); //delete the most recent one, one at the very back
				c.setColor (blue);
				c.fillRect (curx - 20, cury - 5, 20, 20); //cover the character with same colour as background
				curx -= 15; //go back to position of character before
			    }
			}
			else if (val == '\n') //enter key
			{
			    sub = line; //set substitute variable to the line
			    c.setColor (blue); //set color to background color
			    if (i == 0) //first input
			    {
				c.fillRect (305, cury - 15, 40, 20); //cover everything
				curx = 310; //default curx value
			    }
			    else //preparing for second input
			    {
				c.fillRect (490, cury, 40, 20);
				curx = 500;
			    }
			    try
			    {
				int curh = Integer.parseInt (Character.toString (line.charAt (1))); //convert the two characters given by the user
				int curr = convert (line.charAt (0)); //use private return method
				if (matched [curr - 1] [curh - 1]) //if the two cards are matched already
				{
				    throw new NullPointerException (); //throw an exception
				}
				if (curh != 1 && curh != 2 && curh != 3 && curh != 4 && curh != 5 && curh != 6 && curh != 7 && curh != 8) //if number is not equal to any of those values then it is not on the board
				{
				    throw new ArithmeticException (); //throw random exception
				}
				if (((int) line.charAt (0) >= 65 && (int) line.charAt (0) <= 72) || ((int) line.charAt (0) >= 97 && (int) line.charAt (0) <= 104))
				{
				    //first character is fine
				}
				else
				    throw new IndexOutOfBoundsException (); //first character is not fine
				if (i == 1)
				{
				    if (sub.equals (firstChoice))
					throw new Exception ();
				    else
					break;
				}
				else
				{
				    firstChoice = sub;
				    break;
				}
			    }
			    //catch the exceptions
			    catch (IndexOutOfBoundsException e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, the letter/first character of your point must be from 'A' to 'H'. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (NumberFormatException e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, your point must have a number as the second digit. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (ArithmeticException a)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, the number of your point must be between 1 to 8. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			    catch (NullPointerException n)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, that card has already been matched. ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";

			    }
			    catch (Exception e)
			    {
				JOptionPane.showMessageDialog (null, "Sorry, please pick a different card! ", "Error", JOptionPane.ERROR_MESSAGE);
				line = "";
			    }
			}
		    } //proper input has been taken for that specific card coordinate
		    if (i == 1)
		    {
			x1 = convert (line.charAt (0)); //convert the two characters to indexes that can be used in the arrays
			y1 = Integer.parseInt (Character.toString (line.charAt (1)));
			c.setColor (white);
			c.fillRoundRect ((y1 - 1) * 62 + 75, (x1 - 1) * 62 + 50, 52, 52, 20, 20); //"flip" the two cards
			c.fillRoundRect ((y - 1) * 62 + 75, (x - 1) * 62 + 50, 52, 52, 20, 20);
			drawGraphic (memoryCard [x - 1] [y - 1], (y - 1) * 62 + 85, (x - 1) * 62 + 55); //draw the specific graphics of those two cards
			drawGraphic (memoryCard [x1 - 1] [y1 - 1], (y1 - 1) * 62 + 85, (x1 - 1) * 62 + 55);
			try
			{
			    Thread.sleep (1000); //wait 1 second before reflipping
			}
			catch (Exception e)
			{
			}
			c.setColor (grey);
			if (memoryCard [x1 - 1] [y1 - 1] == memoryCard [x - 1] [y - 1]) //if the two cards match
			{
			    if (playerTurn % 2 == 0) //add points to whoever's turn it is
				player2Score++;
			    else
				player1Score++;
			    matched [x1 - 1] [y1 - 1] = true; //these two cards have been matched so set it to true
			    matched [x - 1] [y - 1] = true;
			}
			else //cover the card with the original back of the card
			{
			    int x2 = (y1 - 1) * 62, y2 = (x1 - 1) * 62;
			    c.setColor (grey);
			    c.fillRoundRect (x2 + 75, y2 + 50, 52, 52, 20, 20);
			    c.setColor (new Color (34, 139, 34));
			    c.fillOval (x2 + 80, y2 + 50, 15, 15);
			    c.fillOval (x2 + 80, y2 + 85, 15, 15);
			    c.fillOval (x2 + 105, y2 + 50, 15, 15);
			    c.fillOval (x2 + 105, y2 + 85, 15, 15);
			    c.setColor (red);
			    int[] xPoints = {x2 + 100, x2 + 90, x2 + 110}, yPoints = {y2 + 63, y2 + 83, y2 + 83};
			    c.fillPolygon (xPoints, yPoints, 3);
			    x2 = (y - 1) * 62;
			    y2 = (x - 1) * 62;
			    c.setColor (grey);
			    c.fillRoundRect (x2 + 75, y2 + 50, 52, 52, 20, 20);
			    c.setColor (new Color (34, 139, 34));
			    c.fillOval (x2 + 80, y2 + 50, 15, 15);
			    c.fillOval (x2 + 80, y2 + 85, 15, 15);
			    c.fillOval (x2 + 105, y2 + 50, 15, 15);
			    c.fillOval (x2 + 105, y2 + 85, 15, 15);
			    c.setColor (red);
			    int[] xPoints1 = {x2 + 100, x2 + 90, x2 + 110}, yPoints1 = {y2 + 63, y2 + 83, y2 + 83};
			    c.fillPolygon (xPoints, yPoints, 3);
			    c.fillPolygon (xPoints1, yPoints1, 3);
			}
		    }
		    else
		    {
			//convert the first point
			x = convert (line.charAt (0));
			y = Integer.parseInt (Character.toString (line.charAt (1)));
		    }
		} //for loop is done, someone's turn has ended
		if (player1Score + player2Score == 32)
		{
		    gameWon = true;
		    break;
		}
	    } //all possible matches have been made
	}
	winScreen (); //someone has won so go to the winscreen
    }


    /*Private void  method to draw the corresponding graphic depending on the given number in the parameter(cur)
    Variable Dictionary
    NAME                TYPE                        DESCRIPTION
    cur                 int                         The variable of which graphic to draw
    x                   int                         the x position of the graphic
    y                   int                         the y position of the variable
    ratio               double                      keeps track of the size of the graphic
    black               Color                       Stores the color black for efficency

    All if statements are used to check which number cur is so that the right graphic can be drawn, other than the first 3 which are used to make
    sure that the ratio is the right size depending on the difficulty
    */
    private void drawGraphic (int cur, int x, int y)
    {
	Color black = new Color (0, 0, 0); //black is used a lot so i made a variable in it
	double ratio = 1.0; //if on easy mode, then ratio is just 1
	if (userDifficulty == 'M' || userDifficulty == 'm') //otherwise, ratio is a decimal and it can be smaller
	    ratio = 0.625;
	else if (userDifficulty == 'H' || userDifficulty == 'h')
	    ratio = 0.4625;
	//because hsa does not accept doubles as parameters i will round and then cast to an integer so it works
	if (cur == 1) //orange popsicle
	{
	    c.setColor (new Color (255, 140, 0));
	    c.fillRect (x + (int) Math.round (10 * ratio), y, (int) Math.round (45 * ratio), (int) Math.round (65 * ratio));
	    c.setColor (black);
	    c.drawLine (x + (int) Math.round (33 * ratio), y + (int) Math.round (65 * ratio), x + (int) Math.round (33 * ratio), y + (int) Math.round (90 * ratio));
	}
	else if (cur == 2) //red candy
	{
	    c.setColor (new Color (255, 0, 0));
	    int[] xPoints = {x - (int) Math.round (5 * ratio), x + (int) Math.round (25 * ratio), x - (int) Math.round (5 * ratio) };
	    int[] yPoints = {y + (int) Math.round (20 * ratio), y + (int) Math.round (35 * ratio), y + (int) Math.round (50 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.fillOval (x + (int) Math.round (15 * ratio), y + (int) Math.round (15 * ratio), (int) Math.round (40 * ratio), (int) Math.round (40 * ratio));
	    int[] xPoints1 = {x + (int) Math.round (75 * ratio), x + (int) Math.round (45 * ratio), x + (int) Math.round (75 * ratio) };
	    c.fillPolygon (xPoints1, yPoints, 3);
	}
	else if (cur == 3) //cup of water
	{
	    c.setColor (new Color (173, 216, 230));
	    c.fillRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.setColor (black);
	    c.drawRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (10 * ratio), y);
	    c.drawLine (x + (int) Math.round (60 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), y);
	}
	else if (cur == 4)
	{ //donut
	    c.setColor (new Color (255, 105, 180));
	    c.fillOval (x - (int) Math.round (5 * ratio), y, (int) Math.round (80 * ratio), (int) Math.round (80 * ratio));
	    c.setColor (new Color (255, 255, 255));
	    c.fillOval (x + (int) Math.round (20 * ratio), y + (int) Math.round (25 * ratio), (int) Math.round (30 * ratio), (int) Math.round (30 * ratio));
	}
	else if (cur == 5)
	{ //egg
	    c.setColor (black);
	    c.drawOval (x - (int) Math.round (10 * ratio), y, (int) Math.round (90 * ratio), (int) Math.round (70 * ratio));
	    c.drawOval (x + (int) Math.round (15 * ratio) - 1, y + (int) Math.round (15 * ratio) - 1, (int) Math.round (40 * ratio) + 1, (int) Math.round (40 * ratio) + 1);
	    c.setColor (new Color (255, 255, 0));
	    c.fillOval (x + (int) Math.round (15 * ratio), y + (int) Math.round (15 * ratio), (int) Math.round (40 * ratio), (int) Math.round (40 * ratio));
	}
	else if (cur == 6)
	{ //orange
	    c.setColor (new Color (255, 140, 0));
	    c.fillOval (x, y, (int) Math.round (80 * ratio), (int) Math.round (80 * ratio));
	    c.setColor (black);
	    c.fillOval (x + (int) Math.round (50 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (7 * ratio), (int) Math.round (7 * ratio));
	}
	else if (cur == 7)
	{ //mushroom
	    c.setColor (new Color (222, 184, 135));
	    c.fillArc (x, y - (int) Math.round (5 * ratio), (int) Math.round (70 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.setColor (new Color (245, 222, 179));
	    c.fillRect (x + (int) Math.round (25 * ratio), y + (int) Math.round (20 * ratio), (int) Math.round (20 * ratio), (int) Math.round (50 * ratio));
	}
	else if (cur == 8)
	{ //red apple
	    if (userDifficulty == 'm' || userDifficulty == 'M')
		x += 5;
	    c.setColor (new Color (255, 0, 0));
	    c.fillOval (x - (int) Math.round (5 * ratio), y + (int) Math.round (5 * ratio), (int) Math.round (80 * ratio), (int) Math.round (80 * ratio));
	    c.setColor (black);
	    c.drawArc (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (20 * ratio), 225, 90);
	    c.drawArc (x + (int) Math.round (30 * ratio), y, (int) Math.round (20 * ratio), (int) Math.round (35 * ratio), 45, 180);
	}
	else if (cur == 9)
	{ //watermelon
	    c.setColor (new Color (0, 255, 0));
	    c.fillArc (x, y, (int) Math.round (84 * ratio), (int) Math.round (68 * ratio), 180, 180);
	    c.setColor (new Color (255, 0, 0));
	    c.fillArc (x + (int) Math.round (8 * ratio), y+(int)Math.round(8*ratio), (int) Math.round (68 * ratio), (int) Math.round (52 * ratio), 180, 180);
	}
	else if (cur == 10)
	{ //muffin
	    c.setColor (new Color (222, 184, 135));
	    c.fillArc (x, y + (int) Math.round (5 * ratio), (int) Math.round (60 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.setColor (new Color (255, 192, 203));
	    int[] xPoints = {x, x + (int) Math.round (60 * ratio), x + (int) Math.round (50 * ratio), x + (int) Math.round (10 * ratio) };
	    int[] yPoints = {y + (int) Math.round (25 * ratio), y + (int) Math.round (25 * ratio), y + (int) Math.round (55 * ratio), y + (int) Math.round (55 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 4);
	    c.setColor (black);
	    c.drawPolygon (xPoints, yPoints, 4);
	    c.drawLine (x + (int) Math.round (15 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (15 * ratio), y + (int) Math.round (55 * ratio));
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (30 * ratio), y + (int) Math.round (55 * ratio));
	    c.drawLine (x + (int) Math.round (45 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (45 * ratio), y + (int) Math.round (55 * ratio));
	}
	else if (cur == 11)
	{ //waffle
	    c.setColor (new Color (210, 105, 30));
	    c.fillOval (x, y, (int) Math.round (60 * ratio), (int) Math.round (60 * ratio));
	    c.drawOval (x - 1, y - 1, (int) Math.round (60 * ratio), (int) Math.round (60 * ratio));
	    c.setColor (black);
	    c.drawLine (x + (int) Math.round (50 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (50 * ratio), y + (int) Math.round (50 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (10 * ratio), y + (int) Math.round (50 * ratio));
	    c.drawLine (x + (int) Math.round (30 * ratio), y, x + (int) Math.round (30 * ratio), y + (int) Math.round (60 * ratio));
	    c.drawLine (x, y + (int) Math.round (30 * ratio), x + (int) Math.round (60 * ratio), y + (int) Math.round (30 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (50 * ratio), y + (int) Math.round (10 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (50 * ratio), x + (int) Math.round (50 * ratio), y + (int) Math.round (50 * ratio));
	}
	else if (cur == 12)
	{ //hamburger
	    c.setColor (new Color (245, 222, 179));
	    c.fillArc (x, y, (int) Math.round (60 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.fillArc (x, y + (int) Math.round (15 * ratio), (int) Math.round (60 * ratio), (int) Math.round (50 * ratio), 180, 180);
	    c.setColor (new Color (0, 255, 0));
	    c.fillRect (x, y + (int) Math.round (25 * ratio), (int) Math.round (60 * ratio), (int) Math.round (5 * ratio));
	    c.setColor (new Color (139, 69, 19));
	    c.fillRect (x, y + (int) Math.round (30 * ratio), (int) Math.round (60 * ratio), (int) Math.round (10 * ratio));
	}
	else if (cur == 13)
	{ //chocolate ice cream
	    c.setColor (new Color (141, 85, 49));
	    c.fillArc (x + (int) Math.round (10 * ratio), y - (int) Math.round (5 * ratio), (int) Math.round (50 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.fillRoundRect (x, y + (int) Math.round (15 * ratio), (int) Math.round (70 * ratio), (int) Math.round (20 * ratio), 20, 20);
	    c.setColor (new Color (222, 184, 135));
	    int[] xPoints = {x + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), x + (int) Math.round (35 * ratio) };
	    int[] yPoints = {y + (int) Math.round (30 * ratio), y + (int) Math.round (30 * ratio), y + (int) Math.round (70 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	}
	else if (cur == 14)
	{ //carrot
	    int[] xPoints = {x + (int) Math.round (10 * ratio), x + (int) Math.round (50 * ratio), x + (int) Math.round (30 * ratio) };
	    int[] yPoints = {y + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), y + (int) Math.round (70 * ratio) };
	    c.setColor (new Color (237, 145, 33));
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.setColor (black);
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (30 * ratio), y);
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (25 * ratio), y);
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (35 * ratio), y);
	}
	else if (cur == 15)
	{ //strawberry
	    c.setColor (new Color (255, 0, 0));
	    int[] xPoints = {x, x + (int) Math.round (60 * ratio), x + (int) Math.round (30 * ratio) };
	    int[] yPoints = {y + (int) Math.round (20 * ratio), y + (int) Math.round (20 * ratio), y + (int) Math.round (70 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.fillArc (x, y + (int) Math.round (5 * ratio), (int) Math.round (60 * ratio), (int) Math.round (40 * ratio), 0, 180);
	    c.setColor (black);
	    c.drawArc (x, y + (int) Math.round (5 * ratio), (int) Math.round (60 * ratio), (int) Math.round (40 * ratio), 0, 180);
	    c.drawPolygon (xPoints, yPoints, 3);
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (5 * ratio), x + (int) Math.round (30 * ratio), y - (int) Math.round (5 * ratio));
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (5 * ratio), x + (int) Math.round (25 * ratio), y - (int) Math.round (5 * ratio));
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (5 * ratio), x + (int) Math.round (35 * ratio), y - (int) Math.round (5 * ratio));
	    c.setColor (new Color (255, 0, 0));
	    c.drawLine (x, y + (int) Math.round (20 * ratio), x + (int) Math.round (60 * ratio), y + (int) Math.round (20 * ratio));
	}
	else if (cur == 16)
	{ //sandwich
	    c.setColor (new Color (245, 222, 179));
	    int[] xPoints = {x, x + (int) Math.round (60 * ratio), x + (int) Math.round (30 * ratio) };
	    int[] yPoints = {y + (int) Math.round (35 * ratio), y + (int) Math.round (35 * ratio), y};
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.fillRect (x, y + (int) Math.round (35 * ratio), (int) Math.round (60 * ratio), (int) Math.round (10 * ratio));
	    c.fillRect (x, y + (int) Math.round (55 * ratio), (int) Math.round (60 * ratio), (int) Math.round (10 * ratio));
	    c.setColor (new Color (255, 182, 193));
	    c.fillRect (x, y + (int) Math.round (45 * ratio), (int) Math.round (60 * ratio), (int) Math.round (10 * ratio));
	}
	else if (cur == 17)
	{ //cheese
	    c.setColor (new Color (255, 166, 0));
	    int[] xPoints = {x, x + (int) Math.round (60 * ratio), x + (int) Math.round (45 * ratio) };
	    int[] yPoints = {y + (int) Math.round (30 * ratio), y + (int) Math.round (30 * ratio), y - (int) Math.round (5 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.setColor (new Color (255, 200, 0));
	    c.fillRect (x, y + (int) Math.round (30 * ratio), (int) Math.round (60 * ratio), (int) Math.round (30 * ratio));
	    c.setColor (black);
	    c.drawPolygon (xPoints, yPoints, 3);
	    c.drawRect (x, y + (int) Math.round (30 * ratio), (int) Math.round (60 * ratio), (int) Math.round (30 * ratio));
	    //c.setColor(new Color(
	}
	else if (cur == 18)
	{ //vanilla ice cream
	    c.setColor (black);
	    c.drawArc (x + (int) Math.round (10 * ratio), y - (int) Math.round (5 * ratio), (int) Math.round (50 * ratio), (int) Math.round (45 * ratio), 10, 175);
	    c.drawRoundRect (x, y + (int) Math.round (15 * ratio), (int) Math.round (70 * ratio), (int) Math.round (20 * ratio), 20, 20);
	    c.setColor (new Color (222, 184, 135));
	    int[] xPoints = {x + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), x + (int) Math.round (35 * ratio) };
	    int[] yPoints = {y + (int) Math.round (30 * ratio), y + (int) Math.round (30 * ratio), y + (int) Math.round (70 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.setColor (new Color (255, 255, 255));
	    c.drawLine (x, y + (int) Math.round (15 * ratio), x + (int) Math.round (60 * ratio), y + (int) Math.round (15 * ratio));
	}
	else if (cur == 19)
	{ //strawberry ice cream
	    c.setColor (new Color (255, 192, 203));
	    c.fillArc (x + (int) Math.round (10 * ratio), y - (int) Math.round (5 * ratio), (int) Math.round (50 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.fillRoundRect (x, y + (int) Math.round (15 * ratio), (int) Math.round (70 * ratio), (int) Math.round (20 * ratio), 20, 20);
	    c.setColor (new Color (222, 184, 135));
	    int[] xPoints = {x + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), x + (int) Math.round (35 * ratio) };
	    int[] yPoints = {y + (int) Math.round (30 * ratio), y + (int) Math.round (30 * ratio), y + (int) Math.round (70 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	}
	else if (cur == 20)
	{ //green apple
	    c.setColor (new Color (154, 205, 50));
	    c.fillOval (x - (int) Math.round (5 * ratio), y + (int) Math.round (5 * ratio), (int) Math.round (80 * ratio), (int) Math.round (80 * ratio));
	    c.setColor (black);
	    c.drawArc (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (20 * ratio), 225, 90);
	    c.drawArc (x + (int) Math.round (30 * ratio), y, (int) Math.round (20 * ratio), (int) Math.round (35 * ratio), 45, 180);
	}
	else if (cur == 21) //chocolate popsicle
	{
	    c.setColor (new Color (160, 82, 45));
	    c.fillRect (x + 10, y, (int) Math.round (25 * ratio), (int) Math.round (45 * ratio));
	    c.setColor (black);
	    c.drawLine (x + 10 + (int) Math.round (25 * ratio / 2), y + (int) Math.round (45 * ratio), x + 10 + (int) Math.round (25 * ratio / 2), y + (int) Math.round (45 * ratio) + 15);
	}
	else if (cur == 22) //strawberry popsicle
	{
	    c.setColor (new Color (238, 130, 238));
	    c.fillRect (x + (int) Math.round (10 * ratio), y, (int) Math.round (45 * ratio), (int) Math.round (65 * ratio));
	    c.setColor (black);
	    c.drawLine (x + (int) Math.round (33 * ratio), y + (int) Math.round (65 * ratio), x + (int) Math.round (33 * ratio), y + (int) Math.round (90 * ratio));
	}
	else if (cur == 23) //green candy
	{
	    c.setColor (new Color (0, 128, 0));
	    int[] xPoints = {x - (int) Math.round (5 * ratio), x + (int) Math.round (25 * ratio), x - (int) Math.round (5 * ratio) };
	    int[] yPoints = {y + (int) Math.round (20 * ratio), y + (int) Math.round (35 * ratio), y + (int) Math.round (50 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.fillOval (x + (int) Math.round (15 * ratio), y + (int) Math.round (15 * ratio), (int) Math.round (40 * ratio), (int) Math.round (40 * ratio));
	    int[] xPoints1 = {x + (int) Math.round (75 * ratio), x + (int) Math.round (45 * ratio), x + (int) Math.round (75 * ratio) };
	    c.fillPolygon (xPoints1, yPoints, 3);
	}
	else if (cur == 24) //pink candy
	{
	    c.setColor (new Color (255, 192, 203));
	    int[] xPoints = {x - (int) Math.round (5 * ratio), x + (int) Math.round (25 * ratio), x - (int) Math.round (5 * ratio) };
	    int[] yPoints = {y + (int) Math.round (20 * ratio), y + (int) Math.round (35 * ratio), y + (int) Math.round (50 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.fillOval (x + (int) Math.round (15 * ratio), y + (int) Math.round (15 * ratio), (int) Math.round (40 * ratio), (int) Math.round (40 * ratio));
	    int[] xPoints1 = {x + (int) Math.round (75 * ratio), x + (int) Math.round (45 * ratio), x + (int) Math.round (75 * ratio) };
	    c.fillPolygon (xPoints1, yPoints, 3);
	}
	else if (cur == 25) //orange candy
	{
	    c.setColor (new Color (255, 165, 0));
	    int[] xPoints = {x - (int) Math.round (5 * ratio), x + (int) Math.round (25 * ratio), x - (int) Math.round (5 * ratio) };
	    int[] yPoints = {y + (int) Math.round (20 * ratio), y + (int) Math.round (35 * ratio), y + (int) Math.round (50 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 3);
	    c.fillOval (x + (int) Math.round (15 * ratio), y + (int) Math.round (15 * ratio), (int) Math.round (40 * ratio), (int) Math.round (40 * ratio));
	    int[] xPoints1 = {x + (int) Math.round (75 * ratio), x + (int) Math.round (45 * ratio), x + (int) Math.round (75 * ratio) };
	    c.fillPolygon (xPoints1, yPoints, 3);
	}
	else if (cur == 26) //wine
	{
	    c.setColor (new Color (139, 0, 139));
	    c.fillRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.setColor (black);
	    c.drawRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (10 * ratio), y);
	    c.drawLine (x + (int) Math.round (60 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), y);
	}
	else if (cur == 27) //cup of coffee
	{
	    c.setColor (new Color (205, 133, 63));
	    c.fillRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.setColor (black);
	    c.drawRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (10 * ratio), y);
	    c.drawLine (x + (int) Math.round (60 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), y);
	}
	else if (cur == 28) //cup of orange juice
	{
	    c.setColor (new Color (255, 140, 0));
	    c.fillRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.setColor (black);
	    c.drawRect (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), (int) Math.round (50 * ratio), (int) Math.round (60 * ratio));
	    c.drawLine (x + (int) Math.round (10 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (10 * ratio), y);
	    c.drawLine (x + (int) Math.round (60 * ratio), y + (int) Math.round (10 * ratio), x + (int) Math.round (60 * ratio), y);
	}
	else if (cur == 29)
	{ //powdered donut
	    c.setColor (black);
	    c.drawOval (x - (int) Math.round (5 * ratio), y, (int) Math.round (80 * ratio), (int) Math.round (80 * ratio));
	    c.drawOval (x + (int) Math.round (20 * ratio), y + (int) Math.round (25 * ratio), (int) Math.round (30 * ratio), (int) Math.round (30 * ratio));
	}
	else if (cur == 30)
	{ //chocolate donut
	    c.setColor (new Color (139, 69, 19));
	    c.fillOval (x - (int) Math.round (5 * ratio), y, (int) Math.round (80 * ratio), (int) Math.round (80 * ratio));
	    c.setColor (new Color (255, 255, 255));
	    c.fillOval (x + (int) Math.round (20 * ratio), y + (int) Math.round (25 * ratio), (int) Math.round (30 * ratio), (int) Math.round (30 * ratio));
	}
	else if (cur == 31)
	{ //muffin(light yellow)
	    c.setColor (new Color (222, 184, 135));
	    c.fillArc (x, y + (int) Math.round (5 * ratio), (int) Math.round (60 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.setColor (new Color (255, 255, 153));
	    int[] xPoints = {x, x + (int) Math.round (60 * ratio), x + (int) Math.round (50 * ratio), x + (int) Math.round (10 * ratio) };
	    int[] yPoints = {y + (int) Math.round (25 * ratio), y + (int) Math.round (25 * ratio), y + (int) Math.round (55 * ratio), y + (int) Math.round (55 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 4);
	    //c.fillRect(x+(int)Math.round(5*ratio), y+(int)Math.round(20*ratio), (int)Math.round(50*ratio), (int)Math.round(30*ratio));
	    c.setColor (black);
	    c.drawPolygon (xPoints, yPoints, 4);
	    //c.drawRect(x+(int)Math.round(5*ratio), y+(int)Math.round(20*ratio), (int)Math.round(50*ratio), (int)Math.round(30*ratio));
	    c.drawLine (x + (int) Math.round (15 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (15 * ratio), y + (int) Math.round (55 * ratio));
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (30 * ratio), y + (int) Math.round (55 * ratio));
	    c.drawLine (x + (int) Math.round (45 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (45 * ratio), y + (int) Math.round (55 * ratio));
	}
	else if (cur == 32)
	{ //muffin(light blue)
	    c.setColor (new Color (222, 184, 135));
	    c.fillArc (x, y + (int) Math.round (5 * ratio), (int) Math.round (60 * ratio), (int) Math.round (50 * ratio), 0, 180);
	    c.setColor (new Color (173, 216, 230));
	    int[] xPoints = {x, x + (int) Math.round (60 * ratio), x + (int) Math.round (50 * ratio), x + (int) Math.round (10 * ratio) };
	    int[] yPoints = {y + (int) Math.round (25 * ratio), y + (int) Math.round (25 * ratio), y + (int) Math.round (55 * ratio), y + (int) Math.round (55 * ratio) };
	    c.fillPolygon (xPoints, yPoints, 4);
	    //c.fillRect(x+(int)Math.round(5*ratio), y+(int)Math.round(20*ratio), (int)Math.round(50*ratio), (int)Math.round(30*ratio));
	    c.setColor (black);
	    c.drawPolygon (xPoints, yPoints, 4);
	    //c.drawRect(x+(int)Math.round(5*ratio), y+(int)Math.round(20*ratio), (int)Math.round(50*ratio), (int)Math.round(30*ratio));
	    c.drawLine (x + (int) Math.round (15 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (15 * ratio), y + (int) Math.round (55 * ratio));
	    c.drawLine (x + (int) Math.round (30 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (30 * ratio), y + (int) Math.round (55 * ratio));
	    c.drawLine (x + (int) Math.round (45 * ratio), y + (int) Math.round (25 * ratio), x + (int) Math.round (45 * ratio), y + (int) Math.round (55 * ratio));
	}
    }


    /*Void method to display the top 10 scores of each difficulty
    Variable Dictionary
    NAME                        TYPE                    DESCRIPTION
    input                       BufferedReader          Reads the files to print out the top 10 scores of the difficulty
    output                      PrintWriter             Clears the file
    choice                      char                    Stores which difficulty the user wants to see, either easy, medium or hard
    arr                         String[]                Stores the 10 scores from the file to be printed to the screen
    cur                         int                     Keeps track of the current index of the array that you are on
    line                        String                  Reads each line of the file and then is used as the value to be stored in the array
    cury                        int                     Keeps track of the current y coordinates to draw the specific String at that location
    curr                        int                     Keeps track of the current red value in rgb and changes to create different colours
    curg                        int                     Keeps track of the current green value in rgb and changes it to create different colours
    split                       String[]                Array stores the current line split by spaces, 3 different parts
    clearFile                   char                    Stores whether the user wants to clear the file or not(Y/y or N/n)
    */
    public void highScores ()
    {
	c.clear ();
	c.setColor (new Color (0, 0, 0));
	c.fillRect (0, 0, 960, 600);
	c.setColor (new Color (255, 255, 102));
	c.setFont (new Font ("Serif", Font.BOLD, 40));
	c.drawString ("High Scores Screen", 250, 30);
	BufferedReader input;
	PrintWriter output;
	char choice;
	c.setFont (new Font ("Serif", Font.PLAIN, 30));
	c.drawString ("To view the easy high scores page, enter 'e' or 'E'.", 0, 60);
	c.drawString ("To view the medium high scores page, enter 'm' or 'M'.", 0, 130);
	c.drawString ("To view the hard high scores page, enter 'h' or 'H'.", 0, 200);
	c.drawString ("Any other key will go back to the menu.", 0, 270);
	c.drawString ("Please enter the high scores you would like to see based on the difficulty:", 0, 340);
	choice = c.getChar (); //get input from user
	c.setColor (new Color (0, 0, 0));
	c.fillRect (0, 0, 960, 600);
	int cur = 0;
	String[] arr = new String [10]; //store the current scores for whichever difficulty the user picked
	c.setFont (new Font ("Serif", Font.BOLD, 30));
	c.setColor (new Color (0, 191, 255));
	if (choice == 'e' || choice == 'E')
	{
	    c.drawString ("-  EASY SCORE RANKING  -", 250, 40);
	    try
	    {
		input = new BufferedReader (new FileReader ("EasyHighScores.txt")); //read the file
		String line = " ";
		while (line != null)
		{
		    line = input.readLine (); //keep reading every single line
		    if (line == null)
			break; //if the line is null then break
		    arr [cur] = line; //set the current index of the array to the line
		    cur++; //next index that is empty
		}
	    }
	    catch (IOException e)
	    {
	    }

	}
	else if (choice == 'm' || choice == 'M')
	{
	    c.drawString ("-  MEDIUM SCORE RANKING  -", 230, 40);
	    try
	    {
		input = new BufferedReader (new FileReader ("MediumHighScores.txt"));
		String line = " ";
		while (line != null)
		{
		    line = input.readLine ();
		    if (line == null)
			break;
		    arr [cur] = line;
		    cur++;
		}
	    }
	    catch (IOException e)
	    {
	    }
	}
	else if (choice == 'h' || choice == 'H')
	{
	    c.drawString ("-  HARD SCORE RANKING  -", 300, 40);
	    try
	    {
		input = new BufferedReader (new FileReader ("HardHighScores.txt"));
		String line = " ";
		while (line != null)
		{
		    line = input.readLine ();
		    if (line == null)
			break;
		    arr [cur] = line;
		    cur++;
		}
	    }
	    catch (IOException e)
	    {
	    }
	}
	else
	    return;
	int cury = 380 - (10 - cur) * 30, curr = 25, curg = 140;
	Color curColor = new Color (curr, 255, 128);
	c.setFont (new Font ("Serif", Font.BOLD, 25));
	c.drawString ("N a m e", 300, 80);
	c.drawString ("S c o r e", 500, 80);
	c.setFont (new Font ("Serif", Font.PLAIN, 18));
	for (int i = cur ; i >= 0 ; i--)
	{
	    for (int j = (i - 2) * 40 ; j >= 0 ; j--)
	    {
		c.setColor (new Color (0, 0, 0));
		c.fillRect (0, 90, 640, j);

	    }
	    if (i <= cur - 1)
	    {
		c.setColor (curColor);
		String[] split = arr [i].split (" ");
		c.drawString (split [0], 50, cury);
		if (split.length > 2)
		{
		    c.drawString (split [1], 300, cury);
		    c.drawString (split [2], 500, cury);
		}
		cury -= 30;
		if (i >= 7)
		    curg -= 38;
		else if (i >= 1)
		    curr += 38;
		curColor = new Color (curr, 255, curg);
		try
		{
		    Thread.sleep (100);
		}
		catch (Exception e)
		{
		}
	    }
	}
	c.setColor (new Color (0, 191, 255));
	c.drawString ("Would you like to clear the file?", 100, 450); //ask to clear file
	c.drawString ("Enter Y/y for yes and N/n for no", 150, 470);
	char clearFile = c.getChar (); //take in input
	if (clearFile == 'Y' || clearFile == 'y') //if the user entered "Y"
	{
	    for (int i = 0 ; i < 10 ; i++) //reset values
	    {
		if (choice == 'e' || choice == 'E')
		{
		    highScore [i] = 0; //set every single value to 0
		    highScoreNames [i] = null; //set the name to null
		    try
		    {
			output = new PrintWriter (new FileWriter ("EasyHighScores.txt"));
			output.print (""); //clear file
			output.close ();
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (choice == 'm' || choice == 'M')
		{
		    highScore1 [i] = 0;
		    highScoreNames1 [i] = null;
		    try
		    {
			output = new PrintWriter (new FileWriter ("MediumHighScores.txt"));
			output.print ("");
			output.close ();
		    }
		    catch (IOException e)
		    {
		    }
		}
		else
		{
		    highScore2 [i] = 0;
		    highScoreNames2 [i] = null;
		    try
		    {
			output = new PrintWriter (new FileWriter ("HardHighScores.txt"));
			output.print ("");
			output.close ();
		    }
		    catch (IOException e)
		    {
		    }
		}
	    }
	    c.drawString ("Your file has now been cleared.", 0, 510); //let user know that it has been cleared
	}
	c.drawString ("Press any key to continue: ", 0, 550); //go back to main menu
	c.getChar ();
    }


    /*Void method to display the screen after the game has ended
    Variable Dictionary
    Name                                              Type                                         Description
    output                                            PrintWriter                                  Used to write to files and edit them
    name                                              String                                       Store the input from the user when they are entering their names
    index                                             int                                          Store the index that player 1's score is at in high scores list
    index1                                            int                                          Store the index that player 2's score is at in high scores list
    curx                                              int                                          Store the current x coordinate for the user's name input
    cury                                              int                                          Store the current y coordinate for the user's name input

    try blocks are all used to write to the files so that they can have up to date high scores
    for loops are used to iterate through all of the high scores so they can be compared with the scores that were just gotten by the two players
    if statements are used to compare the single scores with the scores that the players have just gotten
    */
    public void winScreen ()
    {
	PrintWriter output;
	c.setColor (blue);
	c.fillRect (0, 0, 960, 600);
	if (player1Score > player2Score) //player 1 has more points, which means that player 1 wins
	{
	    c.setFont (new Font ("Serif", Font.BOLD, 50));
	    c.setColor (new Color (255, 255, 102));
	    c.drawString ("CONGRATULATIONS!", 30, 65);
	    c.setFont (new Font ("Serif", Font.BOLD, 20));
	    c.drawString ("Player 1 wins with " + player1Score + " points!", 60, 100);
	}
	else if (player1Score < player2Score) //player 2 has more points, which means that player 2 wins
	{
	    c.setFont (new Font ("Serif", Font.BOLD, 50));
	    c.setColor (new Color (255, 255, 102));
	    c.drawString ("CONGRATULATIONS!", 30, 65);
	    c.setFont (new Font ("Serif", Font.BOLD, 20));
	    c.drawString ("Player 2 wins with " + player2Score + " points!", 60, 100);
	}
	else //they have the same number of points, which means that it is a tie
	{
	    c.setFont (new Font ("Serif", Font.BOLD, 50));
	    c.setColor (new Color (255, 255, 102));
	    c.drawString ("TIE GAME", 60, 65);
	    c.setFont (new Font ("Serif", Font.PLAIN, 20));
	    c.drawString ("It was a tie with " + player1Score + " points to each player.", 60, 100);
	}
	String name = ""; //store the name of the users
	int index = -1, index1 = -1; //two variables to store the placement that the two users have
	for (int i = 0 ; i < 10 ; i++) //go through all 10 places in the score
	{
	    if (userDifficulty == 'e' || userDifficulty == 'E') //if difficulty is easy
	    {
		if (player1Score > highScore [i]) //compare with all 10 places and if it is higher set the index to that
		{
		    index = i; //index is for player 1
		    break;
		}
	    }
	    else if (userDifficulty == 'm' || userDifficulty == 'M') //if it is medium
	    {
		if (player1Score > highScore1 [i]) //set index to that for medium
		{
		    index = i;
		    break;
		}
	    }
	    else
	    {
		if (player1Score > highScore2 [i]) //hard
		{
		    index = i; //set index to that place for hard
		    break;
		}
	    }
	}
	for (int i = 0 ; i < 10 ; i++) //same thing, but for player 2
	{
	    if (userDifficulty == 'e' || userDifficulty == 'E')
	    {
		if (player2Score > highScore [i] && i != index)
		{
		    index1 = i;
		    break;
		}
	    }
	    else if (userDifficulty == 'm' || userDifficulty == 'M')
	    {
		if (player2Score > highScore1 [i] && i != index)
		{
		    index1 = i;
		    break;
		}
	    }
	    else
	    {
		if (player2Score > highScore2 [i] && i != index)
		{
		    index1 = i;
		    break;
		}
	    }
	}
	for (int i = 9 ; i >= index ; i--)
	{
	    if (index == -1) //if index is not found then break
		break;
	    if (userDifficulty == 'e' || userDifficulty == 'E') //easy difficulty, deciding which array to edit
	    {
		if (i == index) //if we are at the index then just replace it
		{
		    highScore [i] = player1Score;
		}
		else
		    highScore [i] = highScore [i - 1]; //otherwise replace it with the previous element as all values will move down by 1
	    }
	    else if (userDifficulty == 'm' || userDifficulty == 'M') //medium difficulty
	    {
		if (i == index)
		{
		    highScore1 [i] = player1Score;
		}
		else
		    highScore1 [i] = highScore1 [i - 1];
	    }
	    else
	    {
		if (i == index)
		{
		    highScore2 [i] = player1Score;
		}
		else
		    highScore2 [i] = highScore2 [i - 1];
	    }
	}
	for (int i = 9 ; i >= index1 ; i--)
	{
	    if (index1 == -1)
		break;
	    if (userDifficulty == 'e' || userDifficulty == 'E')
	    {
		if (i == index1)
		{
		    highScore [i] = player2Score;
		}
		else
		    highScore [i] = highScore [i - 1];
	    }
	    else if (userDifficulty == 'm' || userDifficulty == 'M')
	    {
		if (i == index1)
		{
		    highScore1 [i] = player2Score;
		}
		else
		    highScore1 [i] = highScore1 [i - 1];
	    }
	    else
	    {
		if (i == index1)
		{
		    highScore2 [i] = player2Score;
		}
		else
		    highScore2 [i] = highScore2 [i - 1];
	    }
	}
	c.drawString ("Note: your name can be at most 10 characters long.", 10, 430);
	int curx = 310, cury = 130; //set curx and cury
	if (index != -1)
	{
	    c.drawString ("Player 1, please enter your name:", 10, 130);
	    while (true)
	    {
		char val = c.getChar (); //get input
		if (val == '\n') //if key is equal to enter then store it
		{
		    for (int i = 9 ; i >= index ; i--)
		    {
			if (index == -1)
			    break;
			if (userDifficulty == 'e' || userDifficulty == 'E') //store to corresponding array depending on which difficulty game was on
			{
			    if (i == index)
			    {
				highScoreNames [i] = name;
			    }
			    else
				highScoreNames [i] = highScoreNames [i - 1];
			}
			else if (userDifficulty == 'm' || userDifficulty == 'M')
			{
			    if (i == index)
			    {
				highScoreNames1 [i] = name;
			    }
			    else
				highScoreNames1 [i] = highScoreNames1 [i - 1];
			}
			else
			{
			    if (i == index)
			    {
				highScoreNames2 [i] = name;
			    }
			    else
				highScoreNames2 [i] = highScoreNames2 [i - 1];
			}
		    }
		    break;
		}
		if (val == 8) //backspace
		{
		    if (name.length () != 0) //if there is a character entered
		    {
			name = name.substring (0, name.length () - 1); //delete the most recent entered character
			c.setColor (blue);
			c.fillRect (curx - 20, cury - 20, 20, 30); //cover it with background colour
			curx -= 15; //go back 1 character x position
		    }
		}
		else if (name.length () < 10) //if less than 10 characters
		{
		    name += val; //add it
		    c.setColor (new Color (255, 255, 102));
		    c.drawString (Character.toString (val), curx, cury); //display it to screen
		    curx += 15; //next space for character
		}
	    }
	}
	if (index1 != -1) //player 2's score also has a placement
	{
	    name = "";
	    curx = 310; //set the current x placement and y placement back to default
	    cury = 190;
	    c.drawString ("Player 2, please enter your name:", 10, 190); //ask for player 2's name
	    while (true)
	    {
		char val = c.getChar (); //get input from the user
		if (val == '\n') //if pressed enter
		{
		    for (int i = 9 ; i >= index1 ; i--)
		    {
			if (userDifficulty == 'e' || userDifficulty == 'E')
			{
			    if (i == index1) //if at that index then just replace it with the new value
			    {
				highScoreNames [i] = name;
			    }
			    else //otherwise, replace all values with the value above it(pushing it down)
				highScoreNames [i] = highScoreNames [i - 1];
			}
			else if (userDifficulty == 'm' || userDifficulty == 'M') //same if it is medium difficulty
			{
			    if (i == index1)
			    {
				highScoreNames1 [i] = name;
			    }
			    else
				highScoreNames1 [i] = highScoreNames1 [i - 1];
			}
			else //same with hard difficulty
			{
			    if (i == index1)
			    {
				highScoreNames2 [i] = name;
			    }
			    else
				highScoreNames2 [i] = highScoreNames2 [i - 1];
			}
		    }
		    break; //break out of while loop as the name has been fully entered
		}
		if (val == 8) //backspace
		{
		    if (name.length () != 0) //there is a character entered
		    {
			name = name.substring (0, name.length () - 1); //delete the most recent character
			c.setColor (blue);
			c.fillRect (curx - 20, cury - 20, 20, 30); //cover it with background colour
			curx -= 15; //go back one character
		    }
		}
		else if (name.length () < 10) //character max limit can only be 10 characters
		{
		    name += val;
		    c.setColor (new Color (255, 255, 102));
		    c.drawString (Character.toString (val), curx, cury);
		    curx += 15;
		}
	    }
	}
	if (userDifficulty == 'E' || userDifficulty == 'e') //write to file
	{
	    try
	    {
		output = new PrintWriter (new FileWriter ("EasyHighScores.txt"));
		output.print (""); //clear file
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	    try
	    {
		output = new PrintWriter (new FileWriter ("EasyHighScores.txt"));
		for (int i = 1 ; i <= 10 ; i++)
		{
		    if (highScoreNames [i - 1] != null)
		    {
			output.println (i + ". " + highScoreNames [i - 1] + " " + highScore [i - 1]); //write each line from the corresponding array to the file
		    }
		}
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}
	else if (userDifficulty == 'M' || userDifficulty == 'm')
	{
	    for (int i = 0 ; i < 10 ; i++)
		System.out.println (highScoreNames1 [i]);
	    try
	    {
		output = new PrintWriter (new FileWriter ("MediumHighScores.txt"));
		output.print ("");
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	    try
	    {
		output = new PrintWriter (new FileWriter ("MediumHighScores.txt"));
		for (int i = 1 ; i <= 10 ; i++)
		{
		    if (highScoreNames1 [i - 1] != null)
		    {
			output.println (i + ". " + highScoreNames1 [i - 1] + " " + highScore1 [i - 1]);
		    }
		}
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}
	else
	{
	    try //clear file
	    {
		output = new PrintWriter (new FileWriter ("HardHighScores.txt"));
		output.print ("");
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	    try
	    {
		output = new PrintWriter (new FileWriter ("HardHighScores.txt"));
		for (int i = 1 ; i <= 10 ; i++)
		{
		    if (highScoreNames2 [i - 1] != null)
		    {
			output.println (i + ". " + highScoreNames2 [i - 1] + " " + highScore2 [i - 1]); //print every single line to the file
		    }
		}
		output.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}
	c.drawString ("Press any key to continue: ", 0, 500); //prompt user to continue
	c.getChar (); //get char, then go back to main  menu
    }


    /* Private black box method to return the corresponding index
    Each if statement is used to check if the parameter is equal to the specific character or not. Depending on which one it matches, it returns a number
    that can be used as an index for the arrays in the game.
    */

    private int convert (char a)
    {
	if (a == 'A' || a == 'a')
	    return 1;
	else if (a == 'B' || a == 'b')
	    return 2;
	else if (a == 'C' || a == 'c')
	    return 3;
	else if (a == 'D' || a == 'd')
	    return 4;
	else if (a == 'E' || a == 'e')
	    return 5;
	else if (a == 'F' || a == 'f')
	    return 6;
	else if (a == 'G' || a == 'g')
	    return 7;
	else
	    return 8;
    }


    /*Void method to display a goodbye message, wait and then exit the program
    try block used to use Thread.sleep() to wait 4 seconds before exiting the program.
    */
    public void goodbye ()
    {
	c.setColor (blue);
	c.fillRect (0, 0, 960, 600);
	c.setColor (new Color (255, 255, 102));
	c.setFont (new Font ("Serif", Font.BOLD, 50));
	c.drawString ("Thanks for Playing!", 280, 75);
	c.setFont (new Font ("Serif", Font.PLAIN, 30));
	c.drawString ("This program was made by Alan Li.", 20, 200);
	try
	{
	    Thread.sleep (4000);
	}
	catch (Exception e)
	{
	}
	System.exit (0);
    }


    //main method
    public static void main (String[] args)
    {
	ConcentrationGame cg;
	cg = new ConcentrationGame ();
	cg.splashScreen (); //display the splash screen by calling the method
	while (true)
	{
	    cg.mainMenu (); //go to the main menu page by calling the mainMenu method
	    if (cg.userMenuChoice == 'i' || cg.userMenuChoice == 'I') //user pressed key meaning they want to see instructions
	    {
		cg.instructions (); //call the instructions method and go to that screen
	    }
	    else if (cg.userMenuChoice == 'h' || cg.userMenuChoice == 'H') //user wants to go to high scores screen
	    {
		cg.highScores (); //call the high scores method
	    }
	    else if (cg.userMenuChoice == 'c' || cg.userMenuChoice == 'C') //user wants to play the game
	    {
		cg.askData (); //call the askData() method
	    }
	    else
		break; //break if they press any other key
	}
	cg.goodbye (); //go to goodbye screen and then proceed to exit program
    }
}


