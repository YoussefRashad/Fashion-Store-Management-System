/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fashion.store.management.system;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public final class Admin extends Person implements Serializable{
    private final String FilePath = "Admin.bin";
    private ArrayList <Admin> admins = new ArrayList<Admin>();
    
    
    public Admin() {
    }

    public Admin(int ID, String Name, String UserName, String Password, String Gender,  String PhoneNo,String Email) {
        super(ID, Name, UserName, Password, Gender, PhoneNo,Email);
    }
    
    public boolean addAdmin()
    {
        loadFile();
        admins.add(this);
        return commitToFile();
    }
    public boolean removeAdmin(int ID)
    {
        if(searchAdmin(ID)){
            int index = getIndexOfAmdin(ID);
            admins.remove(index);
            return commitToFile();
        }
        return false;
    }
    public boolean editInAdmin(int ID)
    {
        if(searchAdmin(ID)){
            int index = getIndexOfAmdin(ID);
            admins.set(index, this);
            return commitToFile();
        }
        return false;
    }
    public boolean commitToFile()
    {
        return FMangerBinary.writeBinary(FilePath, admins);
    }
    public Admin getAdmin(int ID)
    {
        loadFile();
        for(Admin ad : admins)
            if(ad.ID==ID)
                return ad;
        return null;
    }
    private int getIndexOfAmdin(int ID)
    {
        for(int i=0;i<admins.size();i++)
            if(admins.get(i).ID==ID)
                return i;
        return -1;
    }
    public boolean searchAdmin(int ID)
    {
        loadFile();
        for(Admin ad : admins)
            if(ad.ID==ID)
                return true;
        return false;
    }
    public void loadFile()
    {
        admins=(ArrayList<Admin>)FMangerBinary.readBinary(FilePath);
    }
//    private String getFile()
//    {
//        return this.ID+"`"+this.Name+"`"+this.UserName+"`"+this.Email+"`"+this.Password+"`"+this.PhoneNo+"`"+this.Gender+"`";
//    }
    public ArrayList ListAllAdmin()
    {
        loadFile();
        return admins;
    }
    public boolean Dublicate(int ID)
    {
        loadFile();
        for(Admin a : admins)
            if(a.ID==ID)
                return true;
        return false;
    }

    @Override
    public boolean LogIn(String Email, String Pass) {
        if(Email.equals("Admin@yahoo.com")&&Pass.equals("12345678"))
            return true;
        loadFile();
        for(Admin ad : admins)
            if(ad.Email.equals(Email)&&ad.Password.equals(Pass))
                return true;
        return false;
    }
    
    
    
    
    
    
}
