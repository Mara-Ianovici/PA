package panels;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;

public class DrawingPanel extends JPanel {
    private int clickCounter;
    private int rows, cols;
    private int canvasWidth, canvasHeight;
    private int boardWidth, boardHeight;
    private int cellWidth, cellHeight;
    private int paddingX, paddingY;
    private final int stoneSize = 20;
    private int lastClickedIndex;
    private final MainFrame frame;

    private Point2D point = new Point2D.Double(0, 0);
    private final ArrayList<ArrayList<Ellipse2D>> ovals = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> adjacency = new ArrayList<>();
    private final ArrayList<ArrayList<Color>> colorMatrix = new ArrayList<>();

    private BufferedImage image;
    private Graphics2D offscreen;

    public DrawingPanel(MainFrame frame) {
        this.clickCounter = 0;
        this.frame = frame;
        init((int) frame.configPanel.getHeightSpinner().getValue(), (int)frame.configPanel.getWidthSpinner().getValue());
        createOffscreenImage();
        addMouse();
    }

    private void addMouse(){
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                point = event.getPoint();
                drawImage();
            }
        });
    }

    private void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.paddingX = stoneSize;
        this.paddingY = stoneSize;

        this.canvasHeight = max(400, 40 * rows);
        this.canvasWidth = max(400, 40 * cols);

        this.cellWidth = (canvasWidth - 2 * paddingX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * paddingY) / (rows - 1);

        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        createOffscreenImage();
        initColorMatrix();
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        initAdjacency();
    }

    private void initAdjacency(){
        for (int row = 0; row < rows * cols; row++)
            adjacency.add(new ArrayList<>());
    }

    private void initColorMatrix(){
        for (int row = 0; row < rows; row++) {
            colorMatrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                colorMatrix.get(row).add(Color.LIGHT_GRAY);
            }
        }
        resetColorMatrix();
    }

    protected void resetColorMatrix() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                colorMatrix.get(row).set(col, Color.LIGHT_GRAY);
            }
        }
    }

    private void paintGrid() {
        offscreen.setColor(Color.DARK_GRAY);

        for (int row = 0; row < rows; row++)
            offscreen.drawLine(paddingX, paddingY + row * cellHeight, paddingX + boardWidth, paddingY + row * cellHeight);

        for (int col = 0; col < cols; col++)
            offscreen.drawLine(paddingX + col * cellWidth, paddingY, paddingX + col * cellWidth, boardHeight + paddingY);

        ovals.clear();

        for (int row = 0; row < rows; row++) {
            ovals.add(new ArrayList<>(rows));

            for (int col = 0; col < cols; col++) {

                int height = paddingX + col * cellWidth;
                int width = paddingY + row * cellHeight;

                var ellipse = new Ellipse2D.Double(height - stoneSize / 2.0, width - stoneSize / 2.0, stoneSize, stoneSize);

                ovals.get(row).add(ellipse);
                offscreen.setColor(colorMatrix.get(row).get(col));

                if (colorMatrix.get(row).get(col).equals(Color.LIGHT_GRAY))
                    offscreen.draw(ellipse);
                else {
                    offscreen.draw(ellipse);
                    offscreen.fill(ellipse);
                }
            }
        }
        frame.repaint();
    }

    public void paintLines(){
        offscreen.setColor(Color.LIGHT_GRAY);
        for (int row = 0; row < rows; row++) {

            offscreen.setColor(Color.BLACK);
            offscreen.setStroke(new BasicStroke(5));

            int randomNumberStart = ThreadLocalRandom.current().nextInt(0, cols - 2);
            int randomStart = randomNumberStart * cellWidth + paddingX;
            int randomNumberFinish = ThreadLocalRandom.current().nextInt(randomNumberStart + 1, cols - 1);
            int randomFinish = randomNumberFinish * cellWidth + paddingX;

            offscreen.drawLine(randomStart, paddingY + row * cellHeight, randomFinish, paddingY + row * cellHeight);

            offscreen.setColor(Color.LIGHT_GRAY);
            offscreen.setStroke(new BasicStroke());

            addAdjacencyRow(randomNumberStart + row * cols, randomNumberFinish + row * cols);
        }

        for (int col = 0; col < cols; col++) {
            offscreen.setColor(Color.BLACK);
            offscreen.setStroke(new BasicStroke(5));

            int randomNumberStart = ThreadLocalRandom.current().nextInt(0, rows - 2);
            int randomStart = randomNumberStart * cellHeight + paddingX;
            int randomNumberFinish = ThreadLocalRandom.current().nextInt(randomNumberStart + 1, rows - 1);
            int randomFinish = randomNumberFinish * cellHeight + paddingX;
            offscreen.drawLine(paddingX + col * cellWidth, randomStart, paddingX + col * cellWidth, randomFinish);

            offscreen.setColor(Color.LIGHT_GRAY);
            offscreen.setStroke(new BasicStroke());

            addAdjacencyCol(randomNumberStart * cols + col, randomNumberFinish * cols + col);
        }
    }

    private void addAdjacencyRow(int from, int to){
        for (int index1 = from; index1 < to ; index1++) {
            adjacency.get(index1).add(index1 + 1);
            adjacency.get(index1 + 1).add(index1);
        }
    }

    private void addAdjacencyCol(int from, int to){
        for (int index1 = from; index1 < to ; index1 += cols) {
            adjacency.get(index1).add(index1 + cols);
            adjacency.get(index1 + cols).add(index1);
        }
    }

    private int moveIsPossible (int row, int col){
        int adjIndex = row * cols + col;

        if (clickCounter == 0) {
            for (int index1 = 0; index1 < rows * cols; index1++) {
                if (adjacency.get(index1).contains(adjIndex))
                    return 1;
            }
            JOptionPane.showMessageDialog(frame, "It is the first move. It can only be placed on the allowed edges.");
            return 0;
        }

        else if(adjacency.get(lastClickedIndex).contains(row * cols + col))
            return 1;
        else
        {
            JOptionPane.showMessageDialog(frame, "Oval is not adjacent with the last one added");
            return 0;
        }
    }

    private boolean ovalContainsPoint(Ellipse2D oval){
        return oval.contains(point.getX() - 10, point.getY() - 68);
    }

    public void drawImage() {
        int isPossible;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                var oval = ovals.get(row).get(col);

                if (ovalContainsPoint(oval))
                {
                    isPossible = moveIsPossible(row, col);

                    if(isPossible == 1)
                    {
                        if (colorMatrix.get(row).get(col) == Color.LIGHT_GRAY) {

                            colorMatrix.get(row).set(col, Color.BLUE);
                            clickCounter++;

                            offscreen.setColor(colorMatrix.get(row).get(col));
                            offscreen.fill(ovals.get(row).get(col));

                            frame.repaint();

                            lastClickedIndex = row * cols + col;

                            lastClickedIndex = addNewAutomaticTile(lastClickedIndex);

                            if(hasAnotherMove() == 0)
                            {
                                Color lastColorUsed = colorMatrix.get(lastClickedIndex / cols).get(lastClickedIndex % cols);

                                if(lastColorUsed == Color.RED)
                                    JOptionPane.showMessageDialog(frame, "The game is over. Red won.");
                                else
                                    JOptionPane.showMessageDialog(frame, "The game is over. Blue won.");

                                System.exit(0);
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(frame, "The oval has already been coloured. Choose another one.");
                    }
                }
            }
        }
    }

    private int hasAnotherMove()
    {
        int adjIndex;

        for (int index = 0; index < adjacency.get(lastClickedIndex).size(); index++) {

            adjIndex = adjacency.get(lastClickedIndex).get(index);
            if(colorMatrix.get(adjIndex / cols).get(adjIndex % cols) == Color.LIGHT_GRAY)
                return 1;
        }
        return 0;
    }

    /**
     * A method used by the computer to add the first tile on the board.
     */
    public void addFirstMove(){

        int rowOval, colOval;

        for (int index1 = 0; index1 < rows * cols; index1++) {
            if(adjacency.get(index1).size() > 0)
            {
                rowOval = index1 / cols;
                colOval = index1 % cols;

                colorMatrix.get(rowOval).set(colOval, Color.RED);

                offscreen.setColor(Color.RED);
                offscreen.fill(ovals.get(rowOval).get(colOval));

                frame.repaint();
                return;
            }
        }
    }

    /**
     * A method used for the computer to add a new tile on the board.
     * @param lastIndex the index of the last move made
     * @return the index of the last move if it is successful, -1 otherwise
     */
    private int addNewAutomaticTile(int lastIndex){
        int adjIndex = 0;

        if(hasAnotherMove() != 0)
        {
            for(int index = 0 ;index < adjacency.get(lastIndex).size() ; index ++)
            {
                adjIndex = adjacency.get(lastClickedIndex).get(index);

                int adjRow = adjIndex / cols;
                int adjCol = adjIndex % cols;

                if(colorMatrix.get(adjRow).get(adjCol) == Color.LIGHT_GRAY)
                {
                    colorMatrix.get(adjRow).set(adjCol, Color.RED);

                    offscreen.setColor(colorMatrix.get(adjRow).get(adjCol));
                    offscreen.fill(ovals.get(adjRow).get(adjCol));

                    frame.repaint();

                    return adjIndex;
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(frame, "The game is over. Blue won.");
            System.exit(0);
        }
        return adjIndex;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
        paintGrid();
    }

    @Override
    public void update(Graphics graphics) { }

    public BufferedImage getImage() {
        return image;
    }
}