package com.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.HashSet;

/**
 * @author 牟明
 */
public class SendFrame extends JFrame {

    private static JButton btn2;
    private static Font font;
    private static JLabel recipient;
    private static JLabel subject;
    private static JLabel body;
    private static JButton btn1;
    private static JTextField textField;
    private static JTextField textField1;
    private static JTextArea textArea;


    public static JLabel getRecipient() {
        return recipient;
    }

    public static void setRecipient(JLabel recipient) {
        SendFrame.recipient = recipient;
    }

    public static JLabel getSubject() {
        return subject;
    }

    public static void setSubject(JLabel subject) {
        SendFrame.subject = subject;
    }

    public static JLabel getBody() {
        return body;
    }

    public static void setBody(JLabel body) {
        SendFrame.body = body;
    }


    private static void init() {
        final JFrame frame = new SendFrame();
        frame.setLayout(null);
        frame.setTitle("新邮件");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 添加按钮
        btn1 = new JButton("添加附件");
        btn2 = new JButton("确认发送");
        btn1.setBounds(140, 450, 100, 25);
        btn2.setBounds(300, 450, 100, 25);
        btn1.setVisible(true);
        btn2.setVisible(true);
        frame.add(btn1);
        frame.add(btn2); // 设置字体
        font = new Font("宋体", Font.BOLD, 11);
        btn1.setFont(font);
        btn2.setFont(font); // 添加显示框
        recipient = new JLabel("收件人:");
        subject = new JLabel("主题:");
        body = new JLabel("正文:");
        recipient.setBounds(60, 40, 50, 25);
        recipient.setVisible(true);
        recipient.setFont(font);
        subject.setBounds(60, 70, 50, 25);
        subject.setVisible(true);
        subject.setFont(font);
        body.setBounds(60, 100, 50, 25);
        body.setVisible(true);
        body.setFont(font);
        frame.add(recipient); // 添加文本框
        frame.add(subject);
        frame.add(body);
        textField = new JTextField();
        textField.setBounds(120, 40, 400, 22);
        textField.setVisible(true);
        frame.add(textField);
        frame.setVisible(true);

        textField1 = new JTextField();
        textField1.setBounds(120, 70, 400, 22);
        textField1.setVisible(true);
        frame.add(textField1);
        frame.setVisible(true);

        textArea = new JTextArea();
        textArea.setBounds(120, 100, 400, 300);
        textArea.setVisible(true);
        frame.add(textArea);
        frame.setVisible(true);


        addactionlisten();

    }

    /**
     * @param developer 面板上的触发按钮
     */
    private static void eventOnImport(JButton developer) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        /** 过滤文件类型 * */
        FileNameExtensionFilter filter = new FileNameExtensionFilter("docx",
                "xml", "txt", "doc", "java", "war", "cpp");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(developer);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            /** 得到选择的文件* */
            File[] arrfiles = chooser.getSelectedFiles();
            if (arrfiles == null || arrfiles.length == 0) {
                return;
            }
            FileInputStream input;
            FileOutputStream out;
            String path = "./";//存放的当前位置
            try {
                for (File f : arrfiles) {
                    File dir = new File(path);
                    /** 目标文件夹 * */
                    File[] fs = dir.listFiles();
                    HashSet<String> set = new HashSet<>();
                    for (File file : fs) {
                        set.add(file.getName());
                    }
                    /** 判断是否已有该文件* */
                    if (set.contains(f.getName())) {
                        JOptionPane.showMessageDialog(new JDialog(),
                                f.getName() + ":该文件已存在！");
                        return;
                    }
                    input = new FileInputStream(f);
                    byte[] buffer = new byte[1024];
                    File des = new File(path, f.getName());
                    out = new FileOutputStream(des);
                    int len = 0;
                    while (-1 != (len = input.read(buffer))) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    input.close();
                }
                JOptionPane.showMessageDialog(null, "上传成功！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * 监听器的实现
     */
    private static void addactionlisten() {
        btn1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventOnImport(new JButton());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        btn2.addActionListener(e -> JOptionPane.showMessageDialog(null, "发送成功"));
    }

    private static void getdata() {

    }

    public static void main(final String[] args) {

        init();

    }

}
