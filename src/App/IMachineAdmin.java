package App;

import App.Money.Balance;
import App.Money.IBalance;
import App.Product.IProduct;

/**
 * Created by orymamia on 21/01/2018.
 */
public interface IMachineAdmin {

    Status addProducts(IProduct products, Integer amount);
    Status addChange(IBalance change);
    IBalance getMachineBalance();
}
