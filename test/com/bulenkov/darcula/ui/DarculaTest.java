package com.bulenkov.darcula.ui;

import javax.swing.*;

public class DarculaTest {
  private JPanel myRoot;
  private JComboBox myComboBox1;
  private JComboBox myComboBox2;
  private JComboBox myComboBox3;
  private JComboBox myComboBox4;
  private JComboBox myComboBox5;
  private JTextField myTextField1;
  private JTextField myThisTextIsDisabledTextField;
  private JPasswordField myPasswordField1;
  private JTextField myTextField2;
  private JTextField myTextField3;
  private JTextField myTextField4;
  private JSpinner mySpinner1;
  private JProgressBar myProgressBar1;
  private JButton myProgressButton;
  private JProgressBar myProgressBar2;
  private JButton myStartButton;
  private JButton myHelpButton;
  private JButton myCancelButton;
  private JButton myDisabledButton;
  private JButton myDefaultButton;

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new DarculaTest().myRoot);
    frame.pack();
    frame.setVisible(true);
  }
}
