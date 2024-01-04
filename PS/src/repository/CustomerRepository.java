package repository;

import model.ColorBr;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository{
    private static final String SELECT="select * from nhanvien";
    private static final String INSERT_INTO="insert into nhanvien set('hoten','gioitinh','ngaysinh','sdt','email','trangthai') values(?,?,?,?,?,?)";
    private static final String UPDATE="update nhanvien set('hoten','gioitinh','ngaysinh','sdt','email','trangthai') values(?,?,?,?,?,?) where nhanvien.manv=?";
    private static final String DELETE="delete  from nhanvien where manv=?";
    private static final String SEARCH="select * from nhanvien where nhanvien.hoten=?";
    private static final String SELECT_BY_ID="select nhanvien.hoten from nhanvien where nhanvien.manv=? ";
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Customer cus= null;
        try {
            Connection conn = BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                cus= new Customer();
                cus.setId(rs.getInt("manv"));
                cus.setName(rs.getString("hoten"));
                cus.setGender(rs.getInt("gioitinh"));
                cus.setDate(rs.getString("ngaysinh"));
                cus.setSdt(rs.getString("sdt"));
                cus.setEmail(rs.getString("email"));
                cus.setStatus(rs.getInt("trangthai"));
                customers.add(cus);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public List<Customer> searchByName(String name) {
        List<Customer> customers = new ArrayList<>();
        Customer cus= null;
        try {
            Connection conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps = conn.prepareStatement(SEARCH);
            ps.setString(1, "%"+name+"%");
            ps.executeUpdate();

            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                cus= new Customer();
                cus.setId(rs.getInt("manv"));
                cus.setName(rs.getString("hoten"));
                cus.setGender(rs.getInt("gioitinh"));
                cus.setSdt(rs.getString("sdt"));
                cus.setEmail(rs.getString("email"));
                cus.setStatus(rs.getInt("trangthai"));
                customers.add(cus);
            }
            if (!currentAutoCommit) {
                conn.setAutoCommit(true);
                System.out.println("Autocommit has been set to true.");
            }
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void editCus(Customer customer) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getGender());
            ps.setString(3, customer.getDate());
            ps.setString(4, customer.getSdt());
            ps.setString(5, customer.getEmail());
            ps.setInt(6, 1);
            ps.setInt(7,customer.getId());
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
    public void addCus(Customer customer) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO);
            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getGender());
            ps.setString(3, customer.getDate());
            ps.setString(4, customer.getSdt());
            ps.setString(5, customer.getEmail());
            ps.setInt(6, 1);
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

    @Override
    public String selectName(int id) {
         String name=null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
             name=rs.getString("hoten");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}
