package App.Inventory;

import App.Product.IProduct;
import App.Status;

import java.util.List;

/**
 * Created by orymamia on 24/12/2017.
 */
public interface IInventory {
    List<IProduct> getProductsList();

    IProduct getProductByName(String productName);

    Status addProductToInventory(IProduct product, Integer amount);

    Status updateProductAmount(IProduct product, Integer amount);

    Boolean isProductAvailable(IProduct product);

}