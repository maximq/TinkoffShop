import org.example.components.AccountComponent;
import org.example.components.OrderComponent;
import org.example.components.ProductComponent;
import org.example.components.UserComponent;
import org.example.repositories.AccountRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderComponentTest extends AbstractTest {
    @Autowired
    UserComponent userComponent;

    @Autowired
    ProductComponent productComponent;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderComponent orderComponent;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountComponent accountComponent;

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "Oleg, +7999", "Petya, +7888"
        }
    )
    void createOrderTest(String userName, String userPhone) {
        //PRECONDITION
        //var userName = "Oleg";
        //var userPhone = "+79990001234";
        var productName = "Milk";
        var price = 100;

        var product = productComponent.addNewGoods(productName, price);
        var account = accountComponent.getOrCreateAccount(userComponent.getOrCreateUser(userName, userPhone).getId());
        var startBalance = account.getBalance();

        //TEST
        var createdOrder = orderComponent.createOrder(userName, userPhone, productName);

        //ASSERTS
        var order = orderRepository.findById(createdOrder.getId()).get();

        var authorId = order.getAuthorId();
        var productId = order.getProductId();

        var result = accountComponent.getOrCreateAccount(userComponent.getOrCreateUser(userName, userPhone).getId()).getBalance();

        var user = userRepository.findById(authorId);
        assertThat(user).isNotEmpty();
        assertThat(user.get().getPhone()).isNotEmpty();
        assertThat(user.get().getName()).isNotEmpty();
        assertThat(product.getId()).isEqualTo(productId);
        assertThat(startBalance).isEqualTo(startBalance+result);
    }

    @Test
    void errorWhenTryToCreatedWithoutProductTest() {
        var productName = "Beer";
        var error = assertThrows(NoSuchElementException.class,
                () -> orderComponent.createOrder("maxim","12345",productName)
        );

        assertThat(error.getMessage()).isEqualTo("Продукта с именем '%s' нет!", productName);
    }

    @Test
    void errorWhenTryToCreatedWithoutEnoughBalance() {
        //PRECONDITION
        var userName = "maxim";
        var userPhone = "12345";
        var productName = "Milk";
        var price = 100;

        var product = productComponent.addNewGoods(productName, price);
        var startRemainder = product.getRemainder();

        var account = accountComponent.getOrCreateAccount(
                userComponent.getOrCreateUser(userName, userPhone).getId()
        );
        account.setBalance(price-1);
        accountRepository.save(account);

        //TEST
        var error = assertThrows(UnsupportedOperationException.class,
                () -> orderComponent.createOrder(userName,userPhone,productName)
        );

        //ASSERTS

        assertThat(error.getMessage()).isEqualTo(
                String.format(
                        "Недостаточно средств на аккаунте '%s', текущий баланс '%s'",
                        account.getUserId(), account.getBalance()
                )
        );
        assertThat(product.getRemainder()).isEqualTo(startRemainder);
    }
}
