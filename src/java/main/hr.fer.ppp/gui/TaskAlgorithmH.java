package hr.fer.ppp.gui;

import javax.swing.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TaskAlgorithmH extends SwingWorker<Long, String> {

  private short[] a;
  private long counter;
  private short n, m, j, s, x;
  boolean[] H;

  private App app;

  public TaskAlgorithmH(short n, short m, App app) {
    if(n > 1 && m > 1) {
      a = new short[m + 2];
      counter = 0;
      this.n = n;
      this.m = m;
      H = new boolean[6];
      H[0] = true;
    }

    this.app = app;
  }

  private long algorithm() {
    while(true) {
      /*
       * ne koriste se funkcije za svaki korak jer dolazi do stackoverflowa,
       * ne koristi se switch jer cesto treba pozvati korak koji je direktno ispod pa da se ne ide u sljedecu iteraciju petlje
       */
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if(H[0]) {
        a[1] = (short) (n - m + 1);
        for(int j = 2; j <= m; j++) {
          a[j] = 1;
        }
        a[m + 1] = -1;

        H[0] = false;
        H[1] = true;
      }

      if(H[1]) {
        counter++;
        printPartition();

        H[1] = false;
        if(a[2] >= (a[1] - 1))
          H[3] = true;
        else
          H[2] = true;
      }

      if(H[2]) {
        a[1]--;
        a[2]++;
        H[2] = false;
        H[1] = true;
        continue;
      }

      if(H[3]) {
        j = 3;
        s = (short) (a[1] + a[2] - 1);
        while(a[j] >= a[1] - 1) {
          s = (short) (s + a[j]);
          j++;
        }
        H[3] = false;
        H[4] = true;
      }

      if(H[4]) {
        if(j > m) {
          return counter;
        } else {
          x = (short) (a[j] + 1);
          a[j] = x;
          j--;
          H[4] = false;
          H[5] = true;
        }
      }

      if(H[5]) {
        while(j > 1) {
          a[j] = x;
          s = (short) (s - x);
          j--;
        }
        a[1] = s;
        H[5] = false;
        H[1] = true;
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
