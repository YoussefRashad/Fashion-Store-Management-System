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
public final class Employee extends Person implements Serializable{
    
    private String DateOfJoin="", DateOfBirth="" ,Qualification="";
    private final String FilePath = "Employee.bin";
    private ArrayList<Employee> emps = new ArrayList<Employee>();
    
    public Employee() {
    }
    public Employee(String DateOfJoin, String DateOfBirth, String Qualification, int ID, String Name, String UserName, String Password, String Gender, String PhoneNo, String Email) {
        super(ID, Name, UserName, Password, Gender, PhoneNo, Email);
        this.DateOfJoin = DateOfJoin;
        this.DateOfBirth = DateOfBirth;
        this.Qualification = Qualification;
    }
    public String getDateOfBirth() {
        return this.DateOfBirth;
    }

    public void setDateOfBirth(String DateOfBirth) {
        String Sepreted[] = DateOfBirth.split("#");
        this.DateOfBirth=Sepreted[0] +"#"+ Sepreted[1] + "#"+Sepreted[2];
    }

    public String getQualification() {
        return this.Qualification;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }
    public String getDateOfJoin() {
        return this.DateOfJoin;
    }

    public void setDateOfJoin(String DateOfJoin) {
        String Sepreted[] = DateOfJoin.split("#");
        this.DateOfJoin=Sepreted[0] +"#"+ Sepreted[1] + "#"+Sepreted[2];
    }
     public boolean addEmployee()
    {
        loadFile();
        emps.add(this);
        return commitToFile();
    }
    public boolean removeEmployee(int ID)
    {
        if(searchEmployee(ID)){
            int index = getIndexOfAmdin(ID);
            emps.remove(index);
            return commitToFile();
        }
        return false;
    }
    public boolean editInEmployee(int ID)
    {
        if(searchEmployee(ID)){
            int index = getIndexOfAmdin(ID);
            emps.set(index, this);
            return commitToFile();
        }
        return false;
    }
    public boolean commitToFile()
    {
        return FMangerBinary.writeBinary(FilePath, emps);
    }
    public Employee getEmployee(int ID)
    {
        loadFile();
        for(Employee ad : emps)
            if(ad.ID==ID)
                return ad;
        return null;
    }
    private int getIndexOfAmdin(int ID)
    {
        for(int i=0;i<emps.size();i++)
            if(emps.get(i).ID==ID)
                return i;
        return -1;
    }
    public boolean searchEmployee(int ID)
    {
        loadFile();
        for(Employee ad : emps)
            if(ad.ID==ID)
                return true;
        return false;
    }
    public void loadFile()
    {
        emps=(ArrayList<Employee>)FMangerBinary.readBinary(FilePath);
    }
//    private String getFile()
//    {
//        return this.ID+"`"+this.Name+"`"+this.UserName+"`"+this.Email+"`"+this.Password+"`"+this.PhoneNo+"`"+this.Gender+"`"
//                +this.Qualification+"`"+this.DateOfJoin+"`"+this.DateOfBirth+"`";
//    }
    public ArrayList ListAllEmployee()
    {
        loadFile();
        return emps;
    }
    public boolean Dublicate(int ID)
    {
        loadFile();
        for(Employee e : emps)
            if(e.ID==ID)
                return true;
        return false;
    }

    @Override
    public boolean LogIn(String Email, String Pass) {
        if(Email.equals("Employee@yahoo.com")&&Pass.equals("12345678"))
            return true;
        loadFile();
        for(Employee ad : emps)
            if(ad.Email.equals(Email)&&ad.Password.equals(Pass))
                return true;
        return false;
    }
    
    
}
