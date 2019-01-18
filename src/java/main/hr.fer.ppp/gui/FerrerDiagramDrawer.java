package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FerrerDiagramDrawer implements Runnable {

  private static final int X_INSET = 50;
  private static final int Y_INSET = 50;

  private GraphicsTopView graphicsTopView;
  private DrawCanvas drawCanvas;
  private JScrollPane scrollPane;

  public FerrerDiagramDrawer(GraphicsTopView graphicsTopView, DrawCanvas drawCanvas, JScrollPane scrollPane) {
    this.graphicsTopView = graphicsTopView;
    this.drawCanvas = drawCanvas;
    this.scrollPane = scrollPane;
  }

  @Override
  public void run() {
    clearPanel();
    drawLineOfCircles(graphicsTopView.getTextFieldInputAsListOfNumbers());
  }

  private void clearPanel() {
    SwingUtilities.invokeLater(() -> drawCanvas.clearPanel());
  }

  private void drawLineOfCircles(List<Integer> numberOfCirclesToDraw) {
//    int x = drawCanvas.getX();
//    int y = drawCanvas.getY();

    int x = 0;
    int y = 0;

//    int x = (int)drawCanvas.getLocationOnScreen().getX();
//    int y = (int)drawCanvas.getLocationOnScreen().getY();

    int width = (int)drawCanvas.getBounds().getWidth();
    int height = (int)drawCanvas.getBounds().getHeight();

    int leftX = x + X_INSET;
    int rightX = x + width - X_INSET - 10;

    int topY = y + Y_INSET;
    int bottomY = y + height - Y_INSET;

    int numberOfRows = numberOfCirclesToDraw.size();

//    ravnamo se prema najvecoj vrijednosti
    int distanceBetweenCircles = (rightX - leftX)/numberOfCirclesToDraw.get(0);
    int distanceBetweenCircleRows = (bottomY - topY)/numberOfRows;

    for (int i = 0; i < numberOfRows; i++) {
      int circleY = topY + (i * 40);
//      int circleY = topY + (i * distanceBetweenCircleRows);

      for (int j = 0; j < numberOfCirclesToDraw.get(i); j++) {
        int circleX = leftX + (j * distanceBetweenCircles);

        int a = i;
        SwingUtilities.invokeLater(() -> {
          drawCanvas.setHeight1(a * 50);
          drawCanvas.paintCircle(circleX, circleY);
        });
      }
    }

//    SwingUtilities.invokeLater(()-> {
//      drawCanvas.setPreferredSize(new Dimension(drawCanvas.getPreferredSize().width, numberOfRows * 50));
//      drawCanvas.revalidate();
//      scrollPane.revalidate();
//    });
  }

}
