package hr.fer.ppp.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OptionsView extends JPanel {

  private JLabel label;
  private JRadioButton buttonOne;
  private JRadioButton buttonTwo;
  private JTextField textField;

  public OptionsView() {
    TitledBorder tb = BorderFactory.createTitledBorder("Opcije");
    tb.setTitleFont(new Font("Arial", Font.BOLD, 12));
    setBorder(tb);

    label = new JLabel("Odaberite dužinu:");
    label.setFont(Constants.FONT);

    buttonOne = new JRadioButton("Bilo koja dužina", true);
    buttonOne.setFont(Constants.FONT);

    buttonTwo = new JRadioButton("Specificična dužina");
    buttonTwo.setFont(Constants.FONT);

    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(buttonOne);
    buttonGroup.add(buttonTwo);

    textField = new JTextField(10);
    textField.setFont(Constants.FONT);
    textField.setEditable(false);

    GridBagLayout gdl = new GridBagLayout();
    setLayout(gdl);

    GridBagConstraints gdc = new GridBagConstraints();
    gdc.fill = GridBagConstraints.HORIZONTAL;
    gdc.insets = new Insets(10, 10, 10, 10);

    gdc.gridx = 0;
    gdc.gridy = 0;
    add(label, gdc);

    gdc.gridx = 1;
    gdc.gridy = 0;
    add(buttonOne, gdc);

    gdc.gridx = 2;
    gdc.gridy = 0;
    add(buttonTwo, gdc);

    gdc.gridx = 3;
    gdc.gridy = 0;
    add(textField, gdc);

    buttonOne.addActionListener((event) -> {
      textField.setEditable(false);
    });

    buttonTwo.addActionListener((event) -> {
      textField.setEditable(true);
    });

  }

  public JLabel getLabel() {
    return label;
  }

  public void setLabel(JLabel label) {
    this.label = label;
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

  public JTextField getTextField() {
    return textField;
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

}
