package connect.Component;



import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import connect.Main;
import connect.Panel.Blocked;
import connect.Panel.ThongKe;
import connect.Panel.TrangChu;
import connect.Panel.TrangThai;
import connect.ServerSocketConnect;
import model.*;
import service.AuthorDetailService;
import service.CustomerService;
import service.IAuthorDetailService;
import service.ICustomerService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



//import GUI.Component.itemTaskbar;
public class MenuTaskbar extends JPanel {
    TrangChu trangChu;
    static ThongKe thongKe;
    Blocked blocked;
    static TrangThai status;
    private List<AuthorDetail> listQuyen;
    GrAuthor nhomQuyenDTO;
    Customer nhanVienDTO;
    static Main main;
    User user;
    ICustomerService customer= new CustomerService();
    IAuthorDetailService handle= new AuthorDetailService();
        int maxConnections;
    int countClient;
    int notAccess;
     Socket socket;
     ServerSocketConnect conn;


    public itemTaskbar[] listitem;
    JLabel lblTenNhomQuyen, lblUsername;
    JScrollPane scrollPane;
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;
    Color FontColor = new Color(250, 128, 114);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(238, 118, 33);
    Color HowerBackgroundColor = new Color(255 ,127, 36);
    String[][] getSt = {
//            {"Trang chủ", "home.svg", "trangchu"},
            {"Thống kê", "product.svg", "sanpham"},
            {"Quản lý tài khoản", "brand.svg", "thuoctinh"},
            {"Trạng thái", "staff.svg", "nhanvien"},
            {"Đăng xuất", "log_out.svg", "dangxuat"},
    };

    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    public MenuTaskbar(Main main) {
        this.main = main;
        initComponent();
    }
    public MenuTaskbar(Main main, User tk) {
        this.main = main;
        this.user = tk;
        this.nhomQuyenDTO =tk.getManhomquyen();
        Customer customers = null;
        for(Customer item : customer.findAll()){
            if(item.getId()==tk.getId()){
                customers= item;
            }
        }
        this.nhanVienDTO = customers;
        List<AuthorDetail> list= new ArrayList<AuthorDetail>();
        for(AuthorDetail item : handle.findAll()){
            if(item.getId()==tk.getId()){
                list.add(item);
            }
        }
        listQuyen = list;
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[getSt.length];
        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BorderLayout(0, 0));
        pnlTop.add(info, BorderLayout.CENTER);

        in4(info);

        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 600));
        pnlCenter.setBackground(DefaultColor);
        pnlCenter.setLayout(new FlowLayout(0, 0, 5));

        bar3 = new JPanel();
        bar3.setBackground(new Color(204, 214, 219));
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 10));
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);


        for (int i = 0; i < getSt.length; i++) {
            if (i + 1 == getSt.length) {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlBottom.add(listitem[i]);
            } else {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlCenter.add(listitem[i]);
                if (i != 0) {
                    if (!checkRole(getSt[i][2])) {
                        listitem[i].setVisible(true);
                    }

                }
            }
        }

        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setForeground(HowerFontColor);
        listitem[0].isSelected = true;

        for (int i = 0; i < getSt.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }

//        listitem[0].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent evt) {
//                trangChu = new TrangChu();
//                main.setPanel(trangChu);
//            }
//        });

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (thongKe != null) {
                    thongKe.updateData(main.getMaxConnections(), main.getCountClient(), main.getNotAccess());
                    main.setPanel(thongKe);
                }
                else {
                    thongKe = new ThongKe(main);
                    main.setPanel(thongKe);
                }

            }
        });
        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                blocked = new Blocked(main);
                main.setPanel(blocked);
            }
        });
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                status = new TrangThai(main);
                main.setPanel(status);
            }
        });


        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {

                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    ServerSocketConnect login = new ServerSocketConnect();
                    main.dispose();
                    login.setVisible(true);
                }
            }
        });
    }
    public boolean checkRole(String s) {
        boolean check = false;
        for (int i = 0; i < listQuyen.size(); i++) {
            if (listQuyen.get(i).getAction().equals("view")) {
                if (s.equals(listQuyen.get(i).getName())) {
                    check = true;
                    return check;
                }
            }
        }
        return check;
    }
    public void pnlMenuTaskbarMousePress(MouseEvent evt) {

        for (int i = 0; i < getSt.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(DefaultColor);
                listitem[i].setForeground(FontColor);
            }
        }
    }
    public static void changeTable(){
        if (thongKe != null) {
            thongKe.updateData(main.getMaxConnections(), main.getCountClient(), main.getNotAccess());
            main.setPanel(thongKe);
        }
    }
    public static void changeTableStatus(List<UserStatus> listSta){
        if(status !=null){
            TrangThai.list = listSta;
            status.loadDataTalbe(listSta);
            main.setPanel(status);
        }
    }
    public static void Blocked(String userName){
        try {
            ObjectOutputStream out = new ObjectOutputStream(main.getSocket().getOutputStream());
            out.writeObject(userName);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void in4(JPanel info) {
        JPanel pnlIcon = new JPanel(new FlowLayout());
        pnlIcon.setPreferredSize(new Dimension(60, 0));
        pnlIcon.setOpaque(false);
        info.add(pnlIcon, BorderLayout.WEST);
        JLabel lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(50, 70));
        if (nhanVienDTO.getGender() == 1) {
            lblIcon.setIcon(new FlatSVGIcon("./icon/man_50px.svg"));
        } else {
            lblIcon.setIcon(new FlatSVGIcon("./icon/women_50px.svg"));
        }
        pnlIcon.add(lblIcon);

        JPanel pnlInfo = new JPanel();
        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new FlowLayout(0, 10, 5));
        pnlInfo.setBorder(new EmptyBorder(15, 0, 0, 0));
        info.add(pnlInfo, BorderLayout.CENTER);

        lblUsername = new JLabel("Truy cập vào server");
        lblUsername.putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
        pnlInfo.add(lblUsername);

        lblTenNhomQuyen = new JLabel("quản lý client");
        lblTenNhomQuyen.putClientProperty("FlatLaf.style", "font: 120% $light.font");
        lblTenNhomQuyen.setForeground(Color.GRAY);
        pnlInfo.add(lblTenNhomQuyen);

        lblIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
//                MyAccount ma = new MyAccount(owner, MenuTaskbar.this, "Chỉnh sửa thông tin tài khoản", true);
            }
        });
    }
}
