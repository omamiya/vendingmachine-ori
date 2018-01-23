package App;

import App.Inventory.IInventory;
import App.Money.Coin;
import App.Money.IBalance;
import App.Money.UsdCoinType;
import App.Product.IProduct;

import java.util.List;
import java.util.Scanner;

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

        while(selection != "exit") {
            printMenu(this.machineProvider.getMachine().getAllProducts());
            IProduct product = getProductSelectionFromUser(this.machineProvider.getMachine().getAllProducts());
            collectMoney(this.machineProvider.getMachine().getCustomerBalance());
            this.machineProvider.getMachine().purchaseProduct(product);
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

    void collectMoney(IBalance customerBalance){
        int selection = 0;
        Coin[] coins;
        String[] coinTypesNames = {"Penny", "Nickel", "Dime", "Quarter", "Dollar", "SUBMIT"};
        System.out.println("Please insert money and submit after complete");
        for (int i = 0 ; i < coinTypesNames.length ; i++){
            System.out.println(i + ". " + coinTypesNames[i]);
        }
        while(selection != 5){
            selection = this.reader.nextInt();
            if(selection != 5){
                UsdCoinType usdCoinType = UsdCoinType.valueOf(coinTypesNames[selection].toUpperCase());
                customerBalance.addCoin(usdCoinType);
                System.out.println("Total: " + customerBalance.getTotalBalance());
            }
        }
        System.out.println("Thank You");

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
