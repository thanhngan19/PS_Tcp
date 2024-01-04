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
    private static final String INSERT_INTO="insert into khuvuckho set('tenkhuvuc','ghichu','trangthai') values(?,?,?)";
    private static final String UPDATE="update khuvuckho set('tenkhuvuc','ghichu','trangthai') values(?,?,?) where nhanvien.manv=?";
    private static final String DELETE="delete  from khuvuckho where makhuvuc=?";
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
                ware.setNote(rs.getString("ghichu"));
                ware.setStatus(rs.getInt("trangthai"));
                result.add(ware);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return result;
    }

    @Override
    public void editCus(WareHouse customer) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getNote());
            ps.setInt(3, 1);
            ps.setInt(4, customer.getId());
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
    public void addCus(WareHouse customer) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getNote());
            ps.setInt(3, 1);
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
    public void deleteCustomer(int id) {
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
