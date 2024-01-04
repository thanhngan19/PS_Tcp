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
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, or.getName());
            ps.setInt(2, 1);
            ps.setInt(3,or.getId());
            ps.executeUpdate();
            conn.commit();
            System.out.println("Giao dịch đã hoàn thành.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Giao dịch đã bị hủy bỏ.");
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);;
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("AutoCommit đã được đặt về true.");

        }
    }

    @Override
    public void addOr(Ram or) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, or.getName());
            ps.setInt(2, 1);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Giao dịch đã hoàn thành.");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Giao dịch đã bị hủy bỏ.");
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);;
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("AutoCommit đã được đặt về true.");

        }
    }

    @Override
    public void deleteOr(int id) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Giao dịch đã hoàn thành.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Giao dịch đã bị hủy bỏ.");
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("AutoCommit đã được đặt về true.");

        }

    }
}
