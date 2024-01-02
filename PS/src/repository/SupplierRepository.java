package repository;

import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository implements ISupplierRepository{
    private static final String SELECT="select * from nhacungcap";
    @Override
    public List<Supplier> findAll() {
        List<Supplier> list = new ArrayList<Supplier>();
        Supplier sup = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                sup = new Supplier();
                sup.setId(rs.getInt("manhacungcap"));
                sup.setName(rs.getString("tennhacungcap"));
                sup.setAddress(rs.getString("diachi"));
                sup.setEmail(rs.getString("email"));
                sup.setSdt(rs.getString("sdt"));
                sup.setStatus(rs.getInt("trangthai"));
                list.add(sup);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
