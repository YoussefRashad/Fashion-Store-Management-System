/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fashion.store.management.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public abstract class FileManager {
    
     public boolean Write (String Query , String FilePath , boolean append)
    {
        PrintWriter Writter = null;
        try{
            System.out.print("\nwritting in ! " + FilePath);
            Writter = new PrintWriter(new FileWriter(new File(FilePath),append));
            Writter.println(Query);
            System.out.println(" ... Done ! ");
            return true;
        }
        catch(IOException em)
        {
            System.out.println("Error in Writting : "+em);
        }
        finally{
            Writter.close();
        }
        return false;
    }
     
    public ArrayList Read(String FilePath)
    {
        Scanner Reader= null;
        try{
            System.out.println("Reading ! From " + FilePath);
            Reader = new Scanner(new File(FilePath));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error in Reading by Youssef : "+e);
        }
        
        if(FilePath=="Admin.txt")
        {
            ArrayList<Admin> admins = new ArrayList<Admin>();
            Admin x = null;
            while(Reader.hasNext()){
                x = new Admin();
                String Line = Reader.nextLine();
                String Seprated[]=Line.split("`");
                //ID ` Name ` UserName ` Email` Password ` PhoneNo ` Gender`
                x.setID(Integer.parseInt(Seprated[0]));
                x.setName(Seprated[1]);
                x.setUserName(Seprated[2]);
                x.setEmail(Seprated[3]);
                x.setPassword(Seprated[4]);
                x.setPhoneNo(Seprated[5]);
                x.setGender(Seprated[6]);
                admins.add(x);
            }
            return (ArrayList<Object>)(Object)admins;
        }
        if(FilePath=="Apparel.txt")
        {
            ArrayList<Apparel> app = new ArrayList<Apparel>();
            Apparel x = null;
            while(Reader.hasNext()){
                x = new Apparel();
                String Line = Reader.nextLine();
                String Seprated[]=Line.split("`");
                // StockID ` NameofCategory ` Type ` price ` Quantity  `
                x.setStockID(Integer.parseInt(Seprated[0]));
                x.setNameofCategory(Seprated[1]);
                x.setType(Seprated[2]);
                x.setPrice(Double.parseDouble(Seprated[3]));
                x.setQuantity(Integer.parseInt(Seprated[4]));
                app.add(x);
            }
            return (ArrayList<Object>)(Object)app;
        }
        if(FilePath=="Employee.txt")
        {
            ArrayList<Employee> Emp = new ArrayList<Employee>();
            Employee x = null;
            while(Reader.hasNext()){
                x = new Employee();
                String Line = Reader.nextLine();
                String Seprated[]=Line.split("`");
                //ID ` Name ` UserName ` Email` Password ` PhoneNo ` Gender` Qualification ` DateOfJoin ` DateOfBirth `
                x.setID(Integer.parseInt(Seprated[0]));
                x.setName(Seprated[1]);
                x.setUserName(Seprated[2]);
                x.setEmail(Seprated[3]);
                x.setPassword(Seprated[4]);
                x.setPhoneNo(Seprated[5]);
                x.setGender(Seprated[6]);
                x.setQualification(Seprated[7]);
                x.setDateOfJoin(Seprated[8]);
                x.setDateOfBirth(Seprated[9]);
                Emp.add(x);
                
            }
            return (ArrayList<Object>)(Object)Emp;
        }
        if(FilePath=="Customer.txt")
        {
            ArrayList<Customer> cus = new ArrayList<Customer>();
            Customer x = null;
            while(Reader.hasNext()){
                x = new Customer();
                String Line = Reader.nextLine();
                String Seprated[]=Line.split("`");
                // Name ` Email ` PhoneNo `  Price ` ItemsAndCount ` 
                x.setName(Seprated[0]);
                x.setEmail(Seprated[1]);
                x.setPhoneNo(Seprated[2]);
                x.setPrice(Double.parseDouble(Seprated[3]));
                for(int i=4,j=i+1;i<Seprated.length;i+=2,j+=2){ // lazm tb9y 25r wa7dh
                    x.setClothes(Seprated[i]);
                    x.setClothes_No(Integer.parseInt(Seprated[j]));
                }
                cus.add(x);
            }
            return (ArrayList<Object>)(Object)cus;
        }
        if(FilePath.equals("Category.txt")){
            ArrayList<Category> cat = new ArrayList<Category>();
            Category c= null;
            while(Reader.hasNext()){
                c = new Category();
                String Line = Reader.nextLine();
                String Seprated[]=Line.split("`");
                //IDCategory+"`"+this.CategoryName+"`";
                c.setIDCategory(Integer.parseInt(Seprated[0]));
                c.setCategoryName(Seprated[1]);
                cat.add(c);
            }
            return (ArrayList<Object>)(Object)cat;
        }
        return null;
    }
    
    
    
}
