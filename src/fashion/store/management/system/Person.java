


package fashion.store.management.system;

import java.io.Serializable;




public abstract class Person  implements Serializable{
    protected int ID=0 ;
    protected String Name="" , UserName="" , Password="" , Gender="" , PhoneNo="" , Email=""  ;
    //public FileManager FManager = new FileManager();
    public FileManagerBinary FMangerBinary = new FileManagerBinary();
    
    public Person() {
    }

    public Person(int ID, String Name, String UserName, String Password, String Gender , String PhoneNo,String Email) {
        this.ID = ID;
        this.Name = Name;
        this.UserName = UserName;
        this.Password = Password;
        this.Gender = Gender;
        this.PhoneNo = PhoneNo;
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getGender() {
        return this.Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPhoneNo() {
        return this.PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }
    public abstract boolean LogIn(String Email , String Pass);

    
    
}
