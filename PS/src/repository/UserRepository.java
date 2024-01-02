package repository;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{
    private static final String SELECT="select * from taikhoan";
    private static final String UPDATE="update taikhoan set ('matkhau','manhomquyen','tendangnhap','trangthai') values(?,?,?,?) where taikhoan.manv=?";
    private static final String SELECT_STATUS="select * from taikhoan where trangthai =0";
    private static final String SELECT_USERNAME="select taikhoan.manv from taikhoan where taikhoan.tendangnhap=?";
    private IGrAuthorRepository gr= new GrAuthorRepository();

    @Override
    public List<User> list() {
        User userFind= null;
        List<User> userList= new ArrayList<User>();
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                userFind= new User();
                userFind.setId(rs.getInt("manv"));
                userFind.setPassWord(rs.getString("matkhau"));
                userFind.setManhomquyen(gr.selectById(rs.getInt("manhomquyen")));
                userFind.setUserName(rs.getString("tendangnhap"));
                userFind.setStatus(rs.getInt("trangthai"));
                userList.add(userFind);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void editUser(User user) {
        try {
            Connection conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, user.getPassWord());
            ps.setInt(2, user.getManhomquyen().getId());
            ps.setString(3, user.getUserName());
            ps.setInt(4, user.getStatus());
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
    public List<User> selectUser() {
        User userFind= null;
        List<User> userList= new ArrayList<User>();
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_STATUS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                userFind= new User();
                userFind.setId(rs.getInt("manv"));
                userFind.setPassWord(rs.getString("matkhau"));
                userFind.setManhomquyen(gr.selectById(rs.getInt("manhomquyen")));
                userFind.setUserName(rs.getString("tendangnhap"));
                userFind.setStatus(rs.getInt("trangthai"));
                userList.add(userFind);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public int getUser(String name) {
        int manv=0;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_USERNAME);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
              manv= rs.getInt("manv");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return manv;
    }
}
