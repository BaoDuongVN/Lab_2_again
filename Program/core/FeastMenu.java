package Program.core;

public class FeastMenu {
    private String menuCode, menuName, menuIngredients;
    private int menuPrice;

    public FeastMenu(String menuCode, String menuName, int menuPrice, String menuIngredients) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuIngredients = menuIngredients;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIngredients() {
        return menuIngredients;
    }

    public void setMenuIngredients(String menuIngredients) {
        this.menuIngredients = menuIngredients;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    @Override
    public String toString() {
        menuIngredients = menuIngredients.replaceAll("#", "\n").replaceAll("\"", "");
        return this.menuCode + ", " + this.menuName + ", " + this.menuPrice + menuIngredients;
    }

}
