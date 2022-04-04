package panels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigurationPanel extends JPanel {
    final MainFrame frame;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private final int rules;

    public ConfigurationPanel(MainFrame frame, int rules) {
        this.frame = frame;
        this.rules = rules;

        init();
    }

    public JSpinner getWidthSpinner() {
        return widthSpinner;
    }

    public JSpinner getHeightSpinner() {
        return heightSpinner;
    }

    private void init() {
        JLabel label = new JLabel("Grid size:");
        widthSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        JButton createGameButton = new JButton("Create game");
        createGameButton.addActionListener(this::createGame);

        add(label);
        add(widthSpinner);
        add(heightSpinner);
        add(createGameButton);
    }

    private void createGame(ActionEvent actionEvent) {
        System.out.println("Game created.");

        int newColNumber = (int)widthSpinner.getValue();
        int newRowNumber = (int)heightSpinner.getValue();

        frame.getCanvas().init(newColNumber, newRowNumber);

        frame.getCanvas().resetColorMatrix();
        frame.repaint();

        frame.getCanvas().paintLines();
        frame.repaint();

        if(rules == 1)
            frame.getCanvas().addFirstMove(); //if the AI should place first, the first move is made

        frame.pack();
    }
}

