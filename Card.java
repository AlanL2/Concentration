/*
Alan Li
Mrs. Krasteva
January 2, 2019
The purpose of this class is the animate the splash screen. each card will start from a different height and slide down towards 
the center of the screen at the same time, which is where I use the thread.
*/
//importing libraries
import java.awt.*;
import hsa.Console;
import java.lang.*;

public class Card extends Thread{ //extends thread for the animation
    private Console c; //private console for this class
    private int x, y; //used to store the values of the given x and y points
    final Color pink = new Color (255, 99, 71), white = new Color(255, 255, 255); //colors that we use a lot 
    private void slideCard(){ //public method to animate the card moving
	for(int i = y; i<=170; i+=2){ //starting from the current y, ends up at y=170 so all cards will meet at the same point
	    c.setColor (new Color (30, 144, 225)); //Set it to the background colour
	    c.fillRect (x, y, 75, i - y+10); //cover all of the animation above the current location so card moves downwards
	    c.setColor (pink); //set the colour to our already declared pink
	    c.fillRoundRect (x, i, 70, 70, 20, 20); //draw the current card
	    for (int k = 0 ; k < 5 ; k++)
	    {
		c.setColor (white); //set the colour to white
		c.drawOval (x + k, i+k, 65 - k * 2, 65 - k * 2); //draw the design on the card, for loop makes the lines thicker
	    }
	    c.setColor (white); //set colour to white again in case for colour glitches
	    int[] xPoints = {x + 5, x + 33, x + 61, x + 33}, yPoints = {i + 35, i + 5, i + 35, i + 60};
	    c.fillPolygon (xPoints, yPoints, 4); //draw a diamond using previously declared arrays
	    c.setColor(white); //set colour to white again in case for issues with wrong colour
	    c.fillRect (x + 13, i+13, 40, 40); //draw a normal rectangle to make back of card design
	    c.setColor (new Color (0, 0, 0)); //set colour to black
	    c.drawRoundRect (x, i, 68, 68, 20, 20); //draw the outline
	    try //animation code
	    {
		Thread.sleep (3); //delay for 0.003 seconds 
	    }
	    catch (Exception e)
	    {
	    }
	}
    }
    public Card(Console con, int x0, int y0){ //constructor
	c = con; //set our private console to the main console
	x = x0; //set the private x to the x parameter passed into the class
	y = y0; //set the private y to the y parameter passed into the class
    }
    public void run(){ //method to run the thread
	slideCard(); //call the slideCard() method to animate the cards
    }
}
