package hr.fer.ppp.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class App extends JFrame {

  private TopView topView;
  private OptionsView optionsView;
  private CentralView centralView;
  private BottomView bottomView;
  private GraphicsPanel graphicsPanel;

  public App() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Particije prirodnog broja");

    initComponents();
    initGUI();
  }

  private void initComponents() {
    topView = new TopView();
    optionsView = new OptionsView();
    centralView = new CentralView();
    bottomView = new BottomView();
  }

  private void initGUI() {
    Container cp = getContentPane();

    JTabbedPane tabbedPane = new JTabbedPane();
//    tabbedPane.add()

    JPanel algorithmPanel = new JPanel(new BorderLayout());


    JPanel top = new JPanel(new GridLayout(2, 1));
    top.add(topView);
    top.add(optionsView);

    algorithmPanel.add(top, BorderLayout.PAGE_START);
    algorithmPanel.add(centralView, BorderLayout.CENTER);
    algorithmPanel.add(bottomView, BorderLayout.PAGE_END);

//    cp.add(top, BorderLayout.PAGE_START);
//
//    cp.add(centralView, BorderLayout.CENTER);
//
//    cp.add(bottomView, BorderLayout.PAGE_END);

    GraphicsPanel graphicsPanel = new GraphicsPanel();
//    DrawCanvas canvas = new DrawCanvas();
//    canvas.setPreferredSize(new Dimension(400, 400));
    tabbedPane.add("Algoritmi", algorithmPanel);
    tabbedPane.add("Graficki prikaz", graphicsPanel);

    cp.add(tabbedPane, BorderLayout.CENTER);
//    prikazi options view ovisno o odabranom algoritmu
    topView.getComboBox().addItemListener((event) -> {
      if (event.getStateChange() == ItemEvent.SELECTED) {
        String item = (String)event.getItem();

        if(item.equals("Algoritam H")) {
          top.add(optionsView);
          optionsView.setVisible(true);
        } else {
          top.remove(optionsView);
          optionsView.setVisible(false);
        }
        top.revalidate();
      }
    });

    topView.getButton().addActionListener(event -> {
      SwingWorker<Long, String> task = null;

        if(event.getActionCommand().equals("start")) {
          if (!checkIfValidInput(topView.getTextField().getText())) return;

//          izbrisi prijasnji prikaz particija ako postoji
          centralView.getTextArea().setText(null);
//          izbrisi vrijednost brojaca
          bottomView.getLabelTwo().setText(null);

          resolveTask(task);
        }

        if (event.getActionCommand().equals("stop")) {
          // zaustavi workera
          if (task.cancel(true)) {
            prepareApp();
          }
        }
    });

    centralView.getTextArea().addCaretListener((event) -> {
        if (topView.getButton().getActionCommand().equals("start")) {
//          System.out.println(getPartitionFromTextArea(event.getDot()));
          graphicsPanel.getGraphicsTopView().getTextField().setText(getPartitionFromTextArea(event.getDot()));
        }
    });
  }

  private String getPartitionFromTextArea(int position) {
    String partitionsText = centralView.getTextArea().getText();

    int startOfPartition = 0;
    int endOfPartition = 0;

    char[] partitionsTextChars = partitionsText.toCharArray();

//    pronadi pocetak particije
    for (int i = position - 1; i > 0; i--) {
      if (partitionsTextChars[i] == '\n') {
        startOfPartition = i + 1;
        break;
      }
    }

//    pronadi kraj particije
    for (int i = position; i < partitionsTextChars.length; i++) {
      if (partitionsTextChars[i] == '\n') {
        endOfPartition = i;
        break;
      }
    }

    String resultPartition = partitionsText.substring(startOfPartition, endOfPartition);

    return resultPartition.trim();
  }

  private void resolveTask(SwingWorker<Long, String> task) {
    String algorithmName = (String)getTopView().getComboBox().getSelectedItem();

    if (algorithmName.equals("Algoritam P")) {
      task = new TaskAlgorithmP(Short.parseShort(topView.getTextField().getText()), this);
    } else if (algorithmName.equals("Algoritam H") && optionsView.getButtonTwo().isSelected()){
//      provjeri textfield gdje se navodi duljina i provjeri je li duljina manja od unesenog broja
      if(!checkIfValidInput(optionsView.getTextField().getText())
              || !checkIfValidLength() || !checkIfOne()) return;

      short length = Short.parseShort(optionsView.getTextField().getText());
      task = new TaskAlgorithmH(Short.parseShort(topView.getTextField().getText()), length,this);
    } else if (algorithmName.equals("Algoritam H") && optionsView.getButtonOne().isSelected()) {
      task = new TaskAlgorithmHAnyLength(Short.parseShort(topView.getTextField().getText()),this);
    }

    JButton start = topView.getButton();
    start.setText("Zaustavi");
    start.setActionCommand("stop");
    task.execute();
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

  private boolean checkIfValidInput(String text) {
    String message = null;

    boolean flag = false;
    if (text.isEmpty()) {
      message = "Ulaz je prazan!";
      flag = true;
    }
    if (!checkIfShort(text)) {
      message = "Ulaz nije ispravan broj!";
      flag = true;
    }
    if (flag) {
      createErrorDialog(message);
    }

    return !flag;
  }

  private boolean checkIfValidLength() {
    Short number = Short.parseShort(topView.getTextField().getText());
    Short length = Short.parseShort(optionsView.getTextField().getText());

    if (length > number) {
      createErrorDialog("Duljina ne smije biti ve??a od unesenog broja!");
      return false;
    }

    return true;
  }

  private boolean checkIfOne() {
    if (Short.parseShort(optionsView.getTextField().getText()) == 1) {
      createErrorDialog("Algoritam H ne radi za duljinu 1!");
      return false;
    }

    return  true;
  }

  private void createErrorDialog(String text) {
    JLabel label = new JLabel(text);
    label.setFont(Constants.FONT);
    JOptionPane.showMessageDialog(this,
            label, "ERROR", ERROR_MESSAGE);
  }

  private void prepareApp() {
    JButton start = topView.getButton();
    start.setText("Pokreni");
    start.setActionCommand("start");

    optionsView.getButtonOne().setSelected(true);

    optionsView.getTextField().setEditable(false);
    optionsView.getTextField().setText(null);
  }

  public TopView getTopView() {
    return topView;
  }

  public void setTopView(TopView topView) {
    this.topView = topView;
  }

  public OptionsView getOptionsView() {
    return optionsView;
  }

  public void setOptionsView(OptionsView optionsView) {
    this.optionsView = optionsView;
  }

  public CentralView getCentralView() {
    return centralView;
  }

  public void setCentralView(CentralView centralView) {
    this.centralView = centralView;
  }

  public BottomView getBottomView() {
    return bottomView;
  }

  public void setBottomView(BottomView bottomView) {
    this.bottomView = bottomView;
  }

}
