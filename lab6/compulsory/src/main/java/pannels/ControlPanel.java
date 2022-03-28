package pannels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        setLayout(new GridLayout(1, 4));
        exitBtn.addActionListener(this::exitGame);

        add(saveButton);
        add(loadButton);
        add(exitBtn);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
