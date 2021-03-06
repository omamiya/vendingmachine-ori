package App.Money;

import App.Product.IProduct;
import App.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by orymamia on 30/12/2017.
 */
public interface IBalance {
    Integer getTotalBalance();
    Integer getAmountOfCoinType(UsdCoinType usdCoinType);
    void addBalance(IBalance balanceToAdd);
    void reduceBalance(IBalance balanceToReduce);
    void updateCoinTypeAmount(UsdCoinType usdCoinType, Integer dif);
    void emptyBalance();
}

//select product -> insert coins :
//1. ok
//      a. calculate change
//      b. insert coins to machine balance
//      c. reduce product from inventory



//2. error:
//      a. payment missing
//      b. no product
//      c. no change available