import org.example.components.AccountComponent;
import org.example.components.UserComponent;
import org.example.entity.User;
import org.example.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class AccountComponentUnitTest extends AbstractTest{

    @Mock
    UserComponent userComponent;

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
