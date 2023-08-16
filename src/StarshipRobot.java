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
 * This class models Starship Robot objects delivering food to badger fans
 *
 */
public class StarshipRobot extends MovingThing {
    private Thing source; // source point of this StarshipRobot at its current journey
    /**
     * destination point of this StarshipRobot at its current journey delivering food to badgers
     */
    private Thing destination; // destination point of this StarshipRobot at its current journey

    /**
     * Creates a new StarshipRobot and sets its source, destination, and speed. The (x,y) position of
     * this StarshipRobot is set to the (x,y) position of source.
     *
     * @param source      Thing object representing the start point of this StarshipRobot
     * @param destination Thing object representing the destination point of this StarshipRobot
     * @param speed       movement speed of this StarshipRobot
     */
    public StarshipRobot(Thing source, Thing destination, int speed) {
        // Call the parent class constructor with the source's x and y coordinates, and the speed
        super(source.x, source.y, speed, "starshipRobot.png"); // Replace "starshipRobotImage.png" with the correct image filename

        // Set the source and destination
        this.source = source;
        this.destination = destination;
        this.speed = speed;
        // Determine if the StarshipRobot is facing right based on the source and destination x coordinates
        isFacingRight = source.x < destination.y;
    }


    /**
     * Draws this StarshipRobot to the display window while it is in motion delivering food.
     * This method first prompts this StarshipRobot to go. Then, it draws it to the display window.
     * Think of partial overriding to draw this StarshipRobot as its image is not directly accessed from here.
     * The super.draw() can do so!
     */
    @Override
    public void draw() {
        // Call the parent class's draw method to draw the StarshipRobot
        super.draw();
        go();
    }

    /**
     * Checks whether this StarshipRobot is over a specific Thing
     *
     * @param thing a given Thing object
     * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
     * false.
     */
    public boolean isOver(Thing thing) {
        float x1 = x - this.image().width / 2;
        float x2 = x + this.image().width / 2;
        float y1 = y - this.image().height / 2;
        float y2 = y + this.image().height / 2;

        float x3 = thing.x - thing.image().width / 2;
        float x4 = thing.x + thing.image().width / 2;
        float y3 = thing.y - thing.image().height / 2;
        float y4 = thing.y + thing.image().height / 2;

        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    /**
     * Helper method to move this StarshipRobot towards its destination
     */
    private void moveTowardsDestination() {
        float dx = destination.x - this.x; // x-move towards destination
        float dy = destination.y - this.y; // y-move towards destination
        int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
        if (d != 0) { // move!
            this.x += speed * dx / d;
            this.y += speed * dy / d;
        }
    }

    /**
     * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves back-and-forth between its source and destination.
     * If the starship robot is over its destination, this method:
     * - switches the source and destination,
     * - switches the value of isFacingRight to its opposite (!isFacingRight), so that the starship robot faces the opposite direction.
     */
    public void go() {
        moveTowardsDestination();
        // switch source and destination if this StarshipRobot reached its destination
        if (this.isOver(this.destination)) {
            Thing d = destination;
            destination = source;
            source = d;
            isFacingRight = !isFacingRight;
        }

    }
}
