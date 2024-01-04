package repository;

import model.ColorBr;
import model.Ram;
import model.VersionPhone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VersionPhoneRepository implements IVersionPhoneRepository{
    private static final String INSERT="insert into phienbansanpham set masp=?,rom=?,ram=?,mausac=?,gianhap=?,giaxuat=?,soluongton=?";
    private static final String SELECT="select * from phienbansanpham";
    private static final String UPDATE="update  phienbansanpham set masp=?,rom=?,ram=?,mausac=?,gianhap=?,giaxuat=?,soluongton=? where maphienbansp=?";
    private static final String DELETE="delete from phienbansanpham where masp=?";
    private IPhoneRepository phone = new PhoneRepository();
    private IRamRepository ram= new RamRepository();
    private IRomRepository rom= new RomRepository();
    private IColorRepository color= new ColorRepository();
    @Override
    public void addNew(VersionPhone ver) {
        try {
            Connection conn=BaseRepository.getConnection();
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setInt(1, ver.getPhone().getId());
            ps.setInt(2, ver.getRam().getId());
            ps.setInt(3, ver.getRom().getId());
            ps.setInt(4, ver.getColor().getId());
            ps.setDouble(5, ver.getInPrice());
            ps.setDouble(6, ver.getExPrice());
            ps.setInt(7,10);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Giao dịch đã hoàn thành.");
        } catch (SQLException e) {
            System.out.println("Transaction canceled due to SQLException.");
        }



    }

    @Override
    public List<VersionPhone> findAll() {
        List<VersionPhone> list = new ArrayList<>();
        VersionPhone ramList= null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                ramList= new VersionPhone();
                ramList.setPhone(phone.findById(rs.getInt("masp")));
                ramList.setRam(ram.findById(rs.getInt("ram")));
                ramList.setRom(rom.findById(rs.getInt("rom")));
                ramList.setColor(color.findById(rs.getInt("mausac")));
                ramList.setInPrice(rs.getDouble("gianhap"));
                ramList.setExPrice(rs.getDouble("giaxuat"));
                ramList.setQuantity(rs.getInt("soluongton"));
                ramList.setStatus(rs.getInt("trangthai"));
                list.add(ramList);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void editVer(VersionPhone ver) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps= conn.prepareStatement(UPDATE);
            ps.setInt(1, ver.getPhone().getId());
            ps.setInt(2, ver.getRam().getId());
            ps.setInt(3, ver.getRom().getId());
            ps.setInt(4, ver.getColor().getId());
            ps.setDouble(5, ver.getInPrice());
            ps.setDouble(6, ver.getExPrice());
            ps.setInt(7, ver.getQuantity());
            ps.setInt(8,ver.getId());
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
    public void delete(int ver) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, ver);
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
