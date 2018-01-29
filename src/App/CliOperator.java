package App;

import App.Money.IBalance;
import App.Money.UsdCoinType;
import App.Product.IProduct;
import App.Product.ProductFactory;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        selection = options[this.reader.nextInt() - 1];
        return selection;

    }

    IProduct getProductSelectionFromUser(List<IProduct> products){
        System.out.println("Enter a number: ");
        int selection = this.reader.nextInt();
        IProduct product = products.get(selection - 1);
        System.out.println("Your selection is: " + product.getProductName() + ". Please insert " + product.getProductPrice() + "cents");
        return product;
    }

    void collectMoney(IProduct product){
        IBalance customerBalance = machineProvider.getMachine().getCustomerBalance();
        String input;
        System.out.println("Please choose one of the coins: \n");
        for(UsdCoinType coinType : UsdCoinType.values()){
            System.out.println(coinType.name() + " - " + coinType.getValue());
        }
        System.out.println("Total: " + customerBalance.getTotalBalance());
        while(customerBalance.getTotalBalance() < product.getProductPrice()){
            input = this.reader.next();
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
        collectMoney(product);
        return this.machineProvider.getMachine().purchaseProduct(product);
    }

    void printUserMainMenu(){
        System.out.println("1. Buy a Product");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Logout");
    }

    void printAdminMainMenu(){
            System.out.println("1. Reload Products");
            System.out.println("2. Reload Change");
            System.out.println("3. Check Machine Balance");
            System.out.println("4. Check Machine Inventory");
            System.out.println("5. Logout");
    }

    void userFlow(){
        Status status;
        int selection;

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
        int selection;
        do {
            printAdminMainMenu();
            selection = this.reader.nextInt();

            switch (selection) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    addCoin();
                    break;
                case 3:
                    printBalance(machineProvider.getMachineAdmin().getMachineBalance());
                    break;
                case 4:
                    printInventory();
                    break;
                case 5:
                    break;
            }
        } while(selection != 5);
    }

    private void addProduct() {
        String name;
        Integer price;
        Integer amount;
        System.out.println("please enter product name: ");
        this.reader.nextLine();
        name = this.reader.nextLine();
        System.out.println("please enter product price: ");
        price = this.reader.nextInt();
        System.out.println("Please enter amount: ");
        amount = this.reader.nextInt();
        System.out.println(machineProvider.getMachineAdmin().addProduct(createProduct(name, price), amount).message);
        System.out.println("------------------------");
    }

    private IProduct createProduct(String name, Integer price){
        ProductFactory pf = new ProductFactory();
        return pf.createProduct(name, price);
    }

    private void addCoin(){
        Integer amount;
        String input;
        System.out.println("Please choose one of the coins: \n");
        for(UsdCoinType coinType : UsdCoinType.values()){
            System.out.println(coinType.name() + " - " + coinType.getValue());
        }
        input = this.reader.next();
        UsdCoinType coinType = UsdCoinType.valueOf(input.toUpperCase());
        System.out.println("Please enter amount: ");
        amount = this.reader.nextInt();
        machineProvider.getMachineAdmin().getMachineBalance().updateCoinTypeAmount(coinType, amount);
    }

    private void printBalance(IBalance balance){
        for (UsdCoinType coinType : UsdCoinType.values()) {
            System.out.println(coinType.name() + " - " + balance.getAmountOfCoinType(coinType));
        }
    }

    private void printInventory(){
        List<IProduct> productList = machineProvider.getMachine().getAllProducts();
        System.out.println("Inventory: ");
        for (IProduct product : productList) {
            System.out.println(product.getProductName() + " - " + machineProvider.getMachineAdmin().getProductAmount(product));
        }
        System.out.println("--------------------");
    }
}