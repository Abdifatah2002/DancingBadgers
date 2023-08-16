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
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers extends PApplet  {

    /**
     * array storing badger's dance show steps
     */
    private static DanceStep[] badgersDanceSteps = new DanceStep[]{DanceStep.LEFT,
            DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN,
            DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP}; // array storing badger's dance show steps
    /**
     * Tells wether the dance show is on. Intially
     */
    private boolean danceShowOn; //Tells wether dance show is on. Initially
    /**
     * array storing the postions of the dancing badgers at the start of the dance show
     */
    private static float[][] startDancePositions = new float[][]{{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};// array storing the postions of the dancing badgers at the start of the dance show

    /**
     * backgound image
     */
    private static PImage backgroundImage;  // backgound image

    /**
     * arraylist storing Thing objects
     */
    private static ArrayList<Thing> things;  // arraylist storing Thing objects

    //private  static ArrayList<StarshipRobot>robots;


    /**
     * Generator of random numbers
     */
    private static Random randGen;  // Generator of random numbers

    /**
     * Maximum number of Badger objects allowed in the basketball court
     */
    private static int badgersCountMax;//Maximum number of Badger objects allowed in the basketball court

    public DancingBadgers() {

    }

    /**
     * Driver method to run this graphic application
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {

        PApplet.main("DancingBadgers");

    }

    /**
     * sets the size of the display window of this graphic application
     */
    @Override
    public void settings() {
        this.size(800, 600);

    }


    /**
     * Defines initial environment properties of this graphic application. This method initializes all
     * the data fields defined in this class.
     */

    @Override
    public void setup() {
        // set processing data fields for Thing and StarshipRobot classes
        Thing.setProcessing(this);
        StarshipRobot.setProcessing(this);
        Badger.setProcessing(this);

        // data fields initialization
        backgroundImage = loadImage("images" + File.separator + "background.png");
        randGen = new Random();
        badgersCountMax = 5;

        things = new ArrayList<Thing>();

        // create 4 Things and add them to the things list
        things.add(new Thing(50, 50, "target.png"));
        things.add(new Thing(750, 550, "target.png"));
        things.add(new Thing(750, 50, "shoppingCounter.png"));
        things.add(new Thing(50, 550, "shoppingCounter.png"));

        // create and add two StarshipRobot objects to the things list
        things.add(new StarshipRobot(things.get(2), things.get(0), 3));
        things.add(new StarshipRobot(things.get(3), things.get(1), 5));

        // create and add basketballs
        things.add(new Basketball(50, 300));
        things.add(new Basketball(750, 300));

        this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
        this.textAlign(3, 3); // sets the alignment of the text
        this.imageMode(3); // interprets the x and y position of an image to its center
        this.focused = true; // confirms that this screen is "focused", meaning initialize badgers

        setStartDancePositions();
    }




    /**
     * Callback method that draws and updates the application display window. This method runs in an infinite loop until the program exits.
     */
    @Override
    public void draw() {
        // set the background color and draw the background image to the center of the screen
        background(color(255, 218, 185));
        image(backgroundImage, width / 2, height / 2);

        // draw things
        for (int i = 0; i < things.size(); i++) {
            Thing thing = things.get(i);
            thing.draw();
        }

    }


    /**
     * Callback method called each time the user presses the mouse
     */
    @Override
    public void mousePressed() {
        for (Thing thing : things) {
            if (thing instanceof Clickable && thing.isMouseOver()) {
                ((Clickable) thing).mousePressed();
                return;
            }
        }
    }

    /**
     * Callback method called each time the mouse is released
     */
    @Override
    public void mouseReleased() {
        for (Thing thing : things) {
            if (thing.isMouseOver()) {
                // Do something when the mouse is released over the thing
            }
        }
    }

    /**
     * Gets the number of Badger objects present in the basketball arena
     * @return- the number of Badger objects present in the basketball arena
     */

    public int badgersCount() {
        int count = 0;
        for (Thing thing : things) {
            if (thing instanceof Badger) {
                count++;
            }
        }
        return count;
    }

    /**
     * Sets the badgers start dance positions. The start dance positions of the badgers are provided in the startDancePositions array.
     * The array startDancePositions contains badgersCountMax dance positions. If there are fewer Badger objects in the basketball arena, they will be assigned the first positions.
     */
    private void setStartDancePositions() {
        int index = 0;
        for (Thing thing : things) {
            if (thing instanceof Badger && index < startDancePositions.length) {
                thing.x = startDancePositions[index][0];
                thing.y = startDancePositions[index][1];
                index++;
            }
        }
    }


    /**
     * Callback method called each time the user presses a key.
     */
    @Override
    /**
     * Callback method called each time the user presses a key.
     */

    public void keyPressed() {
        switch (Character.toUpperCase(key)) {
            case 'B': // Add new badgers as long as the maximum number of badgers allowed to be present in the field is not reached
                if (badgersCount() < badgersCountMax && !danceShowOn) {
                    things.add(new Badger(randGen.nextInt(width), randGen.nextInt(height), badgersDanceSteps));

                }
                break;
            case 'C': // Terminate danceShow and remove all MovingThing objects
                if (danceShowOn) {
                    danceShowOn = false;
                    things.removeIf(thing -> thing instanceof MovingThing);
                } else {
                    things.removeIf(thing -> thing instanceof MovingThing);
                }
                break;
            case 'D': // Start the dance show if danceShowOn is false and there is at least one Badger object in the basketball arena
                if (!danceShowOn && badgersCount() > 0) {
                    danceShowOn = true;
                    setStartDancePositions();
                    for (Thing thing : things) {
                        if (thing instanceof Badger) {
                            ((Badger) thing).startDancing();
                        }
                    }
                }
                break;
            case 'R': // Delete the badger being pressed if the danceShow is not on
                if (!danceShowOn) {
                    for (int i = 0; i < things.size(); i++) {
                        if (things.get(i) instanceof Badger && things.get(i).isMouseOver()) {
                            things.remove(i);
                            break;
                        }
                    }
                }
                break;
            case 'S': // Terminate the dance show and stop all Badger objects from dancing
                if (danceShowOn) {
                    danceShowOn = false;
                    for (Thing thing : things) {
                        if (thing instanceof Badger) {
                            ((Badger) thing).stopDancing();
                        }
                    }
                }
                break;
        }
    }
}

