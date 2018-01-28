package App;

import App.Inventory.IInventory;
import App.Money.Coin;
import App.Money.IBalance;
import App.Money.UsdCoinType;
import App.Product.IProduct;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by orymamia on 15/01/2018.
 */
public class CliOperator {
    private MachineProvider machineProvider;
    private Scanner reader = new Scanner(System.in);

    CliOperator(MachineProvider machineProvider){
        this.machineProvider = machineProvider;
    }

    void runMachine() {
        String selection = login();
        Status status;

        while(!selection.equals("exit")) {
            printMenu(this.machineProvider.getMachine().getAllProducts());
            IProduct product = getProductSelectionFromUser(this.machineProvider.getMachine().getAllProducts());
            collectMoney(this.machineProvider.getMachine().getCustomerBalance(), product);
            this.machineProvider.getMachine().purchaseProduct(product);
            System.out.println("your change is: " + this.machineProvider.getMachine().getCustomerBalance().getTotalBalance());
            System.out.println("buy more?");
            selection = this.reader.next();
        }
        this.reader.close();
    }

    String login(){
        String[] options = {"admin", "user", "exit"};
        String selection;
        System.out.println("Please login:" + "\n" + "1. Admin" + "\n" + "2. User" + "\n" + "3. Exit");
//        Scanner reader = new Scanner(System.in);
        selection = options[this.reader.nextInt() - 1];
//        reader.close();
        return selection;

    }

    IProduct getProductSelectionFromUser(List<IProduct> products){
        System.out.println("Enter a number: ");
        int selection = this.reader.nextInt();
        IProduct product = products.get(selection - 1);
        System.out.println("Your selection is: " + product.getProductName() + ". Please insert " + product.getProductPrice() + "cents");
        return product;
    }

    void collectMoney(IBalance customerBalance, IProduct product){
        String input;
        System.out.println("Please one of the coins: \n");
        for(UsdCoinType coinType : UsdCoinType.values()){
            System.out.println(coinType.name());
        }
        System.out.println("Total: " + customerBalance.getTotalBalance());
        while(customerBalance.getTotalBalance() < product.getProductPrice()){
            input = this.reader.next();
            customerBalance.addCoin(UsdCoinType.valueOf(input.toUpperCase()));
            System.out.println("Total: " + customerBalance.getTotalBalance());
        }
        System.out.println("Processing....");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void printMenu(List<IProduct> products){
        int i = 1;
        System.out.println("Menu: ");
        System.out.println("Please select a product");
        for(IProduct product : products){
            System.out.println(i + ". " + product.getProductName() + " " + product.getProductPrice() + " cents");
            i++;
        }

    }

    private void addProduct() {

    }

    private void printAdminMenu(){
    }
}

//How to print the menu according to the user type? Admin / Customer
