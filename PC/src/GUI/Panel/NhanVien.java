package GUI.Panel;


import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Dialog.KhuVucKhoDialog;
import GUI.Dialog.NhanVienTrans;
import GUI.Log_In;
import GUI.Main;
import connect.ISocketClient;
import model.Customer;
import model.ListTransfer;
import model.Phone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class NhanVien extends JPanel implements ActionListener {

    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    NhanVienTrans nvBus= new NhanVienTrans(this);
    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableNhanVien;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    public IntegratedSearch search;
    Main m;
    private ISocketClient conn= new Log_In();
    List<Customer> listnv = conn.findAll().getCustomerList();
    Color BackgroundColor = new Color(240, 247, 250);
    private DefaultTableModel tblModel;

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentCenter.add(functionBar, BorderLayout.NORTH);

        String[] action = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(m.user.getManhomquyen().getId(), "nhanvien", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(nvBus);
        }
        functionBar.add(mainFunction);
        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Họ tên", "Email"});
        functionBar.add(search);
        search.btnReset.addActionListener(this);


        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableNhanVien = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableNhanVien = new JTable();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MNV", "Họ tên", "Giới tính", "Ngày Sinh", "SDT", "Email"};

        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setModel(tblModel);
        tableNhanVien.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableNhanVien.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        scrollTableSanPham.setViewportView(tableNhanVien);
        main.add(scrollTableSanPham);
    }



    public NhanVien(Main m) {
        this.m = m;
        initComponent();
        tableNhanVien.setDefaultEditor(Object.class, null);
        loadDataTalbe(listnv);
    }

    public int getRow() {
        return tableNhanVien.getSelectedRow();
    }

    public Customer getNhanVien() {
        return listnv.get(tableNhanVien.getSelectedRow());
    }

    public void loadDataTalbe(List<Customer> list) {
        listnv = list;
        tblModel.setRowCount(0);
        int n=1;
        for (Customer nhanVien : listnv) {
            tblModel.addRow(new Object[]{n++
                    , nhanVien.getName(), nhanVien.getGender() == 1 ? "Nam" : "Nữ", nhanVien.getDate(), nhanVien.getSdt(), nhanVien.getEmail()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}