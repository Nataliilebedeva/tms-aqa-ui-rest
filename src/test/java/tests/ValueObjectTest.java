package tests;

import baseEntities.BaseTest;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutCompletePage;
import steps.LoginStep;

public class ValueObjectTest extends BaseTest {

    @Test(description = "Позитивный тест оплату")
    public void positiveChekOut() {
        User user = setupUser();
        CheckOutCompletePage checkOutCompletePage = new LoginStep(driver)
                .loginWithCorrectAttribute(user.getLogin(), user.getPassword())
                .addOrDeleteProduct(user.getProductName(), true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOut(user.getFirstName(), user.getLastName(), user.getZipPostalCode())
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    private User setupUser() {
        User user = new User();
        user.setLogin("standard_user");
        user.setPassword("secret_sauce");
        user.setProductName("Sauce Labs Fleece Jacket");
        user.setFirstName("Natali");
        user.setLastName("Leb");
        user.setZipPostalCode("11111");
        return user;
    }
}
