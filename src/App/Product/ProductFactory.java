package App.Product;

/**
 * Created by orymamia on 19/12/2017.
 */
public class ProductFactory {
    public IProduct createProduct(String name, double price) {
        return new Product(name, price);
    }
}
