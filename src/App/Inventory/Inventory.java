package App.Inventory;

import App.Product.IProduct;
import App.Product.Product;
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
    public List<IProduct> getProductsList() {
        List<IProduct> products = new ArrayList<>();
        for(InventoryProduct ip : this.inventory.values()){
          products.add(ip.product);
        }
        return products;
    }

    @Override
    public IProduct getProductByName(String productName) {
        return inventory.get(productName).product;
    }

    @Override
    public Status addProductToInventory(IProduct product, Integer amount) {
        String productName ;
        productName = product.getProductName();
        if (this.inventory.containsKey(productName)){ return new Status("The product is already exist", false); }
        else {
            InventoryProduct ip = new InventoryProduct(product, amount);
            this.inventory.put(productName, ip);
            return new Status(productName + "has been added successfully", true);
        }
    }

     @Override
     public Status updateProductAmount(IProduct product, Integer amount) {

        String productName = product.getProductName();
        InventoryProduct ip = this.inventory.get(productName);
        if(ip.amount + amount < 0){
            return new Status("error", false);
        }
        ip.amount += amount;
        return new Status("Ok", true);
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



