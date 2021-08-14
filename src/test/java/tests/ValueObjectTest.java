package tests;

import baseEntities.BaseTest;
import models.sauseDemo.UserSauseDemo;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutCompletePage;
import steps.LoginStep;

public class ValueObjectTest extends BaseTest {

    @Test(description = "Позитивный тест оплату")
    public void positiveChekOut() {
        UserSauseDemo userSauseDemo = setupUser();
        CheckOutCompletePage checkOutCompletePage = new LoginStep(driver)
                .loginWithCorrectAttribute(userSauseDemo.getLogin(), userSauseDemo.getPassword())
                .addOrDeleteProduct(userSauseDemo.getProductName(), true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOut(userSauseDemo.getFirstName(), userSauseDemo.getLastName(), userSauseDemo.getZipPostalCode())
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    private UserSauseDemo setupUser() {
        UserSauseDemo userSauseDemo = new UserSauseDemo();
        userSauseDemo.setLogin("standard_user");
        userSauseDemo.setPassword("secret_sauce");
        userSauseDemo.setProductName("Sauce Labs Fleece Jacket");
        userSauseDemo.setFirstName("Natali");
        userSauseDemo.setLastName("Leb");
        userSauseDemo.setZipPostalCode("11111");
        return userSauseDemo;
    }
}
