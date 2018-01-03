package App;

import App.Inventory.IInventory;
import App.Money.Balance;
import App.Money.Coin;
import App.Money.IBalance;
import App.Product.IProduct;

/**
 * Created by orymamia on 30/12/2017.
 */
public interface IMachine {
    Balance getMachineBalance();
    Balance getCustomerBalance();
    Status insertCoin(Coin coin, IBalance customerBalance);
    Status verifyPurchase(IProduct product, IBalance customerBalance, IBalance machineBalance, IInventory inventory);

}
