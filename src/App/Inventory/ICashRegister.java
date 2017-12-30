package App.Inventory;

import App.Coin;
import App.Status;

import java.util.Collection;

/**
 * Created by orymamia on 30/12/2017.
 */
public interface ICashRegister {
    Status insertCoin(Coin coin);

}
