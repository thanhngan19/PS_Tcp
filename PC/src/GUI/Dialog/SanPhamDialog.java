package GUI.Dialog;

import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Log_In;
import GUI.Panel.SanPham;
import connect.ISocketClient;
import handler.*;
import helper.Formater;
import helper.Validation;
import model.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.BoxLayout;
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
import javax.swing.text.PlainDocument;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class SanPhamDialog extends JDialog implements ActionListener {
    Socket soc;
    static ObjectOutputStream out;
    static ObjectInputStream in;
    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain, pncard2;
    private ButtonCustom btnThemCHMS, btnHuyBo, btnAddCauHinh, btnEditCTCauHinh, btnDeleteCauHinh, btnResetCauHinh, btnAddSanPham, btnBack, btnViewCauHinh;
    InputForm tenSP, chipxuly, dungluongpin, kichthuocman, thoigianbaohanh, phienbanhdh, camerasau, cameratruoc;
    InputForm txtgianhap, txtgiaxuat;
    SelectForm cbxRom, cbxRam, cbxMausac, hedieuhanh,xuatxu;
    SelectForm thuonghieu, khuvuc;
    InputImage hinhanh;
    JTable tblcauhinh;
    JScrollPane scrolltblcauhinh;
    DefaultTableModel tblModelch;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    SanPham jpSP;
    private IRomHandle rom = new RomHandle();
    private IRamHandle ram = new RamHandle();
    private IColorHandle color= new ColorHandle();
    private IVersionPhoneHandle ver = new VersionPhoneHandle();
    private IOriginHandle orgin = new OriginHandle();
    private IBrandHandle brand = new BrandHandle();
    private OsHandle os = new OsHandle();
    private IPhoneHandle phone = new PhoneHandle();
    private IWareHouseHandle ware= new WareHouseHandle();
    private List<Phone> setPhone = phone.findAll();
    private ISocketClient check= new Log_In();


    List<VersionPhone> listChAll = ver.findAll();
    private List<VersionPhone> listch;
    Phone sp;
    String[] arrkhuvuc;
    String[] arrthuonghieu;
    String[] arrhHDH;
    String[] arrXX;
    int masp;
    int mach;
    private ButtonCustom btnEditCT;
    private ButtonCustom btnSaveCH;
    private ButtonCustom btnAddCauHinhEdit;
    private ButtonCustom btnEditCTCauHinhEdit;
    private ButtonCustom btnDeleteCauHinhEdit;
    private ButtonCustom btnResetCauHinhEdit;


    public void init(SanPham jpSP) {
        int n=0;
        this.jpSP = jpSP;
        arrkhuvuc = ware.transListToArr();
        arrthuonghieu = brand.transListToArr();
        arrhHDH = os.transListToArr();
        arrXX = orgin.transListToArr();
        
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, Phone sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        this.listch = ver.findByMaSP(sp.getId());
        initComponents(title, type);
    }


    public void initCardOne(String type) {
        pnCenter = new JPanel(new BorderLayout());
        pninfosanpham = new JPanel(new GridLayout(3, 4, 0, 0));
        pninfosanpham.setBackground(Color.WHITE);
        pnCenter.add(pninfosanpham, BorderLayout.CENTER);

        pninfosanphamright = new JPanel();
        pninfosanphamright.setBackground(Color.WHITE);
        pninfosanphamright.setPreferredSize(new Dimension(300, 600));
        pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pninfosanphamright, BorderLayout.WEST);

        tenSP = new InputForm("Tên sản phẩm");
        xuatxu = new SelectForm("Xuất xứ", arrXX);
        chipxuly = new InputForm("Chip xử lý");
        dungluongpin = new InputForm("Dung lượng pin");
        PlainDocument pin = (PlainDocument)dungluongpin.getTxtForm().getDocument();
        pin.setDocumentFilter((new NumericDocumentFilter()));
        kichthuocman = new InputForm("Kích thước màn");
        phienbanhdh = new InputForm("Phiên bản hđh");
        PlainDocument pbhdh = (PlainDocument)phienbanhdh.getTxtForm().getDocument();
        pbhdh.setDocumentFilter((new NumericDocumentFilter()));
        thoigianbaohanh = new InputForm("Thời gian bảo hành");
        PlainDocument baohanh = (PlainDocument)thoigianbaohanh.getTxtForm().getDocument();
        baohanh.setDocumentFilter((new NumericDocumentFilter()));
        camerasau = new InputForm("Camera sau");
        cameratruoc = new InputForm("Camera trước");
        hedieuhanh = new SelectForm("Hệ điều hành", arrhHDH);
        thuonghieu = new SelectForm("Thương hiệu", arrthuonghieu);
        khuvuc = new SelectForm("Khu vực kho", arrkhuvuc);
        hinhanh = new InputImage("Hình minh họa");

        pninfosanpham.add(tenSP);
        pninfosanpham.add(xuatxu);
        pninfosanpham.add(chipxuly);
        pninfosanpham.add(dungluongpin);
        pninfosanpham.add(kichthuocman);
        pninfosanpham.add(camerasau);
        pninfosanpham.add(cameratruoc);
        pninfosanpham.add(hedieuhanh);
        pninfosanpham.add(phienbanhdh);
        pninfosanpham.add(thoigianbaohanh);
        pninfosanpham.add(thuonghieu);
        pninfosanpham.add(khuvuc);
        pninfosanphamright.add(hinhanh);
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        switch (type) {
            case "view" -> {
                btnViewCauHinh = new ButtonCustom("Xem cấu hình", "warning", 14);
                btnViewCauHinh.addActionListener(this);
                pnbottom.add(btnViewCauHinh);
            }
            case "update" -> {
                btnSaveCH = new ButtonCustom("Lưu thông tin", "success", 14);
                btnEditCT = new ButtonCustom("Sửa cấu hình", "warning", 14);
                btnSaveCH.addActionListener(this);
                btnEditCT.addActionListener(this);
                pnbottom.add(btnSaveCH);
                pnbottom.add(btnEditCT);
            }
            case "create" -> {
                btnThemCHMS = new ButtonCustom("Tạo cấu hình", "success", 14);
                btnThemCHMS.addActionListener(this);
                pnbottom.add(btnThemCHMS);
            }
        }

        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    public void initCardTwo(String type) {
        pncard2 = new JPanel(new BorderLayout());
        JPanel cauhinhtop = new JPanel(new GridLayout(1, 5));
        cbxRom = new SelectForm("ROM", rom.transListToArr());
        cbxRam = new SelectForm("RAM", ram.transListToArr());
        cbxMausac = new SelectForm("Màu sắc", color.transListToArr());
        txtgianhap = new InputForm("Giá nhập");
        PlainDocument nhap = (PlainDocument)txtgianhap.getTxtForm().getDocument();
        nhap.setDocumentFilter((new NumericDocumentFilter()));
        txtgiaxuat = new InputForm("Giá xuất");
        PlainDocument xuat = (PlainDocument)txtgiaxuat.getTxtForm().getDocument();
        xuat.setDocumentFilter((new NumericDocumentFilter()));
        cauhinhtop.add(cbxRom);
        cauhinhtop.add(cbxRam);
        cauhinhtop.add(cbxMausac);
        cauhinhtop.add(txtgianhap);
        cauhinhtop.add(txtgiaxuat);

        JPanel cauhinhcenter = new JPanel(new BorderLayout());

        JPanel cauhinhcenter_left = new JPanel();
        BoxLayout bl = new BoxLayout(cauhinhcenter_left, BoxLayout.Y_AXIS);
        cauhinhcenter_left.setPreferredSize(new Dimension(100, 226));
        cauhinhcenter_left.setBorder(new EmptyBorder(10, 10, 10, 10));
        cauhinhcenter_left.setLayout(bl);
        cauhinhcenter_left.setBackground(Color.WHITE);
        tblcauhinh = new JTable();
        tblcauhinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = getRowCauHinh();
                if (index != -1) {
                    System.out.println(listChAll.size());
                    setInfoCauHinh(listChAll.get(index));
                }
            }
        });

        scrolltblcauhinh = new JScrollPane(tblcauhinh);
        tblModelch = new DefaultTableModel();
        String[] header = new String[]{"STT", "RAM", "ROM", "Màu sắc", "Giá nhập", "Giá xuất"};
        tblModelch.setColumnIdentifiers(header);
        tblcauhinh.setModel(tblModelch);
        scrolltblcauhinh.setViewportView(tblcauhinh);
        tblcauhinh.setDefaultRenderer(Object.class, centerRenderer);
        cauhinhcenter_left.add(scrolltblcauhinh);

        JPanel cauhinhcenter_right = new JPanel(new FlowLayout());
        cauhinhcenter_right.setPreferredSize(new Dimension(180, 10));
        cauhinhcenter_right.setBackground(Color.white);
        cauhinhcenter_right.setBorder(new EmptyBorder(0, 0, 0, 10));
        if(!type.equals("update")){
        btnAddCauHinh = new ButtonCustom("Thêm cấu hình", "success", 14);
        btnEditCTCauHinh = new ButtonCustom("Sửa cấu hình", "warning", 14);
        btnDeleteCauHinh = new ButtonCustom("Xoá cấu hình", "danger", 14);
        btnResetCauHinh = new ButtonCustom("Làm mới", "excel", 14);

        btnAddCauHinh.addActionListener(this);
        btnEditCTCauHinh.addActionListener(this);
        btnDeleteCauHinh.addActionListener(this);
        btnResetCauHinh.addActionListener(this);
        cauhinhcenter_right.add(btnAddCauHinh);
        cauhinhcenter_right.add(btnEditCTCauHinh);
        cauhinhcenter_right.add(btnDeleteCauHinh);
        cauhinhcenter_right.add(btnResetCauHinh);
        } else {
        btnAddCauHinhEdit = new ButtonCustom("Thêm cấu hình", "success", 14);
        btnEditCTCauHinhEdit = new ButtonCustom("Sửa cấu hình", "warning", 14);
        btnDeleteCauHinhEdit = new ButtonCustom("Xoá cấu hình", "danger", 14);
        btnResetCauHinhEdit = new ButtonCustom("Làm mới", "excel", 14);
        
        btnAddCauHinhEdit.addActionListener(this);
        btnEditCTCauHinhEdit.addActionListener(this);
        btnDeleteCauHinhEdit.addActionListener(this);
        btnResetCauHinhEdit.addActionListener(this);
        
        cauhinhcenter_right.add(btnAddCauHinhEdit);
        cauhinhcenter_right.add(btnEditCTCauHinhEdit);
        cauhinhcenter_right.add(btnDeleteCauHinhEdit);
        cauhinhcenter_right.add(btnResetCauHinhEdit);
        }
        cauhinhcenter.add(cauhinhcenter_left, BorderLayout.CENTER);
        cauhinhcenter.add(cauhinhcenter_right, BorderLayout.EAST);
        JPanel cauhinhbottom = new JPanel(new FlowLayout());
        cauhinhbottom.setBackground(Color.white);
        cauhinhbottom.setBorder(new EmptyBorder(0, 0, 10, 0));

        switch (type) {
            case "view" -> {
                loadDataToTableCauHinh(listch);
                btnAddCauHinh.setVisible(false);
                btnEditCTCauHinh.setVisible(false);
                btnDeleteCauHinh.setVisible(false);
                btnResetCauHinh.setVisible(false);
                cauhinhcenter.remove(cauhinhcenter_right);
            }
            case "update" -> loadDataToTableCauHinh(listch);
            case "create" -> {
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                loadDataToTableCauHinh(listChAll);
                cauhinhbottom.add(btnAddSanPham);
            }
        }
        btnBack = new ButtonCustom("Quay lại trang trước", "warning", 14);
        btnBack.addActionListener(this);
        cauhinhbottom.add(btnBack);
        pncard2.add(cauhinhtop, BorderLayout.NORTH);
        pncard2.add(cauhinhcenter, BorderLayout.CENTER);
        pncard2.add(cauhinhbottom, BorderLayout.SOUTH);
    }
    public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new CardLayout());
        initCardOne(type);
        initCardTwo(type);
        pnmain.add(pnCenter);
        pnmain.add(pncard2);
        switch (type) {
            case "view" -> setInfo(sp);
            case "update" -> setInfo(sp);
            default -> {
            }
        }
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnThemCHMS && validateCardOne()) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnBack) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.previous(pnmain);
        } else if (source == btnAddCauHinh) {
            if (validateCardTwo()) {
                listch.add(getCauHinh());
                loadDataToTableCauHinh(this.listch);
                resetFormCauHinh();
            }
        } else if (source == btnResetCauHinh) {
            resetFormCauHinh();
            loadDataToTableCauHinh(this.listch);
        } else if (source == btnDeleteCauHinh) {
            int index = getRowCauHinh();
            this.listch.remove(index);
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
        } else if (source == btnEditCTCauHinh) {
            eventEditCauHinh();
           loadDataToTableCauHinh(this.listch);
        } else if (source == btnAddSanPham) {
            eventAddSanPham();
        } else if (source == btnViewCauHinh) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnEditCT){
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if(source == btnSaveCH){
            Phone snNew = getInfo();
            snNew.setId(this.sp.getId());
            phone.editPhone(new ListTransfer(snNew,"edit"));
            setPhone.get(this.sp.getId()-1).setName(snNew.getName());
            jpSP.loadDataTalbe(setPhone);
            int input = JOptionPane.showConfirmDialog(this,
                "Bạn có muốn chỉnh sửa chi tiết sản phẩm?", "Chỉnh sửa chi tiết", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(input == 0) {
                CardLayout c = (CardLayout) pnmain.getLayout();
                c.next(pnmain);
            }
                    }
        if(source == btnEditCTCauHinhEdit){
            if (validateCardTwo()) {
            int index = getRowCauHinh();
            if(index < 0){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình");
            } else {
                VersionPhone verP= ver.findById(index);
                verP.setRam(ram.findById(cbxRam.getSelectedIndex()));
                verP.setRom(rom.findById(cbxRom.getSelectedIndex()));
                verP.setColor(color.findById(cbxMausac.getSelectedIndex()));
                verP.setInPrice(Double.parseDouble(txtgianhap.getText()));
                verP.setExPrice(Double.parseDouble(txtgiaxuat.getText()));
           ver.editVer(new ListTransfer(verP,"edit"));
            loadDataToTableCauHinh(ver.findAll());
            resetFormCauHinh();
            }
        }
        }
        if(source == btnDeleteCauHinhEdit){
            int index = getRowCauHinh();
            ver.deleteVer(new ListTransfer("delete",index));
            loadDataToTableCauHinh(ver.findAll());
            resetFormCauHinh();
        }
        if(source == btnAddCauHinhEdit){
            if(validateCardTwo() ){
                ver.addVer(new ListTransfer(getCauHinh(sp.getId()),"add"));
            loadDataToTableCauHinh(ver.findAll());
            resetFormCauHinh();
            }
        }
        if(source == btnResetCauHinhEdit){
            resetFormCauHinh();
            loadDataToTableCauHinh(ver.findAll());
        }
        if(source == btnHuyBo){
            dispose();
        }
    }
    public void eventAddSanPham() {
        Phone sp = getInfo();
        int index = getRowCauHinh()+1;
        System.out.println("số dươc chọn "+index);
        VersionPhone v= ver.findById(index);
        if(v != null){
            sp.setQuantity(10);
            sp.setStatus(0);
            phone.addPhone(new ListTransfer(sp,v,"add"));
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            setPhone.add(sp);
            listChAll.add(v);
            jpSP.loadDataTalbe(setPhone);
        }
            dispose();
    }

    public void eventEditCauHinh() {
        if (validateCardTwo()) {
            int index = getRowCauHinh();
            if(index < 0){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình");
            } else {
                VersionPhone verP= ver.findById(index);
                verP.setRam(ram.findById(cbxRam.getSelectedIndex()));
                verP.setRom(rom.findById(cbxRom.getSelectedIndex()));
                verP.setColor(color.findById(cbxMausac.getSelectedIndex()));
                verP.setInPrice(Double.parseDouble(txtgianhap.getText()));
                verP.setExPrice(Double.parseDouble(txtgiaxuat.getText()));
                ver.editVer(new ListTransfer(verP,"edit"));
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
            }

        }
    }

    public int getRowCauHinh() {
        int index = tblcauhinh.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình !");
        }
        return index;
    }

    public Phone getInfo() {
        String hinhanh = this.hinhanh.getUrl_img();
        String vtensp = tenSP.getText();
        Origin vxuatxu = orgin.findById(xuatxu.getSelectedIndex()+1);
        String vchipxuly = chipxuly.getText();
        int vdungluongpin = Integer.parseInt(dungluongpin.getText());
        double ktman = Double.parseDouble(kichthuocman.getText());
        Os hdh = os.findById(hedieuhanh.getSelectedIndex()+1);
        String camsau = camerasau.getText();
        String camtruoc = cameratruoc.getText();
        int tgbh = Integer.parseInt(thoigianbaohanh.getText());
        int pb = Integer.parseInt(phienbanhdh.getText());
        Brand vthuonghieu = brand.findById(thuonghieu.getSelectedIndex()+1);
        WareHouse khuvuckho =ware.findById(khuvuc.getSelectedIndex()+1);
        Phone result = new Phone(masp, vtensp, hinhanh, vxuatxu, vchipxuly, vdungluongpin, ktman, hdh, pb, camsau, camtruoc, tgbh, vthuonghieu, khuvuckho, 10,0);
        return result;
    }

    public void setInfo(Phone sp) {
        hinhanh.setUrl_img(sp.getImg());
        tenSP.setText(sp.getName());
        xuatxu.setSelectedItem(sp.getOrigin().getId());
        chipxuly.setText(sp.getChip());
        dungluongpin.setText(Integer.toString(sp.getPin()));
        kichthuocman.setText(Double.toString(sp.getInch()));
        hedieuhanh.setSelectedItem(sp.getOs().getId());
        camerasau.setText(sp.getRearCam());
        cameratruoc.setText(sp.getRearCam());
        thoigianbaohanh.setText(Integer.toString(sp.getTime()));
        phienbanhdh.setText(Integer.toString(sp.getiOs()));
        thuonghieu.setSelectedIndex(sp.getBrand().getId());
    }

    public VersionPhone getCauHinh() {
        Rom romList= rom.findAll().get(cbxRom.getSelectedIndex());
        Ram ramList= ram.findAll().get(cbxRam.getSelectedIndex());
        ColorBr colorList= color.findAll().get(cbxMausac.getSelectedIndex());
        double gianhap = Double.parseDouble(txtgianhap.getText());
        double giaban = Double.parseDouble(txtgiaxuat.getText());
        VersionPhone chsp = new VersionPhone(ramList,romList,colorList,gianhap,giaban,0);
        mach++;
        return chsp;
    }
    
    public VersionPhone getCauHinh(int masanpham) {
        Rom romList= rom.findAll().get(cbxRom.getSelectedIndex());
        Ram ramList= ram.findAll().get(cbxRam.getSelectedIndex());
        ColorBr colorList= color.findAll().get(cbxMausac.getSelectedIndex());
        double gianhap = Double.parseDouble(txtgianhap.getText());
        double giaban = Double.parseDouble(txtgiaxuat.getText());
        Phone phoneObj = phone.searchById(masanpham);
        VersionPhone chsp = new VersionPhone(1,phoneObj,ramList,romList,colorList,gianhap,giaban,0);
        return chsp;
    }

    public boolean validateCardOne() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) || Validation.isEmpty((String) xuatxu.getSelectedItem())
                || Validation.isEmpty(chipxuly.getText()) || Validation.isEmpty(dungluongpin.getText())
                || Validation.isEmpty(kichthuocman.getText()) || Validation.isEmpty(hedieuhanh.getValue())
                || Validation.isEmpty(camerasau.getText()) || Validation.isEmpty(cameratruoc.getText())
                || Validation.isEmpty(thoigianbaohanh.getText()) || Validation.isEmpty(phienbanhdh.getText())) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            // Check number 
        }
        return check;
    }

    public boolean validateCardTwo() {
        boolean check = true;
        if (Validation.isEmpty(txtgianhap.getText()) && Validation.isEmpty(txtgiaxuat.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            check = false;
        } else {

        }
        return check;
    }

    public void loadDataToTableCauHinh(List<VersionPhone> ch) {
        tblModelch.setRowCount(0);
        int i=1;
        for (VersionPhone listCh: ch) {
            String mau = listCh.getColor().getName();
            String ram = listCh.getRam().getName();
            String rom = listCh.getRom().getName();
            tblModelch.addRow(new Object[]{i++, ram + "GB", rom + "GB",
                mau, Formater.FormatVND(listCh.getInPrice()), Formater.FormatVND(listCh.getExPrice())
            });
        }
    }


    public void resetFormCauHinh() {
        cbxMausac.setSelectedIndex(0);
        cbxRam.setSelectedIndex(0);
        cbxRom.setSelectedIndex(0);
        txtgianhap.setText("");
        txtgiaxuat.setText("");
    }

    public void setInfoCauHinh(VersionPhone ch) {
        cbxMausac.setSelectedIndex(ch.getColor().getId()-1);
        cbxRam.setSelectedIndex(ch.getRam().getId()-1);
        cbxRom.setSelectedIndex(ch.getRom().getId()-1);
        txtgianhap.setText(Integer.toString((int) ch.getInPrice()));
        txtgiaxuat.setText(Integer.toString((int) ch.getExPrice()));
    }
}
