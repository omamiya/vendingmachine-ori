package App;

import App.Money.IBalance;
import App.Product.IProduct;

/**
 * Created by orymamia on 21/01/2018.
 */
public interface IMachineAdmin {

    Status addProducts(IProduct[] products);
    Status addChange(IBalance change);
}
