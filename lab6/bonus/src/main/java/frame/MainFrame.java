package frame;

import panels.ConfigurationPanel;
import panels.ControlPanel;
import panels.DrawingPanel;

import javax.swing.*;

import java.awt.*;

public class MainFrame extends JFrame {
    public ConfigurationPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MainFrame(String name, int rules) {
        super(name);
        init(rules);
    }

    private void init(int rules) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        configPanel = new ConfigurationPanel(this, rules);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        pack();
    }


    public DrawingPanel getCanvas() {
        return canvas;
    }
}
