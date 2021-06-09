package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.swing.JComponent;


public class Snake extends JComponent implements KeyListener {

    private static List<Head> headList = new ArrayList<>();
    private static int mSecretion;
    private static boolean isRuning = true;
    private static boolean iseating = true;
    private static boolean isChange = false;
    private static int a, b, space ;
    private static int weight, high;

    public Snake(final int number, int weight, int high) {
        Snake.weight = weight;
        Snake.high = high;
        space = number;
        this.setFocusable(true);  // 获取焦点
        this.addKeyListener(this); // 监听键盘事件
        headList.add(new Head());
        a = 4;
        b = 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRuning) {
                    try {
                        Thread.sleep(100);
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 1; i <= weight / space; i++) {
            graphics.drawLine(i * space, 0, i * space, high);
        }
        for (int i = 1; i <= high / space; i++) {
            graphics.drawLine(0, i * space, weight, i * space);
        }
        graphics.setColor(Color.BLUE);
        if (isChange) {
            isChange = false;
            a = new Random().nextInt(14) + 1;
            b = new Random().nextInt(21) + 1;
        }
        graphics.fillRect(a * space, b * space, space, space);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < headList.size(); i++) {
            if (i==0){
                graphics.setColor(Color.RED);
            }else {
                graphics.setColor(Color.BLACK);
            }
            graphics.fillRect(headList.get(i).x, headList.get(i).y, space, space);
        }
        snakeMove(headList, mSecretion);

        if (iseating) {
            headList.remove(headList.size() - 1);
        }
        iseating = true;

    }

    public void snakeMove(List<Head> heads, int mSnakedirection) {
        Head mOldHead = heads.get(0);
        Head mNewheads = new Head();
        switch (mSnakedirection) {
            //上
            case 1:
                mNewheads.x = mOldHead.x;
                mNewheads.y = mOldHead.y - space;
                break;
            //下
            case 2:
                mNewheads.x = mOldHead.x;
                mNewheads.y = mOldHead.y + space;
                break;
            //左
            case 3:
                mNewheads.x = mOldHead.x + space;
                mNewheads.y = mOldHead.y;
                break;
            //右
            case 4:
                mNewheads.x = mOldHead.x - space;
                mNewheads.y = mOldHead.y;
                break;
        }
        Crossing(mNewheads.x, mNewheads.y);
        EatMyself(heads);
        EatFood(mNewheads.x, mNewheads.y);
        Sanme(heads);
        heads.add(0, mNewheads);

    }

    /**
     * 越界
     *
     * @param x
     * @param y
     */
    private void Crossing(int x, int y) {
        if ((x < 0 || x > 280) || (y < 0 || y > 420)) {
            isRuning = false;
        }
    }

    /**
     * 碰到自己
     */
    private void EatMyself(List<Head> heads) {
        for (int i = 1; i < heads.size(); i++) {
            if (heads.get(0).x == heads.get(i).x && heads.get(0).y == heads.get(i).y) {
                isRuning = false;
            }
        }
    }

    /**
     * 吃到食物
     */
    private void EatFood(int x, int y) {
        if (x == a * space && y == b * space) {
            iseating = false;
            isChange = true;
        }
    }

    /**
     * 避免食物与蛇体重合
     */
    private void Sanme(List<Head> heads) {
        for (int i = 0; i < heads.size(); i++) {
            if (a == heads.get(i).x && b == heads.get(i).y) {
                a = new Random().nextInt(14) + 1;
                b = new Random().nextInt(21) + 1;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
//        System.out.print("键盘值:" + keyCode + "\n");
        //上
        if ((keyCode == 87 || keyCode == 38) && mSecretion != 2) {
            mSecretion = 1;
        }
        //下
        else if ((keyCode == 83 || keyCode == 40) && mSecretion != 1) {
            mSecretion = 2;
        }//左
        else if ((keyCode == 68 || keyCode == 39 )&& mSecretion != 4) {
            mSecretion = 3;

        }//右
        else if ((keyCode == 65 || keyCode == 37 )&& mSecretion != 3) {
            mSecretion = 4;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}