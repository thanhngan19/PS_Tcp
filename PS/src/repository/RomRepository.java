package repository;

import model.Ram;
import model.Rom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RomRepository implements IRomRepository{

    private static final String SELECT ="select * from dungluongrom where madlrom=?";
    private static final String SELECT_ALL="select * from dungluongrom";;
    private static final String INSERT="insert into dungluongrom set kichthuocrom=?,trangthai=?";
    private static final String UPDATE="update dungluongrom set kichthuocrom=?,trangthai=? where madlrom=?";
    private static final String DELETE="delete from dungluongrom where madlrom=?";
    @Override
    public Rom findById(int id) {
        Rom color = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                color= new Rom();
                color.setId(rs.getInt("madlrom"));
                color.setName(rs.getString("kichthuocrom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return color;
    }

    @Override
    public List<Rom> findAll() {
        List<Rom> list = new ArrayList<>();
        try {
            Connection conn=BaseRepository.getConnection();
            Rom ramList= null;
            PreparedStatement ps= conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                ramList= new Rom();
                ramList.setId(rs.getInt("madlrom"));
                ramList.setName(rs.getString("kichthuocrom"));
                list.add(ramList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void editOr(Rom or) {
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
    public void addOr(Rom or) {
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
