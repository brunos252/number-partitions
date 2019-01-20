package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;

public class DrawCanvas extends JPanel {

  private static final int CIRCLE_RADIUS = 8;


  public DrawCanvas() {
    setLayout(new BorderLayout());
  }

//  @Override
//  public Dimension getPreferredSize() {
//    if (height1 > super.getPreferredSize().height){
//      return new Dimension(super.getPreferredSize().width, height1);
//    } else {
//      return super.getPreferredSize();
//    }
//  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.PINK);
  }

  public void paintCircle(int locationX, int locationY, Color circleColor) {
    Graphics g = getGraphics();

    g.setColor(circleColor);
    g.fillOval(locationX, locationY, 2*CIRCLE_RADIUS, 2*CIRCLE_RADIUS);
    revalidate();
  }

  public void clearPanel() {
    super.paint(getGraphics());
  }

}
