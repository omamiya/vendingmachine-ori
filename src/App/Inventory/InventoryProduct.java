package App.Inventory;

import App.Product.IProduct;

class InventoryProduct {
    InventoryProduct(IProduct product, Integer amount) {
        this.amount = amount;
        this.product = product;
    }
    IProduct product;
    Integer amount;
}
