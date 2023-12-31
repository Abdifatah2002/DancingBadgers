//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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
 * This class models moving thing objects. A moving thing is defined by its speed to which directiion it is facing ( right or left)
 */

public class MovingThing extends Thing implements Comparable<MovingThing> { // indicates whether this movingThing is facing right ot not
    /**
     * indicates whether this movingThing is facing right ot not
     */
    protected boolean isFacingRight;
    /**
     * Movement speed of this MovingThing
     */
    protected int speed;// Movement speed of this MovingThing

    /**
     * Creates a new MovingThing and sets its speed, image file, and initial x and y position. A MovingThing object is initially facing right.
     * @param x -  starting x-position of this MovingThing
     * @param y - starting y-position of this MovingThing
     * @param speed - movement speed of this MovingThing
     * @param imageFileName - filename of the image of this MovingThing, for instance "name.png"
     */
    public MovingThing(float x, float y, int speed, String imageFileName) {
        // Call the parent class constructor
        super(x, y, imageFileName);

        // Set the speed of this MovingThing
        this.speed = speed;
        this.isFacingRight = true;
    }

    /**
     * Draws this MovingThing at its current position. The implementation details of this method is fully provided in the write-up of p05.
     */
    @Override
    public void draw() {
        processing.pushMatrix();
        processing.rotate(0.0f);
        processing.translate(x, y);
        if (!isFacingRight) {
            processing.scale(-1.0f, 1.0f);
        }
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();
    }

    /**
     * Compares this object with the specified MovingThing for order, in the increasing order of their speeds.
     * @param other - the MovingThing object to be compared.
     * @return- zero if this object and other have the same speed, a negative integer if the speed of this moving object is less than the speed of other, and a positive integer otherwise.
     */
    @Override
    public int compareTo(MovingThing other) {
        return Integer.compare(this.speed, other.speed);
    }
}

