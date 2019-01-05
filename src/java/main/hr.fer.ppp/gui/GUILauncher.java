package hr.fer.ppp.gui;

import javax.swing.*;

public class GUILauncher {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      App app = new App();

      app.pack();
      app.setLocationRelativeTo(null);
//      app.setResizable(false);
      app.setVisible(true);
    });
  }

}
