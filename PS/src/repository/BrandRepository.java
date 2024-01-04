package repository;

import model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository implements IBrandRepository {

    private static final String SELECT= "select * from thuonghieu where mathuonghieu=?";
    private static final String SELECT_ALL="select * from thuonghieu";
    private static final String UPDATE="update thuonghieu set tenthuonghieu=?,trangthai=? where mathuonghieu=?";
    private static final String INSERT="insert into thuonghieu set tenthuonghieu=?,trangthai=?";
    private static final String DELETE="delete from thuonghieu where thuonghieu.mathuonghieu=?";
    @Override
    public Brand findById(int id) {
        Brand brand=null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                brand = new Brand();
                brand.setId(rs.getInt("mathuonghieu"));
                brand.setName(rs.getString("tenthuonghieu"));
                brand.setStatus(rs.getInt("trangthai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brand;


    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = new ArrayList<>();
        Brand brand = null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                brand = new Brand();
                brand.setId(rs.getInt("mathuonghieu"));
                brand.setName(rs.getString("tenthuonghieu"));
                brand.setStatus(rs.getInt("trangthai"));
                brandList.add(brand);
            }
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brandList;
    }

    @Override
    public void editBrand(Brand br) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, br.getName());
            ps.setInt(2, 0);
            ps.setInt(3,br.getId());
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
    public void addBrand(Brand br) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, br.getName());
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
    public void deleteBrand(int id) {
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
