package hr.fer.ppp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class GraphicsPanel extends JPanel {

  private GraphicsTopView graphicsTopView;
  private GraphicsOptionsView graphicsOptionsView;
  private DrawCanvas drawCanvas;
//  private DrawCanvas2 drawCanvas2;
  private JScrollPane scrollPane;

  public GraphicsPanel() {
    initComponents();
    setLayout(new BorderLayout());

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(graphicsTopView, BorderLayout.PAGE_START);
    topPanel.add(graphicsOptionsView, BorderLayout.CENTER);

    add(topPanel, BorderLayout.PAGE_START);
//    add(drawCanvas, BorderLayout.CENTER);
    add(scrollPane, BorderLayout.CENTER);
    initListeners();
  }

  private void initComponents() {
    graphicsTopView = new GraphicsTopView();
    graphicsOptionsView = new GraphicsOptionsView();

//    preffered size?
    drawCanvas = new DrawCanvas();
//    drawCanvas2 = new DrawCanvas2(null, 0);
    scrollPane = new JScrollPane(drawCanvas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//    scrollPane = new JScrollPane(drawCanvas2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//    scrollPane.setLayout(null);
//    scrollPane.setPreferredSize(drawCanvas.getPreferredSize());
  }

  private void initListeners() {
//    graphicsOptionsView.getButtonOne().addActionListener((event) -> {
//      if (checkIfValidInput()) {
//        new Thread(new FerrerDiagramDrawer(graphicsTopView, drawCanvas)).start();
//      } else {
//        createErrorDialog("Unos particije nije ispravan!");
//      }
//    });
    graphicsOptionsView.getStartButton().addActionListener((event) -> {
      if (checkIfValidInput()) {
        if (graphicsOptionsView.getButtonOne().isSelected()) {
          new Thread(new FerrerDiagramDrawer(graphicsTopView, drawCanvas)).start();
        } else if (graphicsOptionsView.getButtonTwo().isSelected()) {
          new Thread(new ConjugatePartitionDrawer(graphicsTopView, drawCanvas)).start();
        }
      }
    });
//    buttonOne.addActionListener((event) -> );
//    buttonOne.addActionListener((event) -> );
  }

  private boolean checkIfValidInput() {
    return !Arrays.stream(graphicsTopView.getTextField().getText().split(","))
            .anyMatch(number -> !checkIfShort(number.trim()));
  }

  private boolean checkIfShort(String text) {
    Short num = null;
    try {
      num = Short.parseShort(text);
    } catch (NumberFormatException ex) {
      return false;
    }
//    broj mora biti prirodan, drugi uvjet je vec provjeren gore
    if (!(num > 0 && num < Short.MAX_VALUE)) return false;
    return true;
  }

  private void createErrorDialog(String text) {
    JLabel label = new JLabel(text);
    label.setFont(Constants.FONT);
    JOptionPane.showMessageDialog(this,
            label, "ERROR", ERROR_MESSAGE);
  }

  public GraphicsTopView getGraphicsTopView() {
    return graphicsTopView;
  }

  public void setGraphicsTopView(GraphicsTopView graphicsTopView) {
    this.graphicsTopView = graphicsTopView;
  }

  public DrawCanvas getDrawCanvas() {
    return drawCanvas;
  }

  public void setDrawCanvas(DrawCanvas drawCanvas) {
    this.drawCanvas = drawCanvas;
  }
}
