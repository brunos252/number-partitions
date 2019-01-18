package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FerrerDiagramDrawer2 implements Runnable {

  private static final int X_INSET = 50;
  private static final int Y_INSET = 50;

  private GraphicsTopView graphicsTopView;
  private DrawCanvas drawCanvas2;
  private JScrollPane scrollPane;

  public FerrerDiagramDrawer2(GraphicsTopView graphicsTopView, DrawCanvas2 drawCanvas, JScrollPane scrollPane) {
    this.graphicsTopView = graphicsTopView;
    this.drawCanvas2 = drawCanvas2;
    this.scrollPane = scrollPane;
  }

  @Override
  public void run() {
    clearPanel();
    drawLineOfCircles(graphicsTopView.getTextFieldInputAsListOfNumbers());
  }

  private void clearPanel() {
    SwingUtilities.invokeLater(() -> drawCanvas2.clearPanel());
  }

  private void drawLineOfCircles(List<Integer> numberOfCirclesToDraw) {
    int x = 0;
    int y = 0;

    int width = (int)drawCanvas2.getBounds().getWidth();
    int height = (int)drawCanvas2.getBounds().getHeight();

    int leftX = x + X_INSET;
    int rightX = x + width - X_INSET - 10;

    int topY = y + Y_INSET;
    int bottomY = y + height - Y_INSET;

    int numberOfRows = numberOfCirclesToDraw.size();

//    ravnamo se prema najvecoj vrijednosti
    int distanceBetweenCircles = (rightX - leftX)/numberOfCirclesToDraw.get(0);
    int distanceBetweenCircleRows = (bottomY - topY)/numberOfRows;

    List<Point> points = new ArrayList<>();
    for (int i = 0; i < numberOfRows; i++) {
//      int circleY = topY + (i * 40);
      int circleY = topY + (i * distanceBetweenCircleRows);

      for (int j = 0; j < numberOfCirclesToDraw.get(i); j++) {
        int circleX = leftX + (j * distanceBetweenCircles);

        points.add(new Point(circleX, circleY));
//        SwingUtilities.invokeLater(() -> {
//          drawCanvas2 = new DrawCanvas2(points)
//        });
      }
    }
    SwingUtilities.invokeLater(() -> new DrawCanvas2(points, numberOfRows));

//    SwingUtilities.invokeLater(()-> {
//      drawCanvas.setPreferredSize(new Dimension(drawCanvas.getPreferredSize().width, numberOfRows * 50));
//      drawCanvas.revalidate();
//      scrollPane.revalidate();
//    });
  }

}

