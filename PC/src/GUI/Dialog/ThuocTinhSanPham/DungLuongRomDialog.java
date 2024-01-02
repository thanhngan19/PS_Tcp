package GUI.Dialog.ThuocTinhSanPham;


import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Panel.QuanLyThuocTinhSP;
import handler.GrAuthorHandle;
import handler.IGrAuthorHandle;
import handler.IRomHandle;
import handler.RomHandle;
import helper.Validation;
import model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author 84907
 */
public class DungLuongRomDialog extends JDialog implements MouseListener {

    HeaderTitle headTite;
    JPanel top, main, bottom, all;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ButtonCustom add, del, update;
    private IRomHandle rom = new RomHandle();

    List<Rom> list = rom.findAll();
    QuanLyThuocTinhSP qltt;
    private IGrAuthorHandle gr= new GrAuthorHandle();

    public DungLuongRomDialog(JFrame owner, QuanLyThuocTinhSP qltt, String title, boolean modal, int nhomquyen) {
        super(owner, title, modal);
        initComponent(qltt);
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
        headTite = new HeaderTitle("DUNG LƯỢNG ROM");
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
        ms = new InputForm("Dung lượng ROM");
        PlainDocument m = (PlainDocument) ms.getTxtForm().getDocument();
        m.setDocumentFilter(new NumericDocumentFilter());
        ms.setPreferredSize(new Dimension(250, 70));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane(table);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã Rom", "Dung lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 250));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        main.add(ms);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel", 15, 100, 40);
        add.addMouseListener(this);
        del = new ButtonCustom("Xóa", "danger", 15, 100, 40);
        del.addMouseListener(this);
        update = new ButtonCustom("Sửa", "success", 15, 100, 40);
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

    public void loadDataTable(List<Rom> result) {
        tblModel.setRowCount(0);
        for (Rom dlr : result) {
            tblModel.addRow(new Object[]{
                dlr.getId(), dlr.getName()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (Validation.isEmpty(ms.getText())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập dung lượng Rom mới");
            } else {
                int kt = Integer.parseInt(ms.getText());
                if (rom.checkUp(kt)) {
                    String kichthuoc = String.valueOf(kt);
                    rom.addPhone(new ListTransfer(new Ram(1, kichthuoc, 1),"add"));
                    loadDataTable(list);
                    ms.setText("");
                }else {
                        JOptionPane.showMessageDialog(this, "Dung lượng ROM đã tồn tại !");
                    }
                }
            } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                rom.deletePhone(new ListTransfer(list.get(index),"delete"));
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                if (e.getSource() == add) {
                    if (Validation.isEmpty(ms.getText())) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập dung lượng Rom");
                    } else {
                        int kt = Integer.parseInt(ms.getText());
                        if (rom.checkUp(kt)) {
                            String kichthuoc = String.valueOf(kt);
                            rom.editPhone(new ListTransfer(new Ram(list.get(index).getId(), kichthuoc,1),"edit"));
                            loadDataTable(list);
                            ms.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this, "Dung lượng ROM đã tồn tại !");
                        }
                    }
                }
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(String.valueOf(list.get(index).getName()));
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dung lượng rom!");
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
}
