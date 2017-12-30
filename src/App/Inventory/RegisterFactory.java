package App.Inventory;

import App.Coin;

import java.util.Collection;

/**
 * Created by orymamia on 30/12/2017.
 */
public class RegisterFactory {
    public Register createRegister(Collection<Coin> register) { return new Register(register); }
}
