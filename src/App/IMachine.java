package App;

import App.Money.Balance;
import App.Money.ICoin;
import App.Product.IProduct;

/**
 * Created by orymamia on 30/12/2017.
 */
public interface IMachine {
    Balance getMachineBalance();
    Balance getCustomerBalance();
    Status insertCoin(ICoin coin);
    Status selectProduct(Balance customerBalance, Balance machineBalance, IProduct product);
}
