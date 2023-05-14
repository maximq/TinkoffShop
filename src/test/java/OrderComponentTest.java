import org.example.components.OrderComponent;
import org.example.components.ProductComponent;
import org.example.components.UserComponent;
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

        //TEST
        var createdOrder = orderComponent.createOrder(userName, userPhone, productName);

        //ASSERTS
        var order = orderRepository.findById(createdOrder.getId()).get();

        var authorId = order.getAuthorId();
        var productId = order.getProductId();

        var user = userRepository.findById(authorId);
        assertThat(user).isNotEmpty();
        assertThat(user.get().getPhone()).isNotEmpty();
        assertThat(user.get().getName()).isNotEmpty();
        assertThat(product.getId()).isEqualTo(productId);
    }

    @Test
    void errorWhenTryToCreatedWithoutProductTest() {
        var productName = "Beer";
        var error = assertThrows(NoSuchElementException.class,
                () -> orderComponent.createOrder("maxim","12345",productName)
        );

        assertThat(error.getMessage()).isEqualTo("Продукта с именем '%s' нет!", productName);
    }
}
