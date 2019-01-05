package hr.fer.ppp.gui;

import javax.swing.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TaskAlgorithmP extends SwingWorker<Long, String> {

  private short[] a;
  private short m, q, x;
  private short n;
  private long counter;
  private boolean[] p;

  private App app;

  public  TaskAlgorithmP(short n, App app) {
    a = new short[n + 1];
    this.n = n;
    counter = 0;
    p = new boolean[6];
    p[0] = true;

    this.app = app;
  }

  private long algorithm() {
    while(true) {

      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      //P1
      if(p[0]) {
        for(m = 2; m <= n; m++) {
          a[m] = 1;
        }
        m = 1;
        a[0] = 0;

        p[0] = false;
        p[1] = true;
      }

      //P2
      if(p[1]) {
        a[m] = n;
        q = (short) (m - (n == 1 ? 1 : 0));

        p[1] = false;
        p[2] = true;
      }

      //P3
      if(p[2]) {
        counter++;
        printPartition();
        p[2] = false;
        if(a[q] == 2)
          p[3] = true;
        else
          p[4] = true;
      }

      //P4
      if(p[3]) {
        a[q] = 1;
        q--;
        m++;
        p[3] = false;
        p[2] = true;
      }

      //P5
      if(p[4]) {
        if(q == 0) {
          return counter;
        } else {
          x = (short) (a[q] - 1);
          a[q] = x;
          n = (short) (m - q + 1);
          m = (short) (q + 1);
          p[4] = false;
          p[5] = true;
        }
      }

      //P6
      if(p[5]) {
        if(n <= x) {
          p[5] = false;
          p[1] = true;
        } else {
          a[m] = x;
          m++;
          n = (short) (n - x);
          //p[5] = true;
        }
      }

    }
  }

  private void printPartition() {
    List<String> nums = new LinkedList<>();
    for(int i = 1; i <= m; i++) {
      nums.add(String.valueOf(a[i]));
    }
    String partOfPartition = nums
            .stream()
            .collect(Collectors.joining(", "));

    publish(partOfPartition);
  }

  @Override
  protected Long doInBackground() throws Exception {
    return algorithm();
  }

  @Override
  protected void process(List<String> chunks) {
//    System.out.println("tu sam");
//    textArea.append(chunks.get(chunks.size()-1) + System.lineSeparator());
    for (String partition : chunks) {
      app.getCentralView().getTextArea().append(partition + System.lineSeparator());
    }
  }

  @Override
  protected void done() {
//    vrati tipku u top viewu u ispravno stanje
    JButton start = app.getTopView().getButton();
    start.setText("Pokreni");
    start.setActionCommand("start");

    app.getOptionsView().getButtonOne().setSelected(true);

    app.getOptionsView().getTextField().setEditable(false);
    app.getOptionsView().getTextField().setText(null);

//    postavi brojac
    try {
      app.getBottomView().getLabelTwo().setText(String.valueOf(get()));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}






