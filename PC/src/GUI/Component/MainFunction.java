package GUI.Component;


import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public final class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btn = new HashMap<>();


    public MainFunction(int manhomquyen, String chucnang, String[] listBtn) {
        initData();
        initComponent(manhomquyen, chucnang, listBtn);
    }

    public void initData() {
        btn.put("create", new ButtonToolBar("THÊM", "add.svg", "create"));
        btn.put("delete", new ButtonToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ButtonToolBar("SỬA", "edit.svg", "update"));
        btn.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "cancel.svg", "delete"));
        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", "view"));
        btn.put("phone", new ButtonToolBar("XEM DS", "phone.svg", "view"));
    }

    private void initComponent(int manhomquyen, String chucnang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String btnn : listBtn) {
            this.add(btn.get(btnn));
                btn.get(btnn).setEnabled(true);
        }
    }
}
