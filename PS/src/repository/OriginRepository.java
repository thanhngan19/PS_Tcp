package repository;

import model.Origin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OriginRepository implements IOriginRepository{

    private static final String SELECT ="select * from xuatxu where maxuatxu=?";
    private static final String SELECT_ALL="select * from xuatxu";
    private static final String INSERT="insert into xuatxu set tenxuatxu=?,trangthai=?";
    private static final String UPDATE="update xuatxu set tenxuatxu=?,trangthai=? where maxuatxu=?";
    private static final String DELETE="delete from xuatxu where maxuatxu=?";

    @Override
    public Origin findById(int id) {
        Origin color = null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                color= new Origin();
                color.setId(rs.getInt("maxuatxu"));
                color.setName(rs.getString("tenxuatxu"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return color;

    }

    @Override
    public List<Origin> findAll() {
        List<Origin> list = new ArrayList<Origin>();
        Origin origin = null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                origin = new Origin();
                origin.setId(rs.getInt("maxuatxu"));
                origin.setName(rs.getString("tenxuatxu"));
                list.add(origin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void editOr(Origin or) {
        try {
            Connection conn=BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(UPDATE);
            ps.setString(1, or.getName());
            ps.setInt(2, or.getStatus());
            ps.setInt(3, or.getId());
            ps.executeUpdate();
            if (!currentAutoCommit) {
                conn.setAutoCommit(true);
                System.out.println("Autocommit has been set to true.");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOr(Origin or) {
        try {
            Connection conn=BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(INSERT);
            ps.setString(1, or.getName());
            ps.setInt(2, or.getStatus());
            ps.executeUpdate();
            if (!currentAutoCommit) {
                conn.setAutoCommit(true);
                System.out.println("Autocommit has been set to true.");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOr(int id) {
        try {
            Connection conn=BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(DELETE);
            ps.setInt(1,id);
            ps.executeUpdate();
            if (!currentAutoCommit) {
                conn.setAutoCommit(true);
                System.out.println("Autocommit has been set to true.");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
