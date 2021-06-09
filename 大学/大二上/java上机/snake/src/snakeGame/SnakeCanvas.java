package snakeGame;

//swing包含图形界面的小组件
import javafx.scene.input.KeyCode;

import javax.swing.*;
//awt同于编写图形界面
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SnakeCanvas  extends JPanel implements KeyListener, ActionListener {
    //JPanel是一个面板容器类
    //KeyListener 这是实现一个监听键盘点击的接口


    //加载所有的图片
    ImageIcon body = new ImageIcon("photo/body.png");
    ImageIcon down = new ImageIcon("photo/down.png");
    ImageIcon food = new ImageIcon("photo/food.png");
    ImageIcon left = new ImageIcon("photo/left.png");
    ImageIcon right = new ImageIcon("photo/right.png");
    ImageIcon title = new ImageIcon("photo/title.jpg");
    ImageIcon up = new ImageIcon("photo/up.png");

    //设置蛇的数据结构（包括横坐标，纵坐标，大小，方向）
    int[] snakex = new int[1000];
    int[] snakey = new int[1000];
    int length = 3;
    String direction = "R";

    //食物的数据
    Random random = new Random();
    int foodx = random.nextInt(34) * 25 + 25;
    int foody = random.nextInt(24) * 25 + 75;

    //定时器
    Timer timer;

    //定时器的计时
    int millisecond = 0;

    //设置一个布尔变量来判断游戏是否开始
    boolean isStart = false;

    //判断游戏是否失败
    boolean isFaild = false;

    //分数
    int score = 0;
    int tempScore = 0;

    JComboBox cmb;

    //难度类型
    int num = 0; //20是简单 //15是一般 //10是困难

    static Player player = null;

    //音乐响应判断
    int judge = 0;

    //在构造方法里初始化蛇
    public SnakeCanvas(int number) {
        //获取画布的焦点 - 获取之后才可以操作画布
        this.setFocusable(true);
        //初始化静态蛇
        initSnake();
        //添加监听事件
        this.addKeyListener(this);
        //添加一个定时器
        if (number == 3) {
            timer = new Timer(150, this);
        } else if (number == 2) {
            timer = new Timer(125, this);
        } else if (number == 1) {
            timer = new Timer(100, this);
        }
        //开启定时器
        timer.start();
        num = number;
    }

    //初始化蛇的方法
    public void initSnake() {
        score = 0;
        millisecond = 0;
        isFaild = false;
        isStart = false;
        length = 3;
        direction = "R";
        //头部的位置
        snakex[0] = 100;
        snakey[0] = 100;
        //身体的位置
        snakex[1] = 75;
        snakey[1] = 100;
        //身体的位置
        snakex[2] = 50;
        snakey[2] = 100;
    }


    //paint是一个绘画的方法，Graphics相当于画笔
    @Override
    public void paint(Graphics g) {
        //设置背景颜色
        this.setBackground(Color.white);
        //用画笔去填充对应的界面
        g.setColor(Color.gray);
        g.fillRect(25, 75, 850, 600);

        //设置标题
        title.paintIcon(this, g, 25, 11);

        //画蛇头 - 根据direction去画出不同方向的蛇头
        if (direction.equals("R")) {
            right.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction.equals("L")) {
            left.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction.equals("U")) {
            up.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction.equals("D")) {
            down.paintIcon(this, g, snakex[0], snakey[0]);
        }
        //画蛇身
        for (int i = 1; i < length; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }
        //画提示语句
        if (!isStart) {
            //用画笔去绘画
            g.setColor(Color.white);
            //设置字体格式
            g.setFont(new Font("arial", Font.BOLD, 30));
            //设置字体在画布中展示的内容以及位置
            g.drawString("Please press space to start or pause", 200, 300);
        }

        //画食物
        food.paintIcon(this, g, foodx, foody);

        //游戏失败的提示语
        if (isFaild) {
            //用画笔去绘画
            g.setColor(Color.white);
            //设置字体格式
            g.setFont(new Font("arial", Font.BOLD, 30));
            //设置字体在画布中展示的内容以及位置
            g.drawString("Game Over! Please press space to play again!", 105, 300);

            //提示最高记录分数
            //设置字体格式
            g.setFont(new Font("arial", Font.PLAIN, 25));
            if (score > tempScore) {
                tempScore = score;
            }
            g.drawString("最高纪录：" + tempScore, 100, 45);

            try {
                player();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }

        g.setFont(new Font("arial", Font.PLAIN, 25));
        if (num == 3) {
            //画秒数
            if (millisecond / 1500 > 7) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawString("还剩下" + String.valueOf(10 - millisecond / 1500) + "秒", 600, 45);
        } else if (num == 2) {
            //画秒数
            if (millisecond / 1250 > 6) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawString("还剩下" + String.valueOf(9 - millisecond / 1250) + "秒", 600, 45);
        } else if (num == 1) {
            //画秒数
            if (millisecond / 1000 > 5) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawString("还剩下" + String.valueOf(8 - millisecond / 1000) + "秒", 600, 45);
        }

        //画分数和长度
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 15));
        g.drawString("Score:" + score, 750, 30);
        g.drawString("Length:" + length, 750, 50);


    }

    //实现监听键盘按键的方法
    @Override
    public void keyPressed(KeyEvent e) {
        judge = 0;
        //获取按键的类型
        int key = e.getKeyCode();
        //判断这个按键是不是空格
        if (key == KeyEvent.VK_SPACE) {
            if (isFaild) {
                initSnake();
                timer.restart();
            } else {
                isStart = !isStart;
            }
        } else if (key == KeyEvent.VK_UP && !direction.equals("D")) {
            direction = "U";
        } else if (key == KeyEvent.VK_DOWN && !direction.equals("U")) {
            direction = "D";
        } else if (key == KeyEvent.VK_LEFT && !direction.equals("R")) {
            direction = "L";
        } else if (key == KeyEvent.VK_RIGHT && !direction.equals("L")) {
            direction = "R";
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //实现动作监听器的方法
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //游戏开始的情况下
        if (isStart && !isFaild) {
            //移动身体
            for (int i = length; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            //移动头部
            if (direction.equals("R")) {
                snakex[0] = snakex[0] + 25;
                if (snakex[0] > 850) {
                    isFaild = true;
                }
            } else if (direction.equals("L")) {
                snakex[0] = snakex[0] - 25;
                if (snakex[0] < 25) {
                    isFaild = true;
                }
            } else if (direction.equals("U")) {
                snakey[0] = snakey[0] - 25;
                if (snakey[0] < 75) {
                    isFaild = true;
                }
            } else if (direction.equals("D")) {
                snakey[0] = snakey[0] + 25;
                if (snakey[0] > 650) {
                    isFaild = true;
                }
            }
            //吃食物的逻辑
            if (snakex[0] == foodx && snakey[0] == foody) {
                length++;
                score++;
                millisecond = 0;
                foodx = random.nextInt(34) * 25 + 25;
                foody = random.nextInt(24) * 25 + 75;
            }
            //判断游戏失败
            for (int i = 1; i < length; i++) {
                if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
                    isFaild = true;
                }
            }
            //食物设置为8秒内不吃掉就会消失
            millisecond = millisecond + timer.getDelay();
            System.out.println(millisecond);
            if (num == 1) {
                if (millisecond % 8000 == 0) {
                    foodSet();
                }
            } else if (num == 2) {
                if (millisecond % 11250 == 0) {
                    foodSet();
                }
            } else if (num == 3) {
                if (millisecond % 15000 == 0) {
                    foodSet();
                }
            }
        }
        repaint();
    }

    public void foodSet() {
        millisecond = 0;
        foodx = random.nextInt(34) * 25 + 25;
        foody = random.nextInt(24) * 25 + 75;
        for (int i = 0; i < length; i++) {
            if (snakex[i] == foodx && snakey[i] == foody) {
                foodx = random.nextInt(34) * 25 + 25;
                foody = random.nextInt(24) * 25 + 75;
            }
        }
    }

    public void player() throws FileNotFoundException, JavaLayerException {
        try {
            File file = new File("/Users/macbookpro/Desktop/841506.mp3");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream stream = new BufferedInputStream(fis);
            Player player = new Player(stream);
            new Thread(() -> {
                //调用播放方法进行播放
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();
            //音乐2s播放完
            Thread.sleep(2000);
            player.close();
            timer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}