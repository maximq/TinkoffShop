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
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

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
        var balance = 100;

        var user = new User();
        user.setId(1L);
        user.setPhone(userPhone);
        user.setName(userName);


        Mockito.when(userComponent.getUserByPhone(userPhone)).thenReturn(user);

        var account = accountComponent.getOrCreateAccount(user.getId());
        Mockito.when(accountComponent.getOrCreateAccount(user.getId())).thenReturn(account);
        Mockito.when(accountRepository.findByUserId(user.getId())).thenReturn(account);

        accountComponent.accountRefill(userPhone, balance);

        Mockito.verify(accountRepository, Mockito.times(2)).save(any());
    }
}
