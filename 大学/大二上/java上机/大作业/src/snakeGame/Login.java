package snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public  Login() {
        //设置主窗体
        JFrame jframe = new JFrame("登录界面");
        jframe.setBounds(300, 400, 280, 180);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);

        // 登录页面画板
        JPanel jpanel = new JPanel();
        Box box = Box.createVerticalBox();
        box.setBackground(Color.black);

        // 用户名ui
        Box userBox = Box.createHorizontalBox();
        userBox.setBackground(Color.BLUE);
        JLabel uLabel = new JLabel("Username ");
        JTextField userField = new JTextField(12);
        userBox.add(uLabel);
        userBox.add(Box.createHorizontalStrut(6));
        userBox.add(userField);
        box.add(Box.createVerticalStrut(16));
        box.add(userBox);

        // 密码ui
        Box pwBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("Password ");
        JTextField pwField = new JTextField(12);
        pwBox.add(pLabel);
        pwBox.add(Box.createHorizontalStrut(6));
        pwBox.add(pwField);
        box.add(Box.createVerticalStrut(8));
        box.add(pwBox);

        // 登录按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        btnBox.add(loginBtn);
        box.add(Box.createVerticalStrut(8));
        box.add(btnBox);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = pwField.getText().trim();
                Snakeuser user = new Snakeuser();
                user.setUsername(username);
                user.setPassword(password);
                Snakeuser existUser = BuildUser.login(user);
                if (existUser == null) {
                    Snakeuser getuser = BuildUser.getInfo();
                    if (username.equals(getuser.getUsername()) && !password.equals(getuser.getPassword())) {
                        JOptionPane.showMessageDialog(jframe, "   password输入错误！", "登录失败", JOptionPane.INFORMATION_MESSAGE);
                    }else if (!username.equals(getuser.getUsername()) && password.equals(getuser.getPassword())) {
                        JOptionPane.showMessageDialog(jframe, "   username输入错误！", "登录失败", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(jframe, "   请输入有效信息！", "登录失败", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    jframe.dispose();
                    new JFrameSelect();
                }
            }
        });
        jpanel.add(box);
        jframe.add(jpanel);
        jframe.setVisible(true);
    }
    }
