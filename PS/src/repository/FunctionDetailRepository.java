package repository;

import model.Brand;
import model.FunctionDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionDetailRepository implements IFunctionDetailRepository{
    private static final String SELECT="select * from danhmucchucnang";
    @Override
    public List<FunctionDetail> findAll() {
        List<FunctionDetail> brandList = new ArrayList<>();
        FunctionDetail detail = null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                detail = new FunctionDetail();
                detail.setMachucnang(rs.getString("machucnang"));
                detail.setTenchucnang(rs.getString("tenchucnang"));
                detail.setStatus(rs.getInt("trangthai"));
                brandList.add(detail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return brandList;
    }

}
