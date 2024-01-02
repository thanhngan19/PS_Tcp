package repository;

import model.Os;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OsRepository implements IOsRepository {
    private static final String SELECT ="select * from hedieuhanh where mahedieuhanh=?";
    private static final String SELECT_ALL="select * from hedieuhanh";
    private static final String INSERT="insert into hedieuhanh set tenhedieuhanh=?,trangthai=?";
    private static final String UPDATE="update hedieuhanh set tenhedieuhanh=?,trangthai=? where mahedieuhanh=?";
    private static final String DELETE="delete from hedieuhanh where mahedieuhanh=?";
    @Override
    public Os findById(int id) {

        Os color = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                color= new Os();
                color.setId(rs.getInt("mahedieuhanh"));
                color.setName(rs.getString("tenhedieuhanh"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return color;

    }

    @Override
    public List<Os> findAll() {
        List<Os> result = new ArrayList<Os>();
        Os osList= null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                osList = new Os();
                osList.setId(rs.getInt("mahedieuhanh"));
                osList.setName(rs.getString("tenhedieuhanh"));
                result.add(osList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void editOr(Os or) {
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
    public void addOr(Os or) {
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
