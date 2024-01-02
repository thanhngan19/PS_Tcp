package repository;

import model.AuthorDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDetailRepository implements IAuthorDetailRepository{
    private static final String SELECT = "select * from ctquyen";
    @Override
    public List<AuthorDetail> findAll() {
        List<AuthorDetail> list = new ArrayList<AuthorDetail>();
        AuthorDetail item = null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                item = new AuthorDetail();
                item.setId(rs.getInt("manhomquyen"));
                item.setName(rs.getString("machucnang"));
                item.setAction(rs.getString("hanhdong"));
                list.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
