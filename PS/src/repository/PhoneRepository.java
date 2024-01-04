package repository;

import model.Phone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepository implements IPhoneRepository{
    private IBrandRepository brand= new BrandRepository();
    private IOriginRepository orgin = new OriginRepository();
    private IOsRepository hdh= new OsRepository();
    private IWareHouseRepository ware= new WareHouseRepository();
    private static final String SELECT="select * from sanpham";
    private static final String FIND_BY_ID="select * from sanpham where masp=?";
    private static final String INSERT="INSERT INTO `sanpham` ( `tensp`, `hinhanh`, `xuatxu`, `chipxuly`, `dungluongpin`, `kichthuocman`, `hedieuhanh`, `phienbanhdh`, `camerasau`, `cameratruoc`, `thoigianbaohanh`, `thuonghieu`, `khuvuckho`, `soluongton`, `trangthai`) VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE `sanpham` SET `tensp`=?, `hinhanh`=?, `xuatxu`=?, `chipxuly`=?, `dungluongpin`=?, `kichthuocman`=?, `hedieuhanh`=?, `phienbanhdh`=?, `camerasau`=?, `cameratruoc`=?, `thoigianbaohanh`=?, `thuonghieu`=?, `khuvuckho`=?,  `trangthai`=? WHERE `masp`=?;";
    private static final String DELETE="delete from sanpham where masp=?";
    private static final String SELECT_ID="SELECT masp FROM sanpham ORDER BY masp DESC LIMIT 1;";
    private static final String SEARCH="select * from sanpham where tensp like ?";

    @Override
    public List<Phone> findAll() {
        List<Phone> list = new ArrayList<Phone>();
        Phone phone = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SELECT);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                phone= new Phone();
                phone.setId(rs.getInt("masp"));
                phone.setName(rs.getString("tensp"));
                phone.setImg(rs.getString("hinhanh"));
                phone.setOrigin(orgin.findById(rs.getInt("xuatxu")));
                phone.setChip(rs.getString("chipxuly"));
                phone.setPin(rs.getInt("dungluongpin"));
                phone.setInch(rs.getInt("kichthuocman"));
                phone.setOs(hdh.findById(rs.getInt("hedieuhanh")));
                phone.setiOs(rs.getInt("phienbanhdh"));
                phone.setRearCam(rs.getString("camerasau"));
                phone.setFrontCam(rs.getString("cameratruoc"));
                phone.setTime(rs.getInt("thoigianbaohanh"));
                phone.setBrand(brand.findById(rs.getInt("thuonghieu")));
                phone.setWareHouse(ware.findById(rs.getInt("khuvuckho")));
                phone.setQuantity(rs.getInt("soluongton"));
                phone.setStatus(rs.getInt("trangthai"));
                list.add(phone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public void addNew(Phone phone) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, phone.getName());
            ps.setString(2, phone.getImg());
            ps.setInt(3, phone.getOrigin().getId());
            ps.setString(4, phone.getChip());
            ps.setInt(5, phone.getPin());
            ps.setDouble(6, phone.getInch());
            ps.setInt(7, phone.getOs().getId());
            ps.setInt(8, phone.getiOs());
            ps.setString(9, phone.getRearCam());
            ps.setString(10, phone.getFrontCam());
            ps.setInt(11, phone.getTime());
            ps.setInt(12, phone.getBrand().getId());
            ps.setInt(13, phone.getWareHouse().getId());
            ps.setInt(14, phone.getQuantity());
            ps.setInt(15, 1);
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
    public void editPhone(Phone phone) {
        Connection conn = null;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, phone.getName());
            ps.setString(2, phone.getImg());
            ps.setInt(3, phone.getOrigin().getId());
            ps.setString(4, phone.getChip());
            ps.setInt(5, phone.getPin());
            ps.setDouble(6, phone.getInch());
            ps.setInt(7, phone.getOs().getId());
            ps.setInt(8, phone.getiOs());
            ps.setString(9, phone.getRearCam());
            ps.setString(10, phone.getFrontCam());
            ps.setInt(11, phone.getTime());
            ps.setInt(12, phone.getBrand().getId());
            ps.setInt(13, phone.getWareHouse().getId());
            ps.setInt(14, 0);
            ps.setInt(15, phone.getId());
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
    public void deletePhone(int id) {
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
    public List<Phone> searchPhone(String url) {
        List<Phone> list =new ArrayList<>();
        Phone phone = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(SEARCH);
            ps.setString(1, "%"+url+"%");
            ResultSet rs=  ps.executeQuery();
            while(rs.next()) {
                phone= new Phone();
                phone.setId(rs.getInt("masp"));
                phone.setName(rs.getString("tensp"));
                phone.setImg(rs.getString("hinhanh"));
                phone.setOrigin(orgin.findById(rs.getInt("xuatxu")));
                phone.setChip(rs.getString("chipxuly"));
                phone.setPin(rs.getInt("dungluongpin"));
                phone.setInch(rs.getInt("kichthuocman"));
                phone.setOs(hdh.findById(rs.getInt("hedieuhanh")));
                phone.setiOs(rs.getInt("phienbanhdh"));
                phone.setRearCam(rs.getString("camerasau"));
                phone.setFrontCam(rs.getString("cameratruoc"));
                phone.setTime(rs.getInt("thoigianbaohanh"));
                phone.setBrand(brand.findById(rs.getInt("thuonghieu")));
                phone.setWareHouse(ware.findById(rs.getInt("khuvuckho")));
                phone.setQuantity(rs.getInt("soluongton"));
                list.add(phone);
            }
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Phone findById(int id) {
        Phone phone = null;
        try {
            Connection conn=BaseRepository.getConnection();
            PreparedStatement ps= conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()) {
                phone= new Phone();
                phone.setId(rs.getInt("masp"));
                phone.setName(rs.getString("tensp"));
                phone.setImg(rs.getString("hinhanh"));
                phone.setOrigin(orgin.findById(rs.getInt("xuatxu")));
                phone.setChip(rs.getString("chipxuly"));
                phone.setPin(rs.getInt("dungluongpin"));
                phone.setInch(rs.getInt("kichthuocman"));
                phone.setOs(hdh.findById(rs.getInt("hedieuhanh")));
                phone.setiOs(rs.getInt("phienbanhdh"));
                phone.setRearCam(rs.getString("camerasau"));
                phone.setFrontCam(rs.getString("cameratruoc"));
                phone.setTime(rs.getInt("thoigianbaohanh"));
                phone.setBrand(brand.findById(rs.getInt("thuonghieu")));
                phone.setWareHouse(ware.findById(rs.getInt("khuvuckho")));
                phone.setQuantity(rs.getInt("soluongton"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phone;
    }

    @Override
    public int selectId() {
        Connection conn = null;
        int index = 0;
        try {
            conn = BaseRepository.getConnection();
            boolean currentAutoCommit = conn.getAutoCommit();
            System.out.println("Trạng thái AutoCommit hiện tại: " + currentAutoCommit);
            conn.setAutoCommit(false);
            System.out.println("Bắt đầu giao dịch.");
            PreparedStatement ps = conn.prepareStatement(SELECT_ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                index=rs.getInt(1);
            }
            System.out.println("Giao dịch đã hoàn thành.");
        } catch (SQLException e) {
            try {
                conn.rollback();
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
        return index;
    }


}
