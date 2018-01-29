package App;

import App.Money.IBalance;
import App.Product.IProduct;

public interface IMachineAdmin {

    Status addProduct(IProduct products, Integer amount);
    IBalance getMachineBalance();
    Integer getProductAmount(IProduct product);

}
