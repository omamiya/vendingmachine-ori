package App;

import App.Inventory.IInventory;
import App.Inventory.InventoryFactory;
import App.Product.IProduct;
import App.Product.ProductFactory;

import static jdk.nashorn.internal.objects.Global.println;

public class Main {
    public static void main(String[] args) {

        ProductFactory productFactory = new ProductFactory();
        IProduct cola = productFactory.createProduct("Coca Cola", 3.0);
        IProduct sprite = productFactory.createProduct("Sprite", 3.5);
        String name = cola.getProductName();
        double price = cola.getProductPrice();
        System.out.println("This is the product name: " + name);
        System.out.println("This is the product price: " + price);

        IProduct[] products = {cola};
        InventoryFactory inventoryFactory = new InventoryFactory();
        IInventory drinksInventory =  inventoryFactory.createInventory(products);
        drinksInventory.addProductToInventory(cola,4);
        drinksInventory.addProductToInventory(sprite,0);
        System.out.println(drinksInventory.isProductAvailable(cola).toString());
        System.out.println(drinksInventory.isProductAvailable(sprite).toString());
    }
}
