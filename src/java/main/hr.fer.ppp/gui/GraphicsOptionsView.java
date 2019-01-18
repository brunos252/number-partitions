package hr.fer.ppp.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GraphicsOptionsView extends JPanel {

  private JRadioButton buttonOne;
  private JRadioButton buttonTwo;
  private JRadioButton buttonThree;
  private JButton startButton;

  public GraphicsOptionsView() {
    initComponents();
    initGUI();
  }

  private void initComponents() {
    buttonOne = new JRadioButton("Ferrerov dijagram", true);
    buttonOne.setFont(Constants.FONT);

    buttonTwo = new JRadioButton("Konjugirana particija");
    buttonTwo.setFont(Constants.FONT);

    buttonThree = new JRadioButton("Durfeeov kvadrat");
    buttonThree.setFont(Constants.FONT);

    startButton = new JButton("Prikaži");
    startButton.setFont(Constants.FONT);
  }

  private void initGUI() {
    TitledBorder tb = BorderFactory.createTitledBorder("Prikaz");
    tb.setTitleFont(new Font("Arial", Font.BOLD, 12));
    setBorder(tb);

    GridBagLayout gdl = new GridBagLayout();
    setLayout(gdl);

    GridBagConstraints gdc = new GridBagConstraints();
    gdc.fill = GridBagConstraints.HORIZONTAL;
    gdc.insets = new Insets(10, 10, 10, 10);

    gdc.gridx = 0;
    gdc.gridy = 0;
    add(buttonOne, gdc);

    gdc.gridx = 1;
    gdc.gridy = 0;
    add(buttonTwo, gdc);

    gdc.gridx = 2;
    gdc.gridy = 0;
    add(buttonThree, gdc);

    gdc.gridx = 3;
    gdc.gridy = 0;
    add(startButton, gdc);

    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(buttonOne);
    buttonGroup.add(buttonTwo);
    buttonGroup.add(buttonThree);
  }

  public JRadioButton getButtonOne() {
    return buttonOne;
  }

  public void setButtonOne(JRadioButton buttonOne) {
    this.buttonOne = buttonOne;
  }

  public JRadioButton getButtonTwo() {
    return buttonTwo;
  }

  public void setButtonTwo(JRadioButton buttonTwo) {
    this.buttonTwo = buttonTwo;
  }

  public JRadioButton getButtonThree() {
    return buttonThree;
  }

  public void setButtonThree(JRadioButton buttonThree) {
    this.buttonThree = buttonThree;
  }

  public JButton getStartButton() {
    return startButton;
  }

  public void setStartButton(JButton startButton) {
    this.startButton = startButton;
  }
}
