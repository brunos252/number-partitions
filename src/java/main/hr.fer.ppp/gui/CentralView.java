package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;

public class CentralView extends JPanel {

  private JTextArea textArea;
  private JScrollPane scrollPane;

  public CentralView() {
    textArea = new JTextArea(20, 50);
    textArea.setEditable(false);
    textArea.setFont(new Font("Arial", Font.PLAIN, 18));

    scrollPane = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    add(scrollPane, BorderLayout.CENTER);
  }

  public JTextArea getTextArea() {
    return textArea;
  }

  public void setTextArea(JTextArea textArea) {
    this.textArea = textArea;
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public void setScrollPane(JScrollPane scrollPane) {
    this.scrollPane = scrollPane;
  }
}
