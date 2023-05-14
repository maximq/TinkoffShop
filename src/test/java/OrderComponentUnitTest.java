import org.example.components.AccountComponent;
import org.example.components.OrderComponent;
import org.example.components.ProductComponent;
import org.example.components.UserComponent;
import org.example.entity.Account;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.enums.ProductType;
import org.example.repositories.AccountRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class OrderComponentUnitTest extends AbstractTest{
    @Mock
    UserRepository userRepository;

    @Mock
    UserComponent userComponent;

    @Mock
    ProductComponent productComponent;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    AccountComponent accountComponent;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    OrderComponent orderComponent;

    @Test
    void createOrderUnitTest() {
        //PRECONDITION
        var userName = "Oleg";
        var userPhone = "+79990001234";
        var productName = "Milk";
        var price = 100;

        var user = new User();
        user.setPhone(userPhone);
        user.setName(userName);

        var product = new Product();
        product.setId(1L);
        product.setName(productName);
        product.setPrice(price);
        product.setProductType(ProductType.GOOD);
        product.setRemainder(1);

        var account = new Account();
        account.setId(1L);
        account.setUserId(user.getId());
        account.setBalance(100);

        Mockito.when(userComponent.getOrCreateUser(userName, userPhone)).thenReturn(user);
        Mockito.when(productComponent.getProductByName(productName)).thenReturn(product);
        Mockito.when(accountComponent.getOrCreateAccount(user.getId())).thenReturn(account);

        orderComponent.createOrder(userName, userPhone, productName);

        Mockito.verify(orderRepository, Mockito.times(1)).save(any());
        Mockito.verify(accountRepository, Mockito.times(1)).save(any());
    }
}
