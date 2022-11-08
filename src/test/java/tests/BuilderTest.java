package tests;

import baseEntities.BaseTest;
import models.CustomerBuilder;
import models.UserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutCompletePage;
import pages.LoginPage;
import steps.LoginStep;

public class BuilderTest extends BaseTest {

    @Test(description = "Позитивный тест оплату")
    public void positiveChekOut() {
        UserBuilder userBuilder = setupUserBuilder();
        CustomerBuilder customerBuilder = setupCustomerBuilder();
        CheckOutCompletePage checkOutCompletePage = new LoginPage(driver,true)
                .loginWithCorrectAttributeForBuilder(userBuilder)
                .addOrDeleteProduct("Sauce Labs Fleece Jacket", true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOutForBuilder(customerBuilder)
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    private UserBuilder setupUserBuilder() {
        UserBuilder userBuilder = new UserBuilder.Builder()
                .withLogin("standard_user")
                .withPassword("secret_sauce")
                .build();
        return userBuilder;
    }

    private CustomerBuilder setupCustomerBuilder() {
        CustomerBuilder customerBuilder = new CustomerBuilder.Builder()
                .withFirstName("Natali")
                .withLastName("Leb")
                .withZipPostalCode("11111")
                .build();
        return customerBuilder;
    }
}
