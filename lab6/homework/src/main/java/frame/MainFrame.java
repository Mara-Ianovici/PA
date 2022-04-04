package frame;

import panels.ConfigurationPanel;
import panels.ControlPanel;
import panels.DrawingPanel;

import javax.swing.*;

import java.awt.*;

/**
 * Contains all the components from the application.
 */
public class MainFrame extends JFrame {
    private ConfigurationPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MainFrame(String name) {
        super(name);
        init();
    }

    /**
     * Positions all the elements in the application's window.
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigurationPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        pack();
    }

    public ConfigurationPanel getConfigPanel() {
        return configPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }
}
