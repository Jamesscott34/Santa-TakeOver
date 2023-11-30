import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.InputEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains various utility functions
 * used in the Santa TakeOver application.
 */
public class functions {
    private static List<String> items = new ArrayList<>();
    public int days;

    /**
     * Calculates the number of days until Christmas from the current date.
     *
     * @return The number of days until Christmas
     */
    private static int daysUntilChristmas() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        LocalDate christmas = LocalDate.of(currentYear, 12, 25);

        if (today.isAfter(christmas)) {
            currentYear++;
            christmas = LocalDate.of(currentYear, 12, 25);
        }

        return (int) java.time.temporal.ChronoUnit.DAYS.between(today, christmas);
    }
    /**
     * Adds an item to the list of items.
     *
     * @param item The item to add to the list
     */
    public static void addItemToList(String item) {
        items.add(item);
    }
    /**
     * Retrieves the list of items.
     *
     * @return The list of items
     */
    public static List<String> getListItems() {
        return items;
    }
    /**
     * Calculates the number of sleeps until Christmas.
     *
     * @return The number of sleeps until Christmas
     */
    public static int sleepsUntilChristmas() {
        int daysLeft = daysUntilChristmas();
        int sleeps = (daysLeft >= 1) ? daysLeft - 1 : 0;
        return sleeps;
    }

    /**
     * Controls Mouse Movement
     *
     * using x and y co ordinates
     */
    public static void moveMouse(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    /**
     * Reads out the given message using FreeTTS.
     *
     * @param message The message to be read out
     */
    static void readOutMessage(String message) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice("kevin16"); // Select a voice from FreeTTS, "kevin16" is an example
        voice.allocate();
        voice.speak(message);
    }
    /**
     * Controls Mouse Clicks
     *
     * Using X and Y Co Ordinates
     */
    public static void leftClick(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    /**
     * Controls Mouse Clicks
     *
     * Using X and Y Co Ordinates
     */
    public static void rightClick(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(functions.class.getResourceAsStream("../music/xmas.wav")));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the first step of the Santa TakeOver application.
     */
    public static void stepOne() {
        // mouse Movement takes place
        // after speech and will open up paint or other stuff
        moveMouse(600, 1058);
        leftClick(600, 1058);
        sleep(5000);
        leftClick(667, 328);
        sleep(5000);
        leftClick(1635, 156);
        sleep(5000);
        leftClick(38, 152);
        leftClick(248, 261);
        sleep(5000);
        leftClick(1173, 24);
        sleep(2000);
        moveMouse(597, 1054);
        leftClick(597, 1054);
        sleep(5000);
        moveMouse(1012, 527);
        leftClick(1012, 527);
    }

    public static void stepTwo() {
        // Implement step two logic
        // opens up paint
        //todo paint logic
    }

    public static void stepThree() {
        // Implement step three logic
        // saves paint picture to screen

    }


    public static void sleep(int milliseconds) {
        // Helper method for sleeping
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes all the steps for the Santa TakeOver application.
     */
    public static void mainStep() {
        stepOne();
        stepTwo();
        stepThree();
    }
}
