package App;

import App.Inventory.IInventory;
import App.Money.Coin;
import App.Money.IBalance;
import App.Product.IProduct;

import java.util.List;

/**
 * Created by orymamia on 30/12/2017.
 */
public interface IMachine {
    IBalance getCustomerBalance();
    Status addMoney(IBalance payment);
    Status emptyCustomerBalance();
    List<IProduct> getAllProducts();
    Status purchaseProduct(IProduct product);
}