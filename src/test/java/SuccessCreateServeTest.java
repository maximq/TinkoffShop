import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuccessCreateServeTest {
    //проверка существования услуги и её удаление при необходимости
    //создание услуги
    //проверка соответствия поля productName и productPrice
    //удаление


    @Test
    public void checkCreateServe() {
        //PRECONDITION
        var productName = "testServe";
        float productPrice = 100;

        var pathGetProduct = "/byProductName?productName=" + productName;
        var response = TestUtils.callGet(pathGetProduct);

        if (response.extract().statusCode() == 200) {
            var idProduct = response.extract().body().path("id").toString();
            var pathDeleteProduct = "/deleteProductById?id=" + idProduct;
            TestUtils.callDelete(pathDeleteProduct).statusCode(200);
        }


        //TEST
        var pathCreateServe = "/createGood?name=" + productName + "&price=" + productPrice;
        var responseCreateServe = TestUtils.callPut(pathCreateServe).assertThat().statusCode(200);

        var nameProduct = responseCreateServe.extract().body().path("name");
        var priceProduct = responseCreateServe.extract().body().path("price");
        var idProduct = responseCreateServe.extract().body().path("id");

        //asserts
        assertThat(nameProduct).as("productName").isEqualTo(productName);
        assertThat(priceProduct).as("productPrice").isEqualTo(productPrice);

        //postcondition
        var pathDeleteProduct = "/deleteProductById?id=" + idProduct;
        TestUtils.callDelete(pathDeleteProduct).statusCode(200);

    }
}
