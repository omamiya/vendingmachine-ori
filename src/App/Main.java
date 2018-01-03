package App;

import App.Inventory.IInventory;
import App.Inventory.InventoryFactory;
import App.Money.Coin;
import App.Money.IBalance;
import App.Money.UsdCoinType;
import App.Product.IProduct;
import App.Product.ProductFactory;

public class Main {
    public static void main(String[] args) {

        ProductFactory productFactory = new ProductFactory();
        IProduct cola = productFactory.createProduct("Coca Cola", 30);
        IProduct sprite = productFactory.createProduct("Sprite", 35);
        String name = cola.getProductName();
        Integer price = cola.getProductPrice();
        System.out.println("This is the product name: " + name);
        System.out.println("This is the product price: " + price);

        IProduct[] products = {cola};
        InventoryFactory inventoryFactory = new InventoryFactory();
        IInventory drinksInventory =  inventoryFactory.createInventory(products);
        drinksInventory.addProductToInventory(cola,4);
        drinksInventory.addProductToInventory(sprite,0);
        System.out.println(drinksInventory.isProductAvailable(cola).toString());
        System.out.println(drinksInventory.isProductAvailable(sprite).toString());
        System.out.println(drinksInventory.updateProductAmount(sprite, -1).message);
        drinksInventory.getProductNamesList();

        IMachine drinksMachine = new Machine();
        System.out.println(drinksMachine.getMachineBalance().getTotalBalance());
        System.out.println(drinksMachine.getCustomerBalance().getTotalBalance());
        Coin penny = new Coin(UsdCoinType.PENNY);
        IBalance customerBalance = drinksMachine.getCustomerBalance();
        drinksMachine.insertCoin(penny, customerBalance);




    }
}
