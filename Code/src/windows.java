import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class contains the GUI components
 * and logic for the Santa TakeOver application.
 */
public class windows {
    private static String savedName = "";
    private static boolean nameEntered = false;
    private static boolean listEntered = false;


    /**
     * Submits the form data and opens the list window.
     *
     * @param rootWindow       The root window
     * @param entryName        Text field for entering the name
     * @param messageTextArea  Text area for displaying the message
     */
    private static void submitName(JFrame rootWindow, JTextField entryName, JTextArea messageTextArea) {
        savedName = entryName.getText();
        rootWindow.dispose();
        nameEntered = true;
        openListWindow(messageTextArea);
    }

    /**
     * Saves the user's list and starts the application.
     *
     * @param listWindow       The list window
     * @param listEntry        Text area for entering the list
     * @param messageTextArea  Text area for displaying the message
     */
    private static void saveList(JFrame listWindow, JTextArea listEntry, JTextArea messageTextArea) {
        String userList = listEntry.getText();
        functions.addItemToList(userList);
        listWindow.dispose();
        listEntered = true;
        start(messageTextArea);
    }


    /**
     * Opens the list window for entering the user's list.
     *
     * @param messageTextArea  Text area for displaying the message
     */
    private static void openListWindow(JTextArea messageTextArea) {
        JFrame listWindow = new JFrame("The Childs List");
        listWindow.setSize(600, 800);
        listWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon originalIcon = new ImageIcon("./images/santa.png"); // Replace 'image_file.png' with your image file name

        // Resizing the image
        Image img = originalIcon.getImage();
        Image resizedImage = img.getScaledInstance(600, 600, Image.SCALE_SMOOTH); // Replace 100, 100 with desired width and height
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        listWindow.add(imageLabel, BorderLayout.SOUTH);

        JLabel listLabel = new JLabel("Enter your list:");
        listWindow.add(listLabel, BorderLayout.NORTH);

        JTextArea listEntry = new JTextArea();
        listWindow.add(new JScrollPane(listEntry), BorderLayout.CENTER);

        JButton saveButton = new JButton("Save List");
        saveButton.addActionListener(e -> saveList(listWindow, listEntry, messageTextArea));
        listWindow.add(saveButton, BorderLayout.SOUTH);

        listWindow.setVisible(true);
    }


    /**
     * Runs the Santa TakeOver application.
     */
    static void run() {
        JFrame root = new JFrame("Santa TakeOver");
        root.setSize(1000, 800);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon originalIcon = new ImageIcon("./images/santa.png"); // Replace 'image_file.png' with your image file name

        // Resizing the image
        Image img = originalIcon.getImage();
        Image resizedImage = img.getScaledInstance(600, 600, Image.SCALE_SMOOTH); // Replace 100, 100 with desired width and height
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        root.add(imageLabel, BorderLayout.SOUTH);

        JPanel frame = new JPanel();
        frame.setLayout(new FlowLayout());
        root.add(frame);

        JLabel nameLabel = new JLabel("Name: ");
        frame.add(nameLabel);

        JTextField entryName = new JTextField(20);
        frame.add(entryName);

        entryName.addActionListener(e -> submitName(root, entryName, messageTextArea()));

        JTextArea messageTextArea = messageTextArea();
        frame.add(messageTextArea);

        root.setVisible(true);
    }

    /**
     * Creates a message text area with specific settings.
     *
     * @return A configured text area for displaying messages
     */

    private static JTextArea messageTextArea() {
        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        return messageTextArea;
    }

    /**
     * Starts the application by generating the message.
     *
     * @param messageTextArea  Text area for displaying the message
     */
    private static void start(JTextArea messageTextArea) {
        if (nameEntered && listEntered) {
            String message = generateMessage();
            messageTextArea.setText(message);
            openMessageWindow(message);
        }
    }
    /**
     * Generates the message based on user input.
     *
     * @return A generated message
     */

    private static String generateMessage() {
        List<String> items = functions.getListItems();
        StringBuilder itemList = new StringBuilder();
        for (String item : items) {
            itemList.append(item).append("\n");
        }

        String days = null;
        return "Dear "+savedName+",\n" +
                "\n" +
                "Ho ho ho! Greetings from the frosty North Pole!\n" +
                "\n" +
                "As the snowflakes dance and the air fills with merriment, I wanted to send my warmest wishes to you this holiday season. \n" +
                "The elves and reindeer are bustling with excitement, \n" +
                "preparing for our magical journey to deliver joy around the world.\n" +
                "\n" +
                "The reindeer prance with glee, \n" +
                "their hooves tapping out a festive tune in anticipation of our grand adventure. \n" +
                "Mrs. Claus and I are busy making sure everything is set for our journey, spreading cheer and goodwill to every corner of the globe.\n" +
                "\n" +
                "I've heard wonderful things about your kindness and generosity. \n" +
                "The elves have been working tirelessly to craft special gifts just for you. On Christmas Eve, \n" +
                "as the stars twinkle above, \n" +
                "I'll be guiding my sleigh to your chimney to leave tokens of joy beneath your beautifully adorned Christmas tree.\n" +
                "\n" +
                "May the twinkling lights, \n" +
                "the comforting warmth of family, and the joy of giving fill your heart with happiness this season. \n" +
                "Embrace the spirit of Christmas and let it wrap you in its loving embrace.\n" +
                "\n" +
                "Mrs. Claus and I eagerly await the joy on your face as you discover the surprises we've prepared. \n" +
                "Cherish these moments and remember, \n" +
                "the true magic of Christmas lies in the love we share and the joy we bring to each other's lives.\n" +
                "\n" +
                "Wishing you a Merry Christmas filled with laughter, \n" +
                "love, and the delightful wonder of the holiday season. \n" +
                "May the New Year ahead be adorned with happiness and dreams come true.\n" +
                "\n" +
                "With jolly wishes and a belly full of cheer,\n" +
                "\n" +
                "i have recieved your list of presents " +itemList+"\n" +
                "\n" +
                "make sure you keep being good and keep your ears open,\n" +
                "now say good bye to your Elf and im sure he will see you next year \n" +
                "\n" +
                "\n" +
                "Santa Claus, Mrs. Claus, and all the merry reindeer";
    }


    /**
     * Opens a window to display the generated message.
     *
     * @param message  The message to display
     */
    private static void openMessageWindow(String message) {
        JFrame messageWindow = new JFrame("Santa Message");
        messageWindow.setSize(800, 900);
        messageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon originalIcon = new ImageIcon("./images/santa.png"); // Replace 'image_file.png' with your image file name

        // Resizing the image
        Image img = originalIcon.getImage();
        Image resizedImage = img.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Replace 100, 100 with desired width and height
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        messageWindow.add(imageLabel, BorderLayout.SOUTH);

        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setText(message);

        JButton readMessageButton = new JButton("Read Message");
        readMessageButton.addActionListener(e -> functions.readOutMessage(messageTextArea.getText()));

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
        messagePanel.add(readMessageButton, BorderLayout.SOUTH);

        messageWindow.add(messagePanel);
        messageWindow.setVisible(true);
    }

    /**
     * Main method to start the application.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        run();
        functions.mainStep();
    }
}
