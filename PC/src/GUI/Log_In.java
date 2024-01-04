package GUI;


import GUI.Component.InputForm;
import GUI.Panel.SanPham;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.google.gson.Gson;
import connect.ISocketClient;
import helper.BCrypt;
import model.*;
import org.apache.poi.ss.formula.functions.T;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

public class Log_In extends JFrame implements KeyListener, Runnable,ISocketClient {
    public static ListTranmission list = new ListTranmission();
    static List<User> userCheck= new ArrayList<>();
    static List<T> searchList= new ArrayList<>();
    public Gson gson = new Gson();
    public static boolean isConn= false;
    public static boolean hasReceived= false;
    static Socket soc;
    static ListTranmission updatedList;
    public static boolean check=false;
    static  ObjectOutputStream out;
    static ObjectInputStream in;
    public  String json;
    private final Object lock = new Object();
    private final Object lockRead = new Object();
    JPanel pnlMain, pnlLogIn;
    JLabel lblImage,lbl3, lbl6, lbl7;
    InputForm txtUsername, txtPassword;
    private Main m;
    Color FontColor = new Color(96, 125, 139);
    public Log_In() {
        initComponent();
        txtUsername.setText("ngan");
        txtPassword.setPass("123456");
        try {
            if(!isConn){
                this.soc = new Socket("localhost", 1234);
                Thread t = new Thread((Runnable) this);
                this.out = new ObjectOutputStream(soc.getOutputStream());
                out.writeObject("hello from client");
                out.flush();
                this.in = new ObjectInputStream(soc.getInputStream());
                t.start();
                isConn= true;
            }
        } catch (Exception var2) {
            JOptionPane.showMessageDialog(this, "The connection limit to the server has been reached.", "Connection Error", JOptionPane.ERROR_MESSAGE);
          System.exit(0);
            isConn = false;
        }
    }
    @Override
    public void listDelete(ListTransfer deList) {
        if(isConn){
            json = gson.toJson(deList);
            try {

                out.writeObject(json);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                synchronized (lockRead) {
                    System.out.println("hgeg");
                    String updatedListJson = (String) in.readObject();
                    updatedList = gson.fromJson(updatedListJson, ListTranmission.class);
                    list = updatedList;
                    isConn = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Phone> listPhone() {
        return null;
    }

    @Override
    public User findUser(String userName) {
        User userFind= null;
        for(User user : list.getUser()){
            if(user.getUserName().equals(userName)){
                userFind=user;

            }
        }
        return userFind;
    }
    @Override
    public <T> List<T> listSearch(ListTransfer search) {
        return null;
    }

    @Override
    public void listEdit(ListTransfer editList) {
        if(isConn){
                json = gson.toJson(editList);
                try {
                    out.writeObject(json);
                    out.flush();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }
    @Override
    public void listAdd(ListTransfer addList) {
        if(isConn){
            json = gson.toJson(addList);
            try {
                out.writeObject(json);
                out.flush();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public List<Phone> listSearch(String url) {
        ListSearch list = new ListSearch(url);
        List<Phone> result= new ArrayList<>();
        if (isConn) {
            json = gson.toJson(list);
            try {
                out.writeObject(json);
                out.flush();
                String updatedListJson = (String) in.readObject();
                ListSearch updatedList = gson.fromJson(updatedListJson, ListSearch.class);
                result= updatedList.getListPhone();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    @Override
    public ListTranmission findAll() {
        if (isConn) {
            return list;


        }
        else {
            try {
                Gson gson = new Gson();
                list = gson.fromJson((String) in.readObject(), ListTranmission.class);
                userCheck = list.getUser();
                isConn = true;
                return list;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private ListTranmission readListFromInputStream(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        String updatedListJson = (String) inputStream.readObject();
        return gson.fromJson(updatedListJson, ListTranmission.class);
    }


    private void initComponent() {
        this.setSize(new Dimension(1000, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Đăng nhập");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame jf = this;
        imgIntro();
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.white);
        pnlMain.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlMain.setPreferredSize(new Dimension(500, 740));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));

        lbl3 = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lbl3.setFont(new Font(FlatRobotoFont.FAMILY_SEMIBOLD, Font.BOLD, 20));
        pnlMain.add(lbl3);

        JPanel paneldn = new JPanel();
        paneldn.setBackground(Color.BLACK);
        paneldn.setPreferredSize(new Dimension(400, 200));
        paneldn.setLayout(new GridLayout(2, 1));

        txtUsername = new InputForm("Tên đăng nhập");
        paneldn.add(txtUsername);
        txtPassword = new InputForm("Mật khẩu", "password");
        paneldn.add(txtPassword);

        txtUsername.getTxtForm().addKeyListener(this);
        txtPassword.getTxtPass().addKeyListener(this);

        pnlMain.add(paneldn);

        lbl6 = new JLabel("ĐĂNG NHẬP");
        lbl6.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lbl6.setForeground(Color.white);

        pnlLogIn = new JPanel();
        pnlLogIn.putClientProperty( FlatClientProperties.STYLE, "arc: 99" );
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setPreferredSize(new Dimension(380, 45));
        pnlLogIn.setLayout(new FlowLayout(1, 0, 15));

        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogInMouseEntered(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    pnlLogInMousePressed(evt);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogInMouseExited(evt);
            }
        });
        pnlLogIn.add(lbl6);

        lbl7 = new JLabel("Quên mật khẩu", JLabel.RIGHT);
        lbl7.setPreferredSize(new Dimension(380, 50));
        lbl7.setFont(new Font(FlatRobotoFont.FAMILY, Font.ITALIC, 18));
        lbl7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

            }
        });
        pnlMain.add(lbl7);

        pnlMain.add(pnlLogIn);

        this.add(pnlMain, BorderLayout.EAST);

    }

    public void checkLogin() throws UnsupportedLookAndFeelException {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getPass();
        try {
            out.writeObject(usernameCheck);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        } else {
            User tk= findUser(usernameCheck);
            if (tk == null) {
                JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (tk.getStatus() == 0) {
                    JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (BCrypt.checkpw(passwordCheck, tk.getPassWord())) {
                        try {
                            this.dispose();
                             m = new Main(tk);
                            m.setIn(in);
                            m.setOut(out);
                            m.setVisible(true);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
    }

    private void pnlLogInMousePressed(java.awt.event.MouseEvent evt) throws UnsupportedLookAndFeelException {
        checkLogin();
    }

    private void pnlLogInMouseEntered(java.awt.event.MouseEvent evt) {
        pnlLogIn.setBackground(FontColor);
        pnlLogIn.setForeground(Color.black);
    }

    private void pnlLogInMouseExited(java.awt.event.MouseEvent evt) {

        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setForeground(Color.white);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        Log_In login = new Log_In();
        login.setVisible(true);

    }

    public void imgIntro() {
        JPanel bo = new JPanel();
        bo.setBorder(new EmptyBorder(3, 10, 5, 5));
        bo.setPreferredSize(new Dimension(500, 740));
        bo.setBackground(Color.white);
        this.add(bo, BorderLayout.WEST);

        lblImage = new JLabel();
//        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/phone2.jpg")));
        lblImage.setIcon(new FlatSVGIcon("./img/login-image.svg"));
        bo.add(lblImage);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                checkLogin();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }




}
