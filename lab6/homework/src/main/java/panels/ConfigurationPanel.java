package panels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A class used for the top part of the application.
 * It contains 2 spinners that set board's dimensions and a Create button that starts the game.
 */
public class ConfigurationPanel extends JPanel {
    private final MainFrame frame;
    private JLabel label;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JButton createGameButton;

    public ConfigurationPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Initialises and adds the label, spinner and button of the application.
     */
    private void init() {
        label = new JLabel("Grid size:");
        widthSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        createGameButton = new JButton("Create game");
        createGameButton.addActionListener(this::createGame);

        add(label);
        add(widthSpinner);
        add(heightSpinner);
        add(createGameButton);
    }

    /**
     * Provides functionality for createGame button.
     * It sets the click counter to 0 and initialises the board components with default values.
     * @param actionEvent
     */
    private void createGame(ActionEvent actionEvent) {
        System.out.println("Game created.");

        int newColNumber = (int)widthSpinner.getValue();
        int newRowNumber = (int)heightSpinner.getValue();

        frame.getCanvas().setClickCounter(0);
        frame.getCanvas().init(newColNumber, newRowNumber);

        frame.getCanvas().resetColorMatrix();
        frame.repaint();

        frame.getCanvas().paintLines();
        frame.repaint();

        frame.pack();
    }

    public MainFrame getFrame() {
        return frame;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JSpinner getWidthSpinner() {
        return widthSpinner;
    }

    public void setWidthSpinner(JSpinner widthSpinner) {
        this.widthSpinner = widthSpinner;
    }

    public JSpinner getHeightSpinner() {
        return heightSpinner;
    }

    public void setHeightSpinner(JSpinner heightSpinner) {
        this.heightSpinner = heightSpinner;
    }

    public JButton getCreateGameButton() {
        return createGameButton;
    }

    public void setCreateGameButton(JButton createGameButton) {
        this.createGameButton = createGameButton;
    }
}

