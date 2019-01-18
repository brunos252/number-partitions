package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawCanvas2 extends JPanel {

  private static final int CIRCLE_RADIUS = 5;

  private int height1;
  private List<Point> points;
  private int length;

  public DrawCanvas2(List<Point> points, int length) {
    this.points = points;
    this.length = length;

    setLayout(new BorderLayout());
  }

  @Override
  public Dimension getPreferredSize() {
    int length2 = length * 50;
    if (length2 > super.getPreferredSize().height){
      return new Dimension(super.getPreferredSize().width, length2);
    } else {
      return super.getPreferredSize();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.PINK);
    if (points != null) {
      for (Point point : points) {
        paintCircle(point.x, point.y);
      }
    }
  }

  public void paintCircle(int locationX, int locationY) {
    Graphics g = getGraphics();

    g.setColor(Color.RED);
    g.fillOval(locationX, locationY, 2*CIRCLE_RADIUS, 2*CIRCLE_RADIUS);
//    a treba?
//    revalidate();
  }

  public void clearPanel() {
    super.paint(getGraphics());
  }

  public int getHeight1() {
    return height1;
  }

  public void setHeight1(int height1) {
    this.height1 = height1;
  }
}
