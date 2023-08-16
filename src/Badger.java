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
// File header comes here
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class models a Badger object in the P03 Dancing Badgers programming assignment
 *
 */
public class Badger extends MovingThing implements Clickable {
    /**
     * indicates whether this badger is being dragged or not
     */
    private boolean isDragging; //indicates whether the badger is being dragged or not

    /**
     * old x-position of the mouse
     */
    private static int oldMouseX; // old x-position of the mouse

    /**
     * old y-position of the mouse
     */
    private static int oldMouseY; // old y-position of the mouse

    /**
     * indicates where this badger is dancing or not
     */
    private boolean isDancing= true  ;//indicates whether this badger is dancing or not


    /**
     * array storing this Badger's dance show steps
     */
    private DanceStep[] danceSteps;  // array storing this Badger's dance show steps

    /**
     * stores the next dance (x,y) postion of this badger
     * next dance postion [0] x-postion
     * nextDancepostion [1]; y-postion
     */
    private float[] nextDancePosition; //stores
    /**
     * index position of the current dance step of this badger
     */
    private int stepIndex;// index position of the current dance step of this badger

    /**
     * Creates a new badger object positioned at a specific position of the display window
     *
     * @param x x-position of this Badger object within the display window
     * @param y y-position of this Badger object within the display window
     */
    public Badger(float x, float y, DanceStep[] danceSteps) {
        super(x, y, 2, "badger.png");
        this.isDragging = false;
        this.isDancing = false;
        this.danceSteps = danceSteps;
        this.stepIndex = 0;
        this.nextDancePosition = new float[]{x, y}; // new line
    }


    /**
     * Draws this badger to the display window. It sets also its position to the mouse position if
     * this badger is being dragged (i.e. if its isDragging field is set to true).
     */
    @Override
    public void draw() {
        if (isDragging) {
            drag();
        } else if (isDancing) {
            dance();
        }
        super.draw();
    }

    /**
     * Checks whether this badger is being dragged
     *
     * @return true if the badger is being dragged, false otherwise
     */
    public boolean isDragging() {
        return isDragging;
    }


    /**
     * Helper method to drag this Badger object to follow the mouse moves
     */
    private void drag() {

        int dx = processing.mouseX - oldMouseX;
        int dy = processing.mouseY - oldMouseY;
        x += dx;
        y += dy;

        if (x > 0)
            x = Math.min(x, processing.width);
        else
            x = 0;
        if (y > 0)
            y = Math.min(y, processing.height);
        else
            y = 0;
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
    }


    /**
     * Starts dragging this badger
     */
    public void startDragging() {//good
        if (!isDancing) {
            isDragging = true;
        }
    }

    /**
     * Stops dragging this Badger object
     */
    public void stopDragging() {
        this.isDragging = false;
    } // good

    /**
     * Defines the behavior of this Badger when it is clicked. If the mouse is over this badger and this badger is NOT dancing, this method starts dragging this badger.
     */
    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            startDragging();
            stopDancing();
        }
    }

    /**
     * Defines the behavior of this Badger when the mouse is released.
     * If the mouse is released, this badger stops dragging.
     */
    @Override
    public void mouseReleased() {
        stopDragging();
    }

    /**
     * This helper method moves this badger one speed towards its nextDancePosition. Then, it checks whether this Badger is facing right and updates the isFacingRight data field accordingly.
     * After making one move dance, a badger is facing right if the x-move towards its next dance position is positive, otherwise, it is facing left.
     * @return - true if this Badger almost reached its next dance position, meaning that the distance to its next dance position is less than 2 times its speed. Otherwise, return false.
     */
    private boolean makeMoveDance() {
        float dx = nextDancePosition[0] - this.x;
        float dy = nextDancePosition[1] - this.y;
        float d = (float) Math.sqrt(dx * dx + dy * dy);

        if (d != 0) {
            this.x += speed * dx / d;
            this.y += speed * dy / d;
        }

        isFacingRight = dx > 0;

        return d < 2 * speed;
    }





    /**
     *Prompts this badger to start dancing. This method:
     * - updates the isDancing data field
     * - stops dragging this badger
     * - sets stepIndex to zero
     * - Resets the nextDancePosition
     */
    public void startDancing() {
        isDancing = true;
        isDragging = false;
        stepIndex = 0;
        nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
    }




    /**
     *Prompts this badger to stop dancing. Sets the isDancing data field to false.
     */

    public void stopDancing() { //good
        isDancing = false;
        stepIndex = 0;
    }

    /**
     * Implements the dance behavior of this Badger. This method prompts the Badger to make one move dance.
     * If the makeMoveDance method call returns true (meaning the badger almost reached its nextDancePosition), this method MUST:
     * - update its next dance position (see DanceStep.getPositionAfter()),
     * - increment the stepIndex.
     * Note that the danceSteps array is a circular indexing array. The stepIndex should be incremented by one and then wrapped around with respect to the length of the array.
     */

    private void dance() {
        if (makeMoveDance()) {
            stepIndex = (stepIndex + 1) % danceSteps.length;
            nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
        }
    }


}



