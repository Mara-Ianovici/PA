package pannels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int paddingX, paddingY;
    int stoneSize = 20;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init((int)frame.configPanel.getHeightSpinner().getValue(), (int)frame.configPanel.getWidthSpinner().getValue());
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.paddingX = stoneSize + 10;
        this.paddingY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * paddingX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * paddingY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(graphics2D);
    }

    private void paintGrid(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);

        for (int row = 0; row < rows; row++)
            graphics2D.drawLine(paddingX, paddingY + row * cellHeight, paddingX + boardWidth, paddingY + row * cellHeight);

        for (int col = 0; col < cols; col++)
            graphics2D.drawLine(paddingX + col * cellHeight, paddingY, paddingX + col * cellHeight, paddingY + boardWidth);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int height = paddingX + col * cellWidth;
                int width = paddingY + row * cellHeight;
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.drawOval(height - stoneSize / 2, width - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }
}