import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorTest {
    @Test
    public void checkErrorGetProduct(){
        var productName = "testProduct8";
        var pathGetProduct = "/byProductName?productName=" + productName;

        var response = TestUtils.callGet(pathGetProduct).assertThat().statusCode(404);
        var message = response.extract().body().path("message").toString();
        var messExpect = "Продукта с именем '" + productName +"' нет!";
        assertThat(message).as("message").isEqualTo(messExpect);

    }
}
