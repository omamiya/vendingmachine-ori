package App.Inventory;


import App.Product.IProduct;

/**
 * Created by orymamia on 24/12/2017.
 */
public class InventoryFactory {
    public Inventory createInventory(IProduct[] products ) {
        return new Inventory(products);
    }
}
