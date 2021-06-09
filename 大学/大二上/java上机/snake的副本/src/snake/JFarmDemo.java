package snake;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Cursor;
import java.awt.FlowLayout;
//import java.awt.Image;
//import java.awt.Point;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
//import javax.swing.JTabbedPane;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JFarmDemo {
    /**
     * mJFrame第一次显示的窗体，游戏开始前选择游戏难度的窗体
     * jFrame 点击开始按钮后显示的窗体，游戏正在运行的窗体
     *
     */
    private static JFrame mJFrame, jFrame;
    private static JLabel mJLabel, mJLabel1;
    private static JPanel mJPanel, mJPanel2;
    private static JButton mJButton, mJButton2;
    private static JSpinner mJSpinner;
    private static String[] strings;
    private static int number = 20;

    public JFarmDemo() {
        init_J();
        mJFrame.setBounds(500, 200, 300, 450);
        mJFrame.setBackground(Color.BLUE);
        mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mJFrame.setLayout(new BorderLayout(10, 10));
        mJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mJPanel.add(mJButton);
        mJPanel.add(mJLabel1);
        SpinnerModel model = new SpinnerListModel(strings);
        mJSpinner = new JSpinner(model);
        mJSpinner.setSize(50, 50);
        mJPanel.add(mJSpinner);
        mJPanel.add(mJButton2);
        ImageIcon mIcon = new ImageIcon("snake/s.jpg");
        mJLabel = new JLabel(mIcon);
        mJPanel2.add(mJLabel);
        mJFrame.add(mJPanel, BorderLayout.SOUTH);
        mJFrame.add(mJPanel2, BorderLayout.CENTER);
        mJFrame.setResizable(false);
        mJFrame.setVisible(true);
        Listen();
    }

    private void init_J() {
        mJFrame = new JFrame("贪吃蛇");
        jFrame = new JFrame("贪吃蛇");
        mJPanel = new JPanel();
        mJPanel2 = new JPanel();
        mJButton = new JButton("开始");
        mJLabel1 = new JLabel("选择难度");
        mJButton2 = new JButton("退出");
        strings = new String[]{"简单", "一般", "困难"};
    }

    private void Listen() {
        mJButton.addActionListener(actionEvent -> {
            mJFrame.setVisible(false);
            jFrame.getContentPane().setBackground(Color.CYAN);
            Snake snake = new Snake(number, 300, 440);
            snake.setBackground(Color.BLUE);
            jFrame.add(snake);
            jFrame.setBounds(500, 200, 320, 480);
            jFrame.setResizable(false);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);

        });
        mJSpinner.addChangeListener(changeEvent -> {
            switch (mJSpinner.getValue().toString()) {
                case "简单":
                    number = 20;
                    break;
                case "一般":
                    number = 15;
                    break;
                case "困难":
                    number = 10;
                    break;
                default:
                    break;
            }
        });
        mJButton2.addActionListener(actionEvent -> mJFrame.dispose());
    }
}