package App.Inventory;

import App.Product.IProduct;
import App.Status;

import java.util.Collection;

/**
 * Created by orymamia on 24/12/2017.
 */
public interface IInventory {
    Collection<String> getProductNamesList();

    IProduct getProductByName(String productName);

    Status addProductToInventory(IProduct product, Integer amount);

    Status updateProductAmount(IProduct product, Integer amount);

    Boolean isProductAvailable(IProduct product);


}