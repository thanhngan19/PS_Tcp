package connect;


import com.google.gson.reflect.TypeToken;
import connect.Component.MenuTaskbar;
import model.*;

import org.apache.poi.ss.formula.functions.T;
import service.*;

import java.io.*;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;

import javax.swing.*;

public class ThreadProcessing extends Thread {
    private IPhoneService phone  = new PhoneService();
    private IUserService userList= new UserService();
    private IBrandService brand= new BrandService();
    private IColorService color= new ColorService();
    private IRamService ram= new RamService();
    private IRomService rom = new RomService();
    private IVersionPhoneService ver= new VersionPhoneService();
    private IWareHouseService ware= new WareHouseService();
    private IOsService os= new OSService();
    private IOriginService origin= new OriginService();
    private IFunctionDetailService fDetail= new FunctionDetailService();
    private IGrAuthorService author= new GrAuthorService();
    private ICustomerService customer= new CustomerService();
    private IAuthorDetailService auDe= new AuthorDetailService();
    private ISupplierService sup= new SupplierService();
    Socket  socket;
   ServerSocketConnect conn;
   private Gson gson = new Gson();
    Vector<ThreadProcessing> clientList ;
   private ListTranmission list;
   private static List<UserStatus> statusList= new ArrayList<UserStatus>();
   private UserStatus client=null;
    ObjectOutputStream out;
    ObjectInputStream in;
    public ThreadProcessing(Socket socket, ServerSocketConnect conn) {
        this.socket = socket;
        this.conn = conn;
        clientList= this.conn.clientList;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
            try {
                List<Phone> listPhone = phone.findAll();
                List<ColorBr> listColor = color.findAll();
                List<Ram> listRam = ram.findAll();
                List<Rom> listRom = rom.findAll();
                List<Brand> listBrand = brand.findAll();
                List<Origin> listOrigin = origin.findAll();
                List<VersionPhone> listVersion = ver.findAll();
                List<Os> listOs = os.findAll();
                List<WareHouse> listWareHouse = ware.findAll();
                List<User> listUser = userList.list();
                List<GrAuthor> listGrAuthor = author.findAll();
                List<Customer> listCustomer = customer.findAll();
                List<FunctionDetail> listFunctionDetail = fDetail.findAll();
                List<AuthorDetail> listAuthorDetail = auDe.findAll();
                List<Supplier> listSupplier = sup.findAll();
                String result = (String) in.readObject();
                System.out.println("result: " + result);
                list = new ListTranmission(listPhone, listColor, listRam, listRom, listBrand, listOrigin, listVersion, listOs, listWareHouse, listUser, listFunctionDetail, listGrAuthor, listCustomer, listAuthorDetail, listSupplier);
                System.out.println("Đã gửi dữ liệu đến client");
                String json = gson.toJson(list);
                out.writeObject(json);
                out.flush();
                String userName = (String) in.readObject();
                client = new UserStatus();
                client.setUserName(userName);
                client.setName(customer.selectName(userList.getUser(userName)));
                client.setStatus("ONLINE");
                statusList.add(client);
                MenuTaskbar.changeTableStatus(statusList);
                while (true) {
                    ListTransfer myObj = gson.fromJson((String) in.readObject(), ListTransfer.class);
                    System.out.println("đã inf nhận từ client");
                    if (myObj.getPhoneEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  phone");
                                phone.editPhone(myObj.getPhoneEdit());
                                for (int i = 0; i < this.conn.clientList.size(); ++i) {
                                    out = new ObjectOutputStream(((ThreadProcessing) this.conn.clientList.get(i)).socket.getOutputStream());
                                    list.setListPhone(phone.findAll());
                                    out.writeObject(gson.toJson(list));
                                    out.flush();
                                }
                                System.out.println("Edit phone successfully");
                                break;
                            case "add":
                                System.out.println("add phone");
                                phone.addNew(myObj.getPhoneEdit());
                                int idPhone = phone.selectId();
                                System.out.println(idPhone);
                                myObj.getVer().setPhone(phone.findById(idPhone));
                                System.out.println(myObj.getVer().getRam().getName());
                                System.out.println(myObj.getVer().getRom().getName());
                                System.out.println(myObj.getVer().getColor().getName());
                                System.out.println(myObj.getVer().getPhone().getId());
                                System.out.println(myObj.getVer().getRam().getName());
                                ver.addVer(myObj.getVer());
                                list.setListPhone(phone.findAll());
                                list.setListVer(ver.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add phone successfully");
                                break;
                            case "delete":
                                System.out.println("delete phone");
                                System.out.println(myObj.getPhoneEdit().getId());
                                phone.deletePhone(myObj.getPhoneEdit().getId());
                                list.setListPhone(phone.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete phone successfully");
                                break;
                        }
                    }
                    if (myObj.getBrEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  brand");
                                brand.addOr(myObj.getBrEdit());
                                list.setListBrand(brand.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit brand successfully");
                                break;
                            case "add":
                                System.out.println("add brand");
                                brand.addOr(myObj.getBrEdit());
                                for (ThreadProcessing clientThread : clientList) {
                                    list.setListBrand(brand.findAll());
                                    clientThread.out.writeObject(gson.toJson(list));
                                    clientThread.out.flush();
                                }
                                System.out.println("Add brand successfully");
                                break;
                            case "delete":
                                brand.deleteOr(myObj.getBrEdit().getId());
                                list.setListPhone(phone.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete phone successfully");
                                break;
                        }
                    }
                    if (myObj.getOsEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  os");
                                os.editOr(myObj.getOsEdit());
                                list.setListOs(os.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit os successfully");
                                break;
                            case "add":
                                System.out.println("add os");
                                os.addOr(myObj.getOsEdit());
                                list.setListOs(os.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add os successfully");
                                break;
                            case "delete":
                                os.deleteOr(myObj.getOsEdit().getId());
                                list.setListOs(os.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete phone successfully");
                                break;
                        }
                    }
                    if (myObj.getRomEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  rom");
                                rom.editOr(myObj.getRomEdit());
                                list.setListRom(rom.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit rom successfully");
                                break;
                            case "add":
                                System.out.println("add rom");
                                rom.addOr(myObj.getRomEdit());
                                list.setListRom(rom.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add os successfully");
                                break;
                            case "delete":
                                rom.deleteOr(myObj.getRomEdit().getId());
                                list.setListOs(os.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete phone successfully");
                                break;
                        }
                    }
                    if (myObj.getRamEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  ram");
                                ram.editOr(myObj.getRamEdit());
                                list.setListRam(ram.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit ram successfully");
                                break;
                            case "add":
                                System.out.println("add ram");
                                ram.addOr(myObj.getRamEdit());
                                list.setListRam(ram.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add ram successfully");
                                break;
                            case "delete":
                                ram.deleteOr(myObj.getRamEdit().getId());
                                list.setListRam(ram.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete ram successfully");
                                break;
                        }
                    }
                    if (myObj.getColorEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  color");
                                color.editOr(myObj.getColorEdit());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit color successfully");
                                break;
                            case "add":
                                System.out.println("add color");
                                color.addOr(myObj.getColorEdit());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add color successfully");
                                break;
                            case "delete":
                                color.deleteOr(myObj.getColorEdit().getId());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete ram successfully");
                                break;
                        }
                    }
                    if (myObj.getColorEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  color");
                                color.editOr(myObj.getColorEdit());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit color successfully");
                                break;
                            case "add":
                                System.out.println("add color");
                                color.addOr(myObj.getColorEdit());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add color successfully");
                                break;
                            case "delete":
                                color.deleteOr(myObj.getColorEdit().getId());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete ram successfully");
                                break;
                        }
                    }
                    if (myObj.getColorEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  color");
                                color.editOr(myObj.getColorEdit());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit color successfully");
                                break;
                            case "add":
                                System.out.println("add color");
                                color.addOr(myObj.getColorEdit());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add color successfully");
                                break;
                            case "delete":
                                color.deleteOr(myObj.getColorEdit().getId());
                                list.setListColor(color.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete ram successfully");
                                break;
                        }
                    }
                    if (myObj.getOrEdit() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  origin");
                                origin.editOr(myObj.getOrEdit());
                                list.setListOr(origin.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit origin successfully");
                                break;
                            case "add":
                                System.out.println("add origin");
                                origin.addOr(myObj.getOrEdit());
                                list.setListOr(origin.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add origin successfully");
                                break;
                            case "delete":
                                origin.deleteOr(myObj.getOrEdit().getId());
                                list.setListOr(origin.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete origin successfully");
                                break;
                        }
                    }
                    if (myObj.getVer() != null) {
                        switch (myObj.getMessage()) {
                            case "edit":
                                System.out.println(" edit  cấu hình");
                                ver.editVer(myObj.getVer());
                                list.setListVer(ver.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Edit ch successfully");
                                break;
                            case "add":
                                System.out.println("add origin");
                                ver.addVer(myObj.getVer());
                                list.setListVer(ver.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Add origin successfully");
                                break;
                            case "delete":
                                origin.deleteOr(myObj.getVer().getId());
                                list.setListVer(ver.findAll());
                                out.writeObject(gson.toJson(list));
                                out.flush();
                                System.out.println("Delete origin successfully");
                                break;
                        }
                    }


                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

        }
    }
}
