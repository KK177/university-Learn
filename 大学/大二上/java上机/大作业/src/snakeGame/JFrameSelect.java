package snakeGame;

import javax.swing.*;
import java.awt.*;

public class JFrameSelect {
    private static JFrame selectFrame,snakeFrame;
    private static SnakeCanvas canvas;
    private static JLabel selectLabel;
    private static JPanel mJPanel,bgJPanel;
    private static JButton beginButton, endButton;
    private static JSpinner mJSpinner;
    private static String[] strings;
    private static int number = 3;

    public JFrameSelect() {
        init();
        selectFrame.setBounds(500, 200, 300, 450);
        selectFrame.setBackground(Color.BLUE);
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectFrame.setLayout(new BorderLayout(10, 10));
        mJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mJPanel.add(beginButton);
        mJPanel.add(selectLabel);
        SpinnerModel model = new SpinnerListModel(strings);
        mJSpinner = new JSpinner(model);
        ((JSpinner.DefaultEditor) mJSpinner.getEditor()).getTextField().setEditable(false);
        mJSpinner.setSize(50, 50);
        mJPanel.add(mJSpinner);
        mJPanel.add(endButton);
        selectFrame.add(mJPanel, BorderLayout.SOUTH);
        selectFrame.add(bgJPanel, BorderLayout.CENTER);
        selectFrame.setResizable(false);
        selectFrame.setVisible(true);
        Listen();
    }
    public void  init() {
        selectFrame = new JFrame("请选择游戏难度");
        snakeFrame = new JFrame("贪吃蛇小游戏");
        mJPanel = new JPanel();
        Image image=new ImageIcon("photo/bg.jpg").getImage();
        bgJPanel = new backGroundImage(image);
        beginButton = new JButton("开始");
        selectLabel = new JLabel("选择难度");
        endButton = new JButton("退出");
        strings = new String[]{"简单", "一般", "困难"};
    }
    public void Listen() {
        beginButton.addActionListener(actionEvent -> {
            selectFrame.setVisible(false);
            snakeFrame.getContentPane().setBackground(Color.CYAN);
            snakeFrame.setBounds(400, 200, 900, 720);
            snakeFrame.setResizable(false);
            snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            canvas = new SnakeCanvas(number);
            snakeFrame.add(canvas);
            snakeFrame.setVisible(true);

        });
        mJSpinner.addChangeListener(changeEvent -> {
            switch (mJSpinner.getValue().toString()) {
                case "简单":
                    number = 3;
                    break;
                case "一般":
                    number = 2;
                    break;
                case "困难":
                    number = 1;
                    break;
                default:
                    break;
            }
        });
        endButton.addActionListener(actionEvent -> selectFrame.dispose());
    }

}
