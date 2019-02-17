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
public final class Apparel implements Serializable {
    private int StockID,Quantity ;
    private String Type ,NameofCategory;
    private double price ;
    private FileManagerBinary FManagerBianry = new FileManagerBinary();
    private final String FilePath="Apparel.bin";
    private ArrayList<Apparel> app = new ArrayList<Apparel>();
    

    public Apparel() {
    }

    public Apparel(int StockID, int Quantity, String Type,  double price ,String NameofCategory) {
        this.StockID = StockID;
        this.Quantity = Quantity;
        this.Type = Type;
        this.price = price;
        Category c = new Category();
        if(c.searchCategoryByName(NameofCategory))
             this.NameofCategory = NameofCategory;
        else
            this.NameofCategory = null;
    }

    public String getNameofCategory() {
        return this.NameofCategory;
    }

    public void setNameofCategory(String NameofCategory) {
        Category c = new Category();
        if(c.searchCategoryByName(NameofCategory))
             this.NameofCategory = NameofCategory;
        else
            this.NameofCategory = "";//kant null tmm !!
    }
    
    public int getStockID() {
        return this.StockID;
    }

    public void setStockID(int StockID) {
        this.StockID = StockID;
    }

    public int getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    public boolean addApparel()
    {
        loadFile();
        app.add(this);
        return commitToFile();
    }
    public boolean removeApparel(int ID)
    {
        if(searchApparel(ID)){
            int index = getIndexOfApparel(ID);
            app.remove(index);
            return commitToFile();
        }
        return false;
    }
    public boolean editInApparel(int ID)
    {
        if(searchApparel(ID)){
            int index = getIndexOfApparel(ID);
            app.set(index, this);
            return commitToFile();
        }
        return false;
    }
    public boolean commitToFile()
    {
        return FManagerBianry.writeBinary(FilePath, app);
    }
    public Apparel getApparel(int ID)
    {
        loadFile();
        for(Apparel ad : app)
            if(ad.StockID==ID)
                return ad;
        return null;
    }
    private int getIndexOfApparel(int ID)
    {
        for(int i=0;i<app.size();i++)
            if(app.get(i).StockID==ID)
                return i;
        return -1;
    }
    public boolean searchApparel(int ID)
    {
        loadFile();
        for(Apparel ad : app)
            if(ad.StockID==ID)
                return true;
        return false;
    }
    public void loadFile()
    {
        app=(ArrayList<Apparel>)FManagerBianry.readBinary(FilePath);
    }
//    private String getFile()
//    {
//        return this.StockID+"`"+this.NameofCategory+"`"+this.Type+"`"+this.price+"`"+this.Quantity+"`";
//    }
    public ArrayList ListAllApparel()
    {
        loadFile();
        return app;
    }
    public int CountCategoryinApparel(String name)
    {
        loadFile();
        int count=0;
        for(Apparel a:app)
            if(a.NameofCategory.equals(name))
                count++;
        return count;
    }
    public ArrayList SpecificCategory(String name) // btrg3 ay 7aga 4aylh 2sm el Catergoy elly 3awzha
    {
        loadFile();
        ArrayList<Apparel> NewApparel = new ArrayList<Apparel>() ;
        for(int i=0; i<app.size();i++)
            if(app.get(i).NameofCategory.equals(name))
                NewApparel.add(app.get(i));
        return NewApparel;
    }
    public boolean searchCaterogyInApparel(String cat)
    {
        loadFile();
        for(Apparel ad : app)
            if(ad.NameofCategory.equals(cat))
                return true;
        return false;
    }
    public boolean RemoveSpecificCategory(String name)
    {
        loadFile();
        for(int i=0 ; i<app.size();i++)
            if(app.get(i).NameofCategory.equals(name))
                app.remove(i);
        return commitToFile();
    }
    public double Calc_Total(int Stocks[], int Count_Quantity[])
    {
        loadFile();
        double S=0;
        for(int i=0;i<Stocks.length;i++)
            for(int j=0;j<app.size();j++)
                if(Stocks[i]==app.get(j).StockID){
                    S+=app.get(j).price*Count_Quantity[i];
                    break;
                }
        return S;
    }
    public double Calc_Item(int Stock, int Count_Quantity)
    {
        loadFile();
        for(Apparel a : app)
            if(a.StockID==Stock)
                return a.price*Count_Quantity;
        return 0;
    }
    public Apparel returnInfoItem(int id)
    {
        loadFile();
        for(Apparel a : app)
            if(a.StockID==id)
                return a;
        return null;
    }
    public boolean Dublicate(int ID)
    {
        loadFile();
        for(Apparel a : app)
            if(a.StockID==ID)
                return true;
        return false;
    }
    public boolean specificQuantityInc(int []arrID , int []arrCount)
    {
        loadFile();
        for(int i=0;i<arrID.length;i++){
            for(int j=0;j<app.size();j++){
                if(app.get(j).StockID==arrID[i]){
                    app.get(j).Quantity+=arrCount[i];
                    break;
                }
            }
        }
        return commitToFile();
            
    }
    public boolean specificQuantityDec(int []arrID , int []arrCount)
    {
        loadFile();
        for(int i=0;i<arrID.length;i++){
            for(int j=0;j<app.size();j++){
                if(app.get(j).StockID==arrID[i]){
                    app.get(j).Quantity-=arrCount[i];
                    break;
                }
            }
        }
        return commitToFile();
    }
    
}
