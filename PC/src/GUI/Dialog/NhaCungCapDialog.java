/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;
import GUI.Log_In;
import GUI.Panel.NhaCungCap;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import connect.ISocketClient;
import handler.ISupplierHandle;
import handler.SupplierHandle;
import helper.Validation;
import model.ListTransfer;
import model.Supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;


public class NhaCungCapDialog extends JDialog implements ActionListener {
    private NhaCungCap jpNcc;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenNcc;
    private InputForm diachi;
    private InputForm email;
    private InputForm sodienthoai;
    private Supplier nccDTO;
    private ISupplierHandle sup = new SupplierHandle();
    private ISocketClient conn = new Log_In();
    public NhaCungCapDialog(NhaCungCap jpNcc, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpNcc = jpNcc;
        initComponents(title, type);
    }

    public NhaCungCapDialog(NhaCungCap jpNcc, JFrame owner, String title, boolean modal, String type, Supplier nccdto) {
        super(owner, title, modal);
        this.jpNcc = jpNcc;
        this.nccDTO = nccdto;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(900, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        tenNcc = new InputForm("Tên nhà cung cấp");
        diachi = new InputForm("Địa chỉ");
        email = new InputForm("Email");
        sodienthoai = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument)sodienthoai.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));

        pnmain.add(tenNcc);
        pnmain.add(diachi);
        pnmain.add(email);
        pnmain.add(sodienthoai);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm đơn vị", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuyBo.addActionListener(this);

        switch (type) {
            case "create" ->
                    pnbottom.add(btnThem);
            case "update" -> {
                pnbottom.add(btnCapNhat);
                initInfo();
            }
            case "view" -> {
                initInfo();
                initView();
            }
            default ->
                    throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initInfo() {
        tenNcc.setText(nccDTO.getName());
        diachi.setText(nccDTO.getAddress());
        email.setText(nccDTO.getEmail());
        sodienthoai.setText(nccDTO.getSdt());
    }

    public void initView() {
        tenNcc.setEditable(false);
        diachi.setEditable(false);
        email.setEditable(false);
        sodienthoai.setEditable(false);

    }
    public boolean Validation(){
        if (Validation.isEmpty(tenNcc.getText())) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else  if (Validation.isEmpty(diachi.getText())) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (Validation.isEmpty(email.getText()) || !Validation.isEmail(email.getText())) {
            JOptionPane.showMessageDialog(this, "Email không được rỗng và phải đúng cú pháp", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (Validation.isEmpty(sodienthoai.getText()) || !Validation.isNumber(sodienthoai.getText()) && sodienthoai.getText().length()!=10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem && Validation()) {
            sup.addPhone(new ListTransfer("add", new Supplier( tenNcc.getText(), diachi.getText(), email.getText(), sodienthoai.getText(),1)));
            jpNcc.listncc= conn.findAll().getSup();
            jpNcc.loadDataTable(jpNcc.listncc);
            dispose();

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && Validation()) {
            sup.editPhone(new ListTransfer("edit", new Supplier( tenNcc.getText(), diachi.getText(), email.getText(), sodienthoai.getText(),1)));
            jpNcc.listncc= conn.findAll().getSup();
            jpNcc.loadDataTable(jpNcc.listncc);
            dispose();
        }
    }
}