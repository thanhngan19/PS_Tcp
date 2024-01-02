package GUI.Dialog.ThuocTinhSanPham;


import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Log_In;
import GUI.Main;
import GUI.Panel.QuanLyThuocTinhSP;
import com.google.gson.Gson;
import connect.ISocketClient;
import handler.BrandHandle;
import handler.GrAuthorHandle;
import handler.IBrandHandle;
import handler.IGrAuthorHandle;
import helper.Validation;
import model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author 84907
 */
public final class ThuongHieuDialog extends JDialog implements MouseListener {

    HeaderTitle headTite;
    JPanel top, main, bottom;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    ButtonCustom add, del, update;
   private  IBrandHandle brand = new BrandHandle();
    private  List<Brand> list = brand.findAll();
    QuanLyThuocTinhSP qltt;
    private IGrAuthorHandle gr= new GrAuthorHandle();
    static ObjectOutputStream out;
    static ObjectInputStream in;
    public static Gson gson = new Gson();
    private ISocketClient conn = new Log_In();


    public ThuongHieuDialog(JFrame owner, QuanLyThuocTinhSP qlttsp, String title, boolean modal, int nhomquyen, Main m) {
        super(owner, title, modal);
        in= m.getIn();
        out=m.getOut();
        initComponent(qlttsp);
        loadQuyen(nhomquyen);
        loadDataTable(list);
    }

    public void loadQuyen(int nhomquyen) {
        if (!gr.checkPermisson(nhomquyen, "thuoctinh", "create")) {
            add.setVisible(false);
        }
        if (!gr.checkPermisson(nhomquyen, "thuoctinh", "delete")) {
            del.setVisible(false);
        }
        if (!gr.checkPermisson(nhomquyen, "thuoctinh", "update")) {
            update.setVisible(false);
        }
    }

    public void initComponent(QuanLyThuocTinhSP qltt) {
        this.qltt = qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTite = new HeaderTitle("THƯƠNG HIỆU SẢN PHẨM");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();

        top.setLayout(new GridLayout(1, 1));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 70));
        top.add(headTite);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        ms = new InputForm("Tên thương hiệu");
        ms.setPreferredSize(new Dimension(250, 70));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã thương hiệu", "Tên thương hiệu"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 250));
        main.add(ms);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel", 15, 100, 40);
        add.addMouseListener(this);
        del = new ButtonCustom("Xóa", "danger", 15, 100, 40);
        del.addMouseListener(this);
        update = new ButtonCustom("Sửa", "success", 15, 100, 40);
        update.addMouseListener(this);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1, 20, 20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(List<Brand> result) {

        int n=1;
        tblModel.setRowCount(0);
        for (Brand th : result) {
            tblModel.addRow(new Object[]{
                n++, th.getName()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (Validation.isEmpty(ms.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thương hiệu mới");
            } else {
                String tenthuonghieu = ms.getText();
                if (brand.checkUp(tenthuonghieu)) {
                        brand.addPhone(new ListTransfer(new Brand(1, tenthuonghieu, 1), "add"));
                        list= conn.findAll().getListBrand();
                        loadDataTable(list);
                        ms.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Thương hiệu đã tồn tại !");
                }
            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                brand.deletePhone(new ListTransfer(list.get(index),"delete"));

                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                if (Validation.isEmpty(ms.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thương hiệu mới");
                } else {
                    String tenthuonghieu = ms.getText();
                    if (brand.checkUp(tenthuonghieu)) {
                        brand.editPhone(new ListTransfer(new Ram(list.get(index).getId(), tenthuonghieu,1),"edit"));
                        loadDataTable(list);
                        ms.setText("");
                    }else {
                        JOptionPane.showMessageDialog(this, "Thương hiệu đã tồn tại !");
                    }
                }
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(list.get(index).getName());
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thương hiệu!");
        }
        return index;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public  List<Brand> getInf(){
        try {
            ListTranmission myObj = gson.fromJson((String) in.readObject(), ListTranmission.class);
            list= myObj.getListBrand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
