package repository;

import model.ColorBr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColorRepository implements IColorRepository {

    private static final String SELECT ="select * from mausac where mamau=?";
    private static final String SELECT_ALL="select * from mausac ";
    private static final String INSERT="insert into mausac set tenmau=?,trangthai=?";
    private static final String UPDATE="update mausac set tenmau=?,trangthai=? where mamau=?";
    private static final String DELETE="delete from mausac where mamau=?";
    @Override
    public ColorBr findById(int id) {
        ColorBr color = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                color= new ColorBr();
                color.setId(rs.getInt("mamau"));
                color.setName(rs.getString("tenmau"));
                color.setStatus(rs.getInt("trangthai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return color;

    }

    @Override
    public List<ColorBr> findAll() {
        List<ColorBr> result = new ArrayList<ColorBr>();
        ColorBr item = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                item = new ColorBr();
                item.setId(rs.getInt("mamau"));
                item.setName(rs.getString("tenmau"));
                result.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void editColor(ColorBr color) {
        try {
            Connection conn=BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(UPDATE);
            ps.setString(1, color.getName());
            ps.setInt(2, color.getStatus());
            ps.setInt(3, color.getId());
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
    public void addColor(ColorBr color) {
        try {
            Connection conn=BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(INSERT);
            ps.setString(1, color.getName());
            ps.setInt(2, color.getStatus());
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
    public void deleteColor(int id) {
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
