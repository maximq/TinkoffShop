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

public class AccountComponentUnitTest extends AbstractTest{
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
    AccountRepository accountRepository;

    @InjectMocks
    AccountComponent accountComponent;

    @Test
    void createOrderUnitTest() {
        //PRECONDITION
        var userName = "Oleg";
        var userPhone = "+79990001234";

        var user = new User();
        user.setPhone(userPhone);
        user.setName(userName);


        Mockito.when(userComponent.getOrCreateUser(userName, userPhone)).thenReturn(user);

        accountComponent.getOrCreateAccount(user.getId());
        accountComponent.accountRefill(user.getId(), 100);

        Mockito.verify(accountRepository, Mockito.times(3)).save(any());
    }
}
