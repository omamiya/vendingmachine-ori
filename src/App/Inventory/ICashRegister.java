package App.Inventory;

import App.Money.Coin;
import App.Status;

/**
 * Created by orymamia on 30/12/2017.
 */
public interface ICashRegister {
    Status insertCoin(Coin coin);

}
