package App.UserAction;

import App.Machine;
import App.MachineProvider;
import App.Money.IBalance;
import App.Money.UsdCoinType;
import App.Product.IProduct;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by orymamia on 30/01/2018.
 */
public class BuyProduct implements UserAction {
    String description = UserActionTypes.BUY_PRODUCT.getValue() + ". Buy a Product";
    MachineProvider machineProvider;
    Scanner reader;

    public BuyProduct(MachineProvider machineProvider, Scanner reader){
        this.machineProvider = machineProvider;
        this.reader = reader;
    }

    @Override
    public void invoke() {
        printProductsMenu(this.machineProvider.getMachine().getAllProducts());
        IProduct product = getProductSelectionFromUser(this.machineProvider.getMachine().getAllProducts());
        collectMoney(product);
        System.out.println(this.machineProvider.getMachine().purchaseProduct(product).message);
        System.out.println("------------------------");
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }

    private void printProductsMenu(List<IProduct> products){
        int i = 1;
        System.out.println("Menu: ");
        System.out.println("Please select a product");
        for(IProduct product : products){
            System.out.println(i + ". " + product.getProductName() + " " + product.getProductPrice() + " cents");
            i++;
        }
    }

    private void collectMoney(IProduct product){
        IBalance customerBalance = this.machineProvider.getMachine().getCustomerBalance();
        String input;
        if(customerBalance.getTotalBalance() < product.getProductPrice()){
            System.out.println("Please choose one of the coins: \n");
            for(UsdCoinType coinType : UsdCoinType.values()){
                System.out.println(coinType.name() + " - " + coinType.getValue());
            }
            System.out.println("Total: " + customerBalance.getTotalBalance());
        }
        while(customerBalance.getTotalBalance() < product.getProductPrice()){
            input = this.reader.nextLine();
            customerBalance.updateCoinTypeAmount(UsdCoinType.valueOf(input.toUpperCase()), 1);
            System.out.println("Total: " + customerBalance.getTotalBalance());
        }
        System.out.println("Processing....");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private IProduct getProductSelectionFromUser(List<IProduct> products){
        System.out.println("Enter a number: ");
        Integer selection = Integer.parseInt(this.reader.nextLine());
        IProduct product = products.get(selection - 1);
        System.out.println("Your selection is: " + product.getProductName() + ". Please insert " + product.getProductPrice() + "cents");
        return product;
    }
}
