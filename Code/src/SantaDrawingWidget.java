import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SantaDrawingWidget extends JPanel {

    private BufferedImage image;
    private int animationDelay = 100; // Milliseconds between drawing each part
    private int step = 0; // Current step in the animation

    private Timer timer;

    public SantaDrawingWidget() {

        setPreferredSize(new Dimension(800, 600));

        // Create the buttons
        JButton saveButton = new JButton("Save");
        JButton returnButton = new JButton("Return");

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Panel to hold the buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        buttonPanel.add(returnButton);

        // Add the button panel to the bottom of the main panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveImage();
                closeFrame();
            }
        });
        timer = new Timer(animationDelay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Draw next step of Santa
                step++;
                repaint();

                if (step == 10) { // Adjust the number of steps as needed
                    // Stop the timer after the animation is complete
                    timer.stop();
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFrame();
            }
        });
    }
    private void closeFrame() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Set up delay between lines (milliseconds)
        int lineDelay = 300;
        // Draw Santa Claus
        drawSanta(g, centerX, centerY, lineDelay);
    }
    private void drawSanta(Graphics g, int centerX, int centerY, int lineDelay) {
        // Draw Santa Claus
        switch (step) {
            case 0:
                drawCircle(g, centerX - 50, centerY - 50, 100, 100, Color.RED, lineDelay); // Santa's face
                drawCircle(g, centerX - 35, centerY - 25, 20, 20, Color.WHITE, lineDelay); // Left eye
                drawCircle(g, centerX + 5, centerY - 25, 20, 20, Color.WHITE, lineDelay); // Right eye
                drawRect(g, centerX - 35, centerY + 10, 60, 20, Color.RED, lineDelay); // Santa's hat
                drawRect(g, centerX - 30, centerY - 10, 50, 40, Color.RED, lineDelay); // Santa's hat
                break;
            case 1:
                // Draw Santa's body
                drawCircle(g, centerX - 30, centerY + 40, 50, 80, Color.RED, lineDelay); // Stomach

                // Draw Santa's arms
                drawRect(g, centerX - 65, centerY + 50, 35, 10, Color.RED, lineDelay); // Left arm
                drawRect(g, centerX + 55, centerY + 50, 35, 10, Color.RED, lineDelay); // Right arm
                break;
            case 2:
            // Draw Santa's legs
            drawRect(g, centerX - 15, centerY + 120, 10, 50, Color.RED, lineDelay); // Left leg
            drawRect(g, centerX + 5, centerY + 120, 10, 50, Color.RED, lineDelay); // Right leg
                break;
        }
    }
    public void startAnimation() {
        timer.start();
    }

    private void drawCircle(Graphics g, int x, int y, int width, int height, Color color, int lineDelay) {
        g.setColor(color);
        for (int i = 0; i < height; i++) {
            g.drawOval(x, y, width, i);
            delay(lineDelay);
        }
    }

    private void drawRect(Graphics g, int x, int y, int width, int height, Color color, int lineDelay) {
        g.setColor(color);
        for (int i = 0; i < height; i++) {
            g.drawRect(x, y, width, i);
            delay(lineDelay);
        }
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveImage() {
        // Create a BufferedImage of the panel and save it as an image file
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        this.paint(g2d);
        g2d.dispose();

        try {
            File output = new File("santa_image.png"); // Change the file name and format if needed
            ImageIO.write(image, "png", output);
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void santaRun() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Santa Drawing Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SantaDrawingWidget santaDrawingWidget = new SantaDrawingWidget();
            frame.add(santaDrawingWidget);

            frame.pack();
            frame.setVisible(true);

            // Start the animation when the frame becomes visible
            santaDrawingWidget.startAnimation();
        });
    }

    private void drawSanta() {
        repaint(); // This will call the paintComponent method
    }
}

