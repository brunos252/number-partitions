package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;

public class BottomView extends JPanel {

//  brojac
  private JLabel labelOne;
//  vrijednost brojaca
  private JLabel labelTwo;


  public BottomView() {
    labelOne = new JLabel("Broj particija:");
    labelOne.setFont(Constants.FONT);

    labelTwo = new JLabel();
    labelTwo.setFont(Constants.FONT);

    GridBagLayout gdl = new GridBagLayout();
    setLayout(gdl);

    GridBagConstraints gdc = new GridBagConstraints();
    gdc.fill = GridBagConstraints.HORIZONTAL;
    gdc.insets = new Insets(10, 10, 10, 10);

    gdc.gridx = 0;
    gdc.gridy = 0;
    add(labelOne, gdc);

    gdc.gridx = 1;
    gdc.gridy = 0;
    add(labelTwo, gdc);
  }

  public JLabel getLabelOne() {
    return labelOne;
  }

  public void setLabelOne(JLabel labelOne) {
    this.labelOne = labelOne;
  }

  public JLabel getLabelTwo() {
    return labelTwo;
  }

  public void setLabelTwo(JLabel labelTwo) {
    this.labelTwo = labelTwo;
  }

}
