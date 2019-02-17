

package fashion.store.management.system;

import GUI.Login;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;



public class FashionStoreManagementSystem {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        File currentDir = new File(projectPath); 				
	checkDirectoryContents(currentDir);
        
        

        new Login().setVisible(true);
        
        
        
    }
    public static void checkDirectoryContents(File dir)
    {
        File files []= dir.listFiles();
        boolean EmpFile = true;
        boolean AdminFile = true;
        boolean CategoryFile = true;
        boolean ApparelFile = true;
        boolean CustomerFile = true;
        for(File file : files){
            if(file.getName().equals("Employee.bin"))
                EmpFile = false;
            if(file.getName().equals("Admin.bin"))
                AdminFile = false;
            if(file.getName().equals("Customer.bin"))
                CustomerFile = false;
            if(file.getName().equals("Category.bin"))
                CategoryFile = false;
            if(file.getName().equals("Apparel.bin"))
                ApparelFile = false;
        }
        if(EmpFile)
            new Employee().commitToFile();
        if(AdminFile)
            new Admin().commitToFile();
        if(CustomerFile)
            new Customer().commitToFile();
        if(ApparelFile)
            new Apparel().commitToFile();
        if(CategoryFile)
            new Category().commitToFile();
    }
    
}
