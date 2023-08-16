///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P05 Dancing Badger Part 3
// Course:   CS 300 Spring 2023
//
// Author:   Abdifatah Abdi
// Email:    aaabdi2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
/// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//
////   _X__ Write-up states that pair programming is allowed for this assignment.
//
////   _X__ We have both read and understand the course Pair Programming Policy.
//
////   _X__ We have registered our team prior to the team registration deadline.
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//// Persons:         TA: TA Snehal Wadhwani  help with little help on my isOver method in the starshiprobot
// TA: Yiwei Zhang help with little help on my moveTowardsDestination
//TA; MICHELLE JENSEN help me a lttile bit
//// Online Sources:  i used the https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p5/doc/StarshipRobot.html for my fields and methods
///////////////////////////////////////////////////////////////////////////////
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class models Basketball objects. When clicked, the baskeball rotate
 */

public class Basketball extends Thing implements Clickable {


    /**
     * Total number of rotations this Basketball object has made since it was created.
     */
    private int rotations;//Total number of rotations this Basketball object has made since it was created.
    /**
     * Defines the rotation angle in radians that this Basketball object make when clicked.
     */
    public float rotation;//Defines the rotation angle in radians that this Basketball object make when clicked.

    /**
     * Creates a new Basketball object located at (x,y) position whose image filename is "basketball.png", and sets its rotation angle to PApplet.PI/2.
     * Initially, when created, the basketball has made zero rotations.
     *
     * @param x-           x-position of this Basketball object in the display window
     * @param y-y-position of this Basketball object in the display window
     */
    public Basketball(float x, float y) {
        super(x, y, "basketball.png");
        this.rotation = PApplet.PI / 2;
        this.rotations = 0;
    }

    /**
     * Draws this rotating Basketball object to the display window. The implementation details of this method is fully provided in the write-up of p05.
     */
    @Override
    public void draw() {

        processing.pushMatrix();
        processing.translate(x, y);
        processing.rotate(this.rotations * rotation);
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();

    }

    /**
     * Defines the behavior of this basketball when the mouse is pressed. The basketball rotates when it is clicked (the mouse is over it when pressed).
     */

    public void mousePressed() {
        if (isMouseOver()) {
            this.rotations++;
        }
    }

    /**
     * Called when the mouse is released. A basketball object does nothing when the mouse is released. This is a method with an empty body.
     */
    public void mouseReleased() {

    }

    /**
     * This method rotates this basketball object by incrementing the number of its rotations by one.
     */
    public void rotate() {
        this.rotations++;
    }
}


