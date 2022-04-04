package panels;

import frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * A class used for the bottom part of the application.
 * It has functionalities like save and exit.
 */
public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private final JButton exitBtn = new JButton("Exit");
    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        exitBtn.addActionListener(this::exitGame);
        saveButton.addActionListener(this::saveCurrentCanvas);

        add(saveButton);
        add(loadButton);
        add(exitBtn);
    }

    /**
     * Saves the current state of the game as a png.
     * It captures a picture of the drawing panel using a BufferedImage.
     * @param actionEvent
     */
    private void saveCurrentCanvas(ActionEvent actionEvent){
        try {
            ImageIO.write(frame.getCanvas().getImage(), "PNG", new File("shot.png"));

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    private void exitGame(ActionEvent actionEvent) {
        frame.dispose();
    }
}
