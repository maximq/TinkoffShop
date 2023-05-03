import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuccessTest {
    //добавление товара
    //добавление заказа
    //проверка, что заказ добавлен
    // проверка , что пользватель и продукт в заказе корректный
    //удаление


    @Test
    public void checkCreateOrder() {
        //PRECONDITION
        var productName = "testProd1";
        var productPrice = 100;

        var userName = "Vanya";
        var userPhone = "+7123456767";

        var pathCreateProduct = "/createGood?name=" + productName + "&price=" + productPrice;
        var responseCreateProduct = TestUtils.callPut(pathCreateProduct).assertThat().statusCode(200);
        var idProduct = Long.valueOf((Integer) responseCreateProduct.extract().body().path("id"));

        var pathCreateOrder = "/createOrder?userName=" + userName + "&userPhone=" + userPhone + "&productName=" + productName;


        //TEST

        var responseCreateOrder = TestUtils.callPost(pathCreateOrder).assertThat().statusCode(200);

        var idOrder = Long.valueOf((Integer) responseCreateOrder.extract().body().path("id"));

        //asserts
        var pathGetUser = "/byPhone?phone=" + userPhone;

        var responseCreateUser = TestUtils.callGet(pathGetUser);

        var idProductInOrder = Long.valueOf((Integer) responseCreateOrder.extract().body().path("productId"));
        var idUserInOrder = Long.valueOf((Integer) responseCreateOrder.extract().body().path("authorId"));
        var idUser = Long.valueOf((Integer) responseCreateUser.extract().body().path("id"));

        assertThat(responseCreateUser.extract().statusCode()).as("statusCode").isEqualTo(200);
        assertThat(idProductInOrder).as("productId").isEqualTo(idProduct);
        assertThat(idUserInOrder).as("authorId").isEqualTo(idUser);

        //postcondition
        var pathDeleteOrder = "/deleteOrderById?id=" + idOrder;
        TestUtils.callDelete(pathDeleteOrder).statusCode(200);

        var pathDeleteProduct = "/deleteProductById?id=" + idProduct;
        TestUtils.callDelete(pathDeleteProduct).statusCode(200);

        var pathDeleteUser = "/deleteUserById?id=" + idUser;
        TestUtils.callDelete(pathDeleteUser).statusCode(200);

    }
}
