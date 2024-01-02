package repository;

import model.WareHouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WareHouseRepository implements IWareHouseRepository{
    private static final String SELECT ="select * from khuvuckho where makhuvuc=?";
    private static final String SELECT_ALL="select * from khuvuckho";
    @Override
    public WareHouse findById(int id) {
        WareHouse color = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                color= new WareHouse();
                color.setId(rs.getInt("makhuvuc"));
                color.setName(rs.getString("tenkhuvuc"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return color;

    }

    @Override
    public List<WareHouse> findAll() {
        List<WareHouse> result = new ArrayList<WareHouse>();
        WareHouse ware= null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                ware = new WareHouse();
                ware.setId(rs.getInt("makhuvuc"));
                ware.setName(rs.getString("tenkhuvuc"));
                result.add(ware);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return result;
    }
}
