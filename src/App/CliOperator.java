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
        String user;
        do{
            user = login();
            switch (user) {
                case "admin":
                    adminFlow();
                    break;
                case "user":
                    userFlow();
                    break;
                case "exit":
                    break;
            }
        } while(user != "exit");

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
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void printProductsMenu(List<IProduct> products){
        int i = 1;
        System.out.println("Menu: ");
        System.out.println("Please select a product");
        for(IProduct product : products){
            System.out.println(i + ". " + product.getProductName() + " " + product.getProductPrice() + " cents");
            i++;
        }
    }

    Status buyProduct(){
        printProductsMenu(this.machineProvider.getMachine().getAllProducts());
        IProduct product = getProductSelectionFromUser(this.machineProvider.getMachine().getAllProducts());
        collectMoney(this.machineProvider.getMachine().getCustomerBalance(), product);
        return this.machineProvider.getMachine().purchaseProduct(product);
    }

    void printUserMainMenu(){
        System.out.println("1. Buy a Product");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    void userFlow(){
        Status status;
        int selection = 0;

        do {
            printUserMainMenu();
            selection = this.reader.nextInt();

            switch (selection) {
                case 1:
                    status = buyProduct();
                    if (!status.status) {
                        System.out.println(status.message + "\n");
                    } else System.out.println("Enjoy!!! \n");
                    break;
                case 2:
                    System.out.println("Your Total Balance is: " + machineProvider.getMachine().getCustomerBalance().getTotalBalance());
                    break;
                case 3:
                    machineProvider.getMachine().emptyCustomerBalance();
                    break;
                case 4:
                    break;
            }
        } while(selection != 4);
    }

    void adminFlow(){

    }

    private void addProduct() {

    }

    private void printAdminMenu(){
    }
}

// Implement adminFlow
// How to use the addChange?
// How to use the addProduct