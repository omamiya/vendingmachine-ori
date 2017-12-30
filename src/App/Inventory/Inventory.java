package App.Inventory;

import App.Product.IProduct;
import App.Status;

import java.util.*;

 class Inventory implements IInventory{
    private Map<String, InventoryProduct> inventory = new HashMap<>();

    Inventory(IProduct[] products) {
        for (IProduct product : products) {
            InventoryProduct inventoryProduct = new InventoryProduct(product, 10);
            inventory.put(product.getProductName(), inventoryProduct);
        }
    }

    @Override
    public Collection<String> getProductNamesList() {
        return inventory.keySet();
    }

    @Override
    public IProduct getProductByName(String productName) {
        return inventory.get(productName).product;
    }

    @Override
    public Status addProductToInventory(IProduct product, Integer amount) {
        String productName ;
        productName = product.getProductName();
        if (this.inventory.containsKey(productName)){
            InventoryProduct ip = this.inventory.get(productName);
            ip.amount += amount;
        }
        else {
            InventoryProduct ip = new InventoryProduct(product, amount);
            this.inventory.put(product.getProductName(), ip);
        }
        return new Status("Ok");
    }

     @Override
     public Status updateProductAmount(IProduct product, Integer amount) {
         String productName = product.getProductName();
         InventoryProduct ip = this.inventory.get(productName);
         ip.amount += amount;
         return new Status("Ok");
     }

     @Override
     public Boolean isProductAvailable(IProduct product) {
        return this.inventory.get(product.getProductName()).amount > 0;
    }

 }

// Inventory and InventoryFactory are the public classes.
// InventoryProduct is an inner class.
// I should create methods to insert and remove products from/ to the Inventory

// the get product price should be within he Product class



