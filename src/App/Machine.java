package App;

import App.Inventory.IInventory;
import App.Inventory.InventoryFactory;
import App.Money.*;
import App.Product.IProduct;
import App.Product.ProductFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Machine implements IMachine, IMachineAdmin{
    IBalance customerBalance;
    IBalance machineBalance;
    IInventory inventory;
    ChangeCalculator changeCalculator = new ChangeCalculator();


    public Machine(){
        BalanceFactory bf = new BalanceFactory();
        this.customerBalance = bf.createCustomerBalance();
        this.machineBalance = createDefaultBalance();
        this.inventory = initInventory();
    }

    private IInventory initInventory(){
        ProductFactory productFactory = new ProductFactory();
        IProduct cola = productFactory.createProduct("Coca Cola", 121);
        IProduct sprite = productFactory.createProduct("Sprite", 125);
        IProduct fanta = productFactory.createProduct("Fanta", 129);
        IProduct[] products = {cola, sprite, fanta};
        InventoryFactory inventoryFactory = new InventoryFactory();
        return inventoryFactory.createInventory(products);
    }

    private IBalance createDefaultBalance(){
        BalanceFactory bf = new BalanceFactory();
        HashMap<UsdCoinType, Integer> defaultBalance = new HashMap<>();

        defaultBalance.put(UsdCoinType.PENNY, 1);
        defaultBalance.put(UsdCoinType.NICKEL, 1);
        defaultBalance.put(UsdCoinType.DIME, 1);
        defaultBalance.put(UsdCoinType.QUARTER, 1);
        defaultBalance.put(UsdCoinType.DOLLAR, 1);

        return bf.createCustomBalance(defaultBalance);
    }

    @Override
    public IBalance getMachineBalance() { return this.machineBalance; }

    @Override
    public Integer getProductAmount(IProduct product) {
        return this.inventory.getProductAmount(product);
    }

    public IBalance getCustomerBalance(){ return this.customerBalance; }

    @Override
    public Status addMoney(IBalance payment) {
        this.machineBalance.addBalance(payment);
        return new Status("Ok", true);
    }

    @Override
    public Status emptyCustomerBalance() {
        this.customerBalance.emptyBalance();
        return new Status("Ok", true);
    }

    @Override
    public List<IProduct> getAllProducts() {
        return this.inventory.getProductsList();
    }

    @Override
    public Status purchaseProduct(IProduct product) {
        Status status = verifyPurchase(product);
        String noChangeMsg = "Not enough change";
        IBalance change;
        if(status.status){
            try {
                change = calculateChange(product);
            } catch (NoChangeException e) {
                return new Status(noChangeMsg, false);
            }
            inventory.updateProductAmount(product, -1);
            machineBalance.reduceBalance(change);
            addMoney(this.customerBalance);
            this.customerBalance = change;
            return new Status("Ok", true);
        }
        return status;
    }

    Status verifyPurchase(IProduct product) {
        String message = "Ok";
        boolean status = true;

        Integer productPrice = product.getProductPrice();
        boolean enoughPayment = changeCalculator.isPaymentMissing(productPrice, customerBalance.getTotalBalance());
        boolean productAvailable = this.inventory.isProductAvailable(product);

        if (!enoughPayment){
            message = "Payment is missing";
            status = false;
        }else if(!productAvailable){
            message = "Product is not available";
            status = false;
        }
        return new Status(message, status);
    }

    IBalance calculateChange(IProduct product) throws NoChangeException {
        return new ChangeCalculator().calculateChange(product, this.customerBalance, this.machineBalance);
    }

    @Override
    public Status addProduct(IProduct product, Integer amount) {
        return this.inventory.addProductToInventory(product, amount);
    }
}
