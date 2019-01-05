package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;

public class TopView extends JPanel {

  private JLabel label;
  private JTextField textField;
  private JComboBox<String> comboBox;
  private JButton button;

  public TopView() {
    label = new JLabel("Unesite broj:", SwingConstants.CENTER);
    label.setFont(Constants.FONT);

    textField = new JTextField(20);
    textField.setFont(Constants.FONT);

    String[] algorithms = new String[] {"Algoritam H", "Algoritam P"};
    comboBox = new JComboBox<>(algorithms);
    comboBox.setFont(Constants.FONT);
    comboBox.setSelectedIndex(0);

    button = new JButton("Pokreni");
    button.setFont(Constants.FONT);
    button.setActionCommand("start");

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
    add(textField, gdc);

    gdc.gridx = 2;
    gdc.gridy = 0;
    add(comboBox, gdc);

    gdc.gridx = 3;
    gdc.gridy = 0;
    add(button, gdc);
  }

  public JLabel getLabel() {
    return label;
  }

  public void setLabel(JLabel label) {
    this.label = label;
  }

  public JTextField getTextField() {
    return textField;
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  public JComboBox<String> getComboBox() {
    return comboBox;
  }

  public void setComboBox(JComboBox<String> comboBox) {
    this.comboBox = comboBox;
  }

  public JButton getButton() {
    return button;
  }

  public void setButton(JButton button) {
    this.button = button;
  }



}
