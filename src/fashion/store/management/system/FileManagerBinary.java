/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fashion.store.management.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public final class FileManagerBinary implements Serializable{
    
    public boolean writeBinary(String FilePath , Object data)
    {
        try{
            System.out.print("\nwritting in ! " + FilePath);
            ObjectOutputStream writter = new ObjectOutputStream(new FileOutputStream(FilePath));
            writter.writeObject(data);
            System.out.println(" ... Done ! ");
            writter.close();
            return true;
        }catch(Exception e){
            System.out.println("Error in Writting : "+e);
        }
        return false;
    }
    public Object readBinary(String FilePath)
    {
        Object data = null;
        try {
            System.out.println("Reading ! From " + FilePath);
            ObjectInputStream Reader = new ObjectInputStream(new FileInputStream(FilePath));
            data= Reader.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("Error in Reading by Youssef : "+ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManagerBinary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileManagerBinary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
}
