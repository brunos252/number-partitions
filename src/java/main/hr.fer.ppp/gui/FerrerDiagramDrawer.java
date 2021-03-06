package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FerrerDiagramDrawer implements Runnable {

  private static final int X_INSET = 50;
  private static final int Y_INSET = 50;

  private GraphicsTopView graphicsTopView;
  private DrawCanvas drawCanvas;

  public FerrerDiagramDrawer(GraphicsTopView graphicsTopView, DrawCanvas drawCanvas) {
    this.graphicsTopView = graphicsTopView;
    this.drawCanvas = drawCanvas;
  }

  @Override
  public void run() {
    clearPanel();
    drawLineOfCircles(graphicsTopView.getTextFieldInputAsListOfNumbers());
  }

  private void clearPanel() {
    SwingUtilities.invokeLater(() -> drawCanvas.clearPanel());
  }

  @SuppressWarnings("Duplicates")
  private void drawLineOfCircles(List<Integer> numberOfCirclesToDraw) {
    int x = 0;
    int y = 0;

    int width = (int)drawCanvas.getBounds().getWidth();
    int height = (int)drawCanvas.getBounds().getHeight();

    int leftX = x + X_INSET;
    int rightX = x + width - X_INSET;

    int topY = y + Y_INSET;
    int bottomY = y + height - Y_INSET;

    int numberOfRows = numberOfCirclesToDraw.size();

//    ravnamo se prema najvecoj vrijednosti
    int distanceBetweenCircleColumn = (rightX - leftX)/numberOfCirclesToDraw.get(0);
    int distanceBetweenCircleRows = (bottomY - topY)/numberOfRows;

//    ispisujemo redak po redak, pomicemo y koordinatu i  potom u svakom retku x koordinatu
    for (int i = 0; i < numberOfRows; i++) {
      int circleY = topY + (i * distanceBetweenCircleRows);

      for (int j = 0; j < numberOfCirclesToDraw.get(i); j++) {
        int circleX = leftX + (j * distanceBetweenCircleColumn);

        SwingUtilities.invokeLater(() -> {
          drawCanvas.paintCircle(circleX, circleY, Color.RED);
        });
      }
    }
  }

}
