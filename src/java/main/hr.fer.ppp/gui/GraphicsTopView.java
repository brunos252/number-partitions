package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphicsTopView extends JPanel {

  private JLabel label;
  private JTextField textField;

  public GraphicsTopView() {
    label = new JLabel("Unesite particiju:", SwingConstants.CENTER);
    label.setFont(Constants.FONT);

    textField = new JTextField(30);
    textField.setFont(Constants.FONT);
    textField.setToolTipText("Brojevi particije moraju biti odvojeni zarezom, npr. 4, 2, 1");


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
  }

  public List<Integer> getTextFieldInputAsListOfNumbers() {
    return Arrays.stream(textField.getText().split(","))
            .map(num -> Integer.parseInt(num.trim()))
            .collect(Collectors.toList());
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
}
