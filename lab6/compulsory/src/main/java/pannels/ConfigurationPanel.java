package pannels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigurationPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner widthSpinner;
    JSpinner heightSpinner;
    JButton createGameButton;

    public ConfigurationPanel(MainFrame frame) {
        this.frame = frame;
        init();
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

    private void createGame(ActionEvent actionEvent) {
        System.out.println("Game started.");
    }
}

