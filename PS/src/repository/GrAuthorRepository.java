package repository;

import model.GrAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrAuthorRepository implements IGrAuthorRepository{
    private static final String SELECT="select * from nhomquyen";
    private static final String SELECT_BY_ID="select * from nhomquyen where nhomquyen.manhomquyen=?";
    private static final String INSERT="insert into nhomquyen ('tennhomquyen','trangthai')";
    private static final String DELETE= "delete from nhomquyen where nhomquyen.manhomquyen=?";
    private static final String UPDATE="update nhomquyen set 'tennhomquyen'=? where nhomquyen.manhomquyen=? ";
    @Override
    public List<GrAuthor> findAll() {
        List<GrAuthor> list = new ArrayList<>();
        GrAuthor author =null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                author= new GrAuthor();
                author.setId(rs.getInt("manhomquyen"));
                author.setName(rs.getString("tennhomquyen"));
                author.setStatus(rs.getInt("trangthai"));
                list.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void addNew(GrAuthor author) {
        try {
            Connection conn= BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(INSERT);
            ps.setString(1, author.getName());
            ps.setInt(2, 1);
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
    public void editAuthor(GrAuthor author) {
        try {
            Connection conn= BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(UPDATE);
            ps.setString(1, author.getName());
            ps.setInt(2, author.getId());
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
    public void deleteAuthor(int id)  {
        try {
            Connection conn= BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Current AutoCommit Status: " + currentAutoCommit);
            PreparedStatement ps= conn.prepareStatement(DELETE);
            ps.setInt(1, id);
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
    public GrAuthor selectById(int id) {

        GrAuthor author =null;
        try {
            Connection conn= BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                author= new GrAuthor();
                author.setId(rs.getInt("manhomquyen"));
                author.setName(rs.getString("tennhomquyen"));
                author.setStatus(rs.getInt("trangthai"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return author;
    }
}
