package App.AdminAction;

import App.MachineProvider;
import App.Money.UsdCoinType;

import java.util.Scanner;

/**
 * Created by orymamia on 30/01/2018.
 */
public class AddCoin implements AdminAction{
    private String description = AdminActionTypes.ADD_COIN.getValue() + ". Reload Change";
    private MachineProvider machineProvider;
    private Scanner reader;

    public AddCoin(MachineProvider machineProvider, Scanner reader){
        this.machineProvider = machineProvider;
        this.reader = reader;
    }

    @Override
    public void invoke() {
        Integer amount;
        String input;
        System.out.println("Please choose one of the coins: \n");
        for(UsdCoinType coinType : UsdCoinType.values()){
            System.out.println(coinType.name() + " - " + coinType.getValue());
        }
        input = this.reader.nextLine();
        UsdCoinType coinType = UsdCoinType.valueOf(input.toUpperCase());
        System.out.println("Please enter amount: ");
        amount = Integer.parseInt(this.reader.nextLine());
        machineProvider.getMachineAdmin().getMachineBalance().updateCoinTypeAmount(coinType, amount);
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}