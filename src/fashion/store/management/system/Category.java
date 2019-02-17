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
public final class Category  implements Serializable{
    private String CategoryName;
    private int IDCategory;
    private ArrayList<Category> cats = new ArrayList<Category>();
    private FileManagerBinary FManagerBinary = new FileManagerBinary();
    
    private final String FilePath = "Category.bin";
    
    public Category() {
    }

    public Category(String CategoryName,int ID) {
        this.CategoryName = CategoryName;
        this.IDCategory= ID;
    }

    public int getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(int IDCategory) {
        this.IDCategory = IDCategory;
    }
    

    public String getCategoryName() {
        return this.CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    
    public boolean addCategory()
    {
        loadFile();
        cats.add(this);
        return commitToFile();
    }
    public boolean removeCategory(int ID)
    {
        if(searchCategory(ID)){
            int index = getIndexOfCategory(ID);
            cats.remove(index);
            return commitToFile();
        }
        return false;
    }
    public boolean removeCategoryAndClothes(int ID)
    {
        if(searchCategory(ID)){
            int index = getIndexOfCategory(ID);
            cats.remove(index);
            new Apparel().RemoveSpecificCategory(this.CategoryName);
            return commitToFile();
        }
        return false;
    }
    public boolean editInCategory(int ID)
    {
        if(searchCategory(ID)){
            int index = getIndexOfCategory(ID);
            cats.set(index, this);
            return commitToFile();
        }
        return false;
    }
    public boolean commitToFile()
    {
        return FManagerBinary.writeBinary(FilePath, cats);
    }
    public Category getCategory(int ID)
    {
        loadFile();
        for(Category ad : cats)
            if(ad.IDCategory==ID)
                return ad;
        return null;
    }
    private int getIndexOfCategory(int ID)
    {
        for(int i=0;i<cats.size();i++)
            if(cats.get(i).IDCategory==ID)
                return i;
        return -1;
    }
    public boolean searchCategory(int ID)
    {
        loadFile();
        for(Category ad : cats)
            if(ad.IDCategory==ID)
                return true;
        return false;
    }
    public void loadFile()
    {
        cats=(ArrayList<Category>)FManagerBinary.readBinary(FilePath);
    }
//    private String getFile()
//    {
//        return this.IDCategory+"`"+this.CategoryName+"`";
//    }
    public ArrayList ListAllCategory()
    {
        loadFile();
        return cats;
    }
    
    
     
    public ArrayList<String> CurrentlyCategory()  //bst5dmha 34an t9oly ell Catergoy el 7alyh ayh !! 34an a7th fy El Combo box
    {
        ArrayList<String> str = new ArrayList<String>();
        int k=0;
        boolean act =false;
        loadFile();
        for(int i=0;i<cats.size();i++){
            act = false;
            for(int j=0;j<i;j++){
                if(cats.get(j).CategoryName.equals(cats.get(i).CategoryName))
                {
                    act = true;
                    break;
                }
            }
            if(!act)
                str.add(cats.get(i).CategoryName);
                //str[k++]=cats.get(i).CategoryName;
        }
        return str;
    }
    public boolean searchCategoryByName(String Name)
    {
        loadFile();
        for(Category ad : cats)
            if(ad.CategoryName.equals(Name))
                return true;
        return false;
    }
    public boolean DublicateID(int ID)
    {
        loadFile();
        for(Category ad : cats)
            if(ad.IDCategory==ID)
                return true;
        return false;
    }
    public boolean DublicateName(String Name)
    {
        loadFile();
        for(Category ad : cats)
            if(ad.CategoryName.equals(Name))
                return true;
        return false;
    }
}
