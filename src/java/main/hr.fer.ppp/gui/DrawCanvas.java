package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;

public class DrawCanvas extends JPanel {

  private static final int CIRCLE_RADIUS = 5;

  private int height1;

  public DrawCanvas() {
    setLayout(new BorderLayout());
  }

  @Override
  public Dimension getPreferredSize() {
    if (height1 > super.getPreferredSize().height){
      return new Dimension(super.getPreferredSize().width, height1);
    } else {
      return super.getPreferredSize();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.PINK);
  }

  public void paintCircle(int locationX, int locationY) {
    Graphics g = getGraphics();

    g.setColor(Color.RED);
    g.fillOval(locationX, locationY, 2*CIRCLE_RADIUS, 2*CIRCLE_RADIUS);
    revalidate();
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
