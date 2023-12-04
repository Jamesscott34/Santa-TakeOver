import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Windows {
    private static String savedName = ""; // Initialize with empty string
    private static List<String> itemList; // Initialize the list

    private static boolean nameEntered = false;
    private static boolean listEntered = false;

    public static void run() {
        openNameWindow();
    }

    private static void openNameWindow() {
        JFrame nameWindow = new JFrame("Enter Name");
        nameWindow.setSize(400, 200);
        nameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel nameLabel = new JLabel("Name: ");
        JTextField entryName = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            savedName = entryName.getText();
            nameEntered = true;
            nameWindow.dispose();
            openListWindow();
        });

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(entryName, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);

        nameWindow.add(panel);
        nameWindow.setVisible(true);
    }

    private static void openListWindow() {
        JFrame listWindow = new JFrame("Enter List");
        listWindow.setSize(400, 300);
        listWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel listLabel = new JLabel("Enter your list:");
        JTextArea listEntry = new JTextArea();

        JButton saveListButton = new JButton("Save List");
        saveListButton.addActionListener(e -> {
            itemList = functions.getListItems(); // Get the list items
            listEntered = true;
            listWindow.dispose();
            start();
        });

        panel.add(listLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(listEntry), BorderLayout.CENTER);
        panel.add(saveListButton, BorderLayout.SOUTH);

        listWindow.add(panel);
        listWindow.setVisible(true);
    }

    private static void start() {
        if (nameEntered && listEntered) {
            String message = generateMessage();
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
        return "Dear " + savedName + ",\n" +
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
                "i have recieved your list of presents " + itemList + "\n" +
                "\n" +
                "make sure you keep being good and keep your ears open,\n" +
                "now say good bye to your Elf and im sure he will see you next year \n" +
                "\n" +
                "\n" +
                "Santa Claus, Mrs. Claus, and all the merry reindeer";
    }


    /**
     * Opens a window to display the generated message.
     */
    public static void openMessageWindow(String message) {
        JFrame messageWindow = new JFrame("Santa Message");
        messageWindow.setSize(600, 500);
        messageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea messageTextArea = new JTextArea(message);
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);

        JButton readButton = new JButton("Read "); // New button for reading message
        readButton.addActionListener(e -> {
            // Read out the message using FreeTTS
            functions.readOutMessage(message);
        });

        JButton drawButton = new JButton("Draw Santa");
        drawButton.addActionListener(e -> {
            openSantaDrawing();

        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(readButton);
        buttonPanel.add(drawButton);

        messageWindow.setLayout(new BorderLayout());
        messageWindow.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
        messageWindow.add(buttonPanel, BorderLayout.SOUTH);

        messageWindow.setVisible(true);
    }

    private static void openSantaDrawing() {
        SantaDrawingWidget.santaRun();
    }


}


