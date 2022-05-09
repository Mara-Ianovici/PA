package frame;

import pannels.ConfigurationPanel;
import pannels.ControlPanel;
import pannels.DrawingPanel;

import javax.swing.*;

import java.awt.*;

public class MainFrame extends JFrame {
    public ConfigurationPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

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

    public void setConfigPanel(ConfigurationPanel configPanel) {
        this.configPanel = configPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void setCanvas(DrawingPanel canvas) {
        this.canvas = canvas;
    }
}
