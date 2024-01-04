package connect.Panel;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import connect.Component.IntegratedSearch;
import connect.Component.MainFunction;
import connect.Component.PanelBorderRadius;
import connect.Component.TableSorter;
import connect.Main;
import connect.ServerSocketConnect;
import model.Phone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public final class ThongKe extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    public DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");

    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    JTable tableSanPham;
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    LocalDateTime currentTime = LocalDateTime.now();
    int maxConnections;
    int countClient;
    int notAccess;
    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT","Số lượng max client  ", "Số client đang truy cập ", " số client không thể truy cập" ,"Date","Time"};
        tblModel.setColumnIdentifiers(header);
        TableModelListener listener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
            }
        };
        tblModel.addTableModelListener(listener);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableSanPham.getColumnModel();
        for (int i = 0; i < 3; i++) {
            if (i != 1) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(180);
        tableSanPham.setFocusable(false);
        tableSanPham.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableSanPham, 2, TableSorter.INTEGER_COMPARATOR);
        tableSanPham.setDefaultEditor(Object.class, null);
        initPadding();
        JLabel label = new JLabel("Bảng thống kê các client ");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] action = { "update", "delete"};
        mainFunction = new MainFunction(action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        contentCenter.add(functionBar, BorderLayout.NORTH);
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentCenter.add(main, BorderLayout.CENTER);
        main.add(scrollTableSanPham);
    }
    public ThongKe(Main m) {
        this.m = m;
        this.maxConnections= m.maxConnections;
        this.countClient = m.countClient;
        this.notAccess= m.notAccess;
        initComponent();
        loadDataTalbe();
    }
    public void loadDataTalbe() {
        int n=1;
        tblModel.setRowCount(0);
        tblModel.addRow(new Object[]{1, maxConnections + " client", countClient + " client", notAccess + " client", formatter1.format(java.time.LocalDate.now()), getTime()});
    }
    public void updateData(int maxConnections, int countClient, int notAccess) {
        this.maxConnections = maxConnections;
        this.countClient = countClient;
        this.notAccess = notAccess;
        loadDataTalbe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    public String getTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
       return formattedTime;
    }




    private void initPadding() {
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
    }

    public PanelBorderRadius getMain() {
        return main;
    }

    public void setMain(PanelBorderRadius main) {
        this.main = main;
    }
}
