package repository;

import model.Ram;
import model.WareHouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RamRepository implements IRamRepository{
    private static final String SELECT ="select * from dungluongram where madlram=?";
    private static final String SELECT_ALL="select * from dungluongram";
    private static final String INSERT="insert into dungluongram set kichthuocram=?,trangthai=?";
    private static final String UPDATE="update dungluongram set kichthuocram=?,trangthai=? where madlram=?";
    private static final String DELETE="delete from dungluongram where madlram=?";
    @Override
    public Ram findById(int id) {
       Ram color = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                color= new Ram();
                color.setId(rs.getInt("madlram"));
                color.setName(rs.getString("kichthuocram"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return color;
    }

    @Override
    public List<Ram> findAll() {
        List<Ram> list = new ArrayList<>();
        Ram ramList= null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                ramList= new Ram();
                ramList.setId(rs.getInt("madlram"));
                ramList.setName(rs.getString("kichthuocram"));
                list.add(ramList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void editOr(Ram or) {
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
    public void addOr(Ram or) {
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
