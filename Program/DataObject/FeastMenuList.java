package Program.DataObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Program.core.FeastMenu;

public class FeastMenuList extends ArrayList<FeastMenu> {

    public void loadFromFile(String fName) {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            bf.readLine();
            String line;
            while ((line = bf.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String menuCode = stk.nextToken().trim();
                    String menuName = stk.nextToken().trim();
                    int menuPrice = Integer.parseInt(stk.nextToken().trim());
                    String menuIngredients = stk.nextToken().trim();
                    FeastMenu fMenu = new FeastMenu(menuCode, menuName, menuPrice, menuIngredients);
                    this.add(fMenu);
                }

            }
            fr.close();
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void display() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("List of Set Menus for ordering party");
        System.out.println("---------------------------------------------------------------");
        for (FeastMenu fMenu : this) {
            System.out.format("%-15s :%-15s\n", "Code", fMenu.getMenuCode());
            System.out.format("%-15s :%-15s\n", "Name", fMenu.getMenuName());
            System.out.format("%-15s : %-15s\n", "Price", fMenu.getMenuPrice() + " Vnd");
            System.out.format("%-15s\n", "Ingredients");
            System.out.println(fMenu.getMenuIngredients().replaceAll("#", "\n").replaceAll("\"", ""));
            System.out.println("---------------------------------------------------------------");
        }
    }

    public FeastMenuList getMenu() {
        return this;
    }

    public FeastMenu findMenuByCode(String menuCode) {
        for (FeastMenu menu : this) {
            if (menu.getMenuCode().equalsIgnoreCase(menuCode)) {
                return menu;
            }
        }
        return null;
    }

}
