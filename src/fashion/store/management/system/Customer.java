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
public final class Customer implements Serializable {
    
    private final String FilePath = "Customer.bin";
    private ArrayList<Customer>customers = new ArrayList<Customer>();
    private String Name="" , PhoneNo="", Email="";
    private double Price=0;
    public ArrayList<String> Clothes = new ArrayList<String>();
    public ArrayList<Integer> Clothes_No = new ArrayList<Integer>();
    
    private FileManagerBinary FManagerBinary = new FileManagerBinary();
    
    public Customer() {
    }

    public Customer(String Name, String PhoneNo, String Email, double salary) {
        this.Name = Name;
        this.PhoneNo = PhoneNo;
        this.Email = Email;
        this.Price = salary;
    }
    
    public double getPrice() {
        return this.Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public ArrayList<Integer> getClothes_No() {
        return this.Clothes_No;
    }

    public void setClothes_No(int Clothes_No) {
        this.Clothes_No.add(Clothes_No);
    }

    public ArrayList<String> getClothes() {
        return this.Clothes;
    }

    public void setClothes(String NewItem) {
        this.Clothes.add(NewItem);
    }
    
    
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNo() {
        return this.PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
    
     public boolean addCustomer()
    {
        loadFile();
        customers.add(this);
        return commitToFile();
    }
    public boolean removeCustomer(String Name)
    {
        if(searchCustomer(Name)){
            int index = getIndexOfAmdin(Name);
            customers.remove(index);
            return commitToFile();
        }
        return false;
    }
    public boolean editInCustomer(String Name)
    {
        if(searchCustomer(Name)){
            int index = getIndexOfAmdin(Name);
            customers.set(index, this);
            return commitToFile();
        }
        return false;
    }
    public boolean commitToFile()
    {
        return FManagerBinary.writeBinary(FilePath, customers);
    }
    public Customer getCustomer(String Name)
    {
        loadFile();
        for(Customer ad : customers)
            if(ad.Name.equals(Name))
                return ad;
        return null;
    }
    private int getIndexOfAmdin(String Name)
    {
        for(int i=0;i<customers.size();i++)
            if(customers.get(i).Name.equals(Name))
                return i;
        return -1;
    }
    public boolean searchCustomer(String Name)
    {
        loadFile();
        for(Customer ad : customers)
            if(ad.Name.equals(Name))
                return true;
        return false;
    }
    public void loadFile()
    {
        customers=(ArrayList<Customer>)FManagerBinary.readBinary(FilePath);
    }
//    private String getFile()
//    {
//        String ItemsAndCount = "";
//        for(int i=0 ; i<this.Clothes.size();i++)
//            ItemsAndCount+=this.Clothes.get(i)+"`"+this.Clothes_No.get(i)+"`";
//        return this.Name+"`"+this.Email+"`"+this.PhoneNo+"`"+this.Price+"`"+ItemsAndCount;//mfe4 25r ` 34an m7tot fy el 25r `
//    }
    public ArrayList ListAllCustomer()
    {
        loadFile();
        return customers;
    }
    public boolean removeAllClothesAndNo() // use in Class ReturnItem
    {
        loadFile();
        int index=this.getIndexOfAmdin(this.Name);
/*        for(int i=0;i<this.customers.get(index).Clothes.size()*2;i++)
        {
            this.customers.get(index).Clothes.remove(i);
            this.customers.get(index).Clothes_No.remove(i);
        }    */
        this.customers.get(index).Clothes = new ArrayList<String>();
        this.customers.get(index).Clothes_No = new ArrayList<Integer>();
        return commitToFile();
    }
    public double CalcTotalCollected()
    {
        loadFile();
        double d =0.0;
        for(Customer c : customers)
            d+=c.Price;
        return d;
    }
    public boolean Dublicate(String Name)
    {
        loadFile();
        for(Customer c : customers)
            if(c.Name.equals(Name))
                return true;
        return false;
    }
}
