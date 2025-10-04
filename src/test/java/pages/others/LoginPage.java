package pages.others;

import pages.GeneralPage;
import utils.helpers.PropertiesLoader;

import static locators.modules.HomeLocator.CAROUSEL;
import static locators.others.LoginLocator.*;
import static locators.others.MenuLocator.*;

public class LoginPage extends GeneralPage {

    private final String email;
    private final String password;
    private final String user;

    public LoginPage() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        email = propertiesLoader.getInstance().getValue("email");
        password = propertiesLoader.getInstance().getValue("password");
        user = propertiesLoader.getInstance().getValue("user");
    }

    public void validateLoginPage() {
        dsl.waitToBeClickabe(EMAIL_INPUT);
        dsl.waitToBeClickabe(PASSWORD_INPUT);
        dsl.waitToBeClickabe(LOGIN_BUTTON);
    }

    public void insertLoginAndPassword() {
        dsl.type(EMAIL_INPUT, email);
        dsl.type(PASSWORD_INPUT, password);
    }

    public void clickOnLoginButton() {
        dsl.click(LOGIN_BUTTON);
    }

    public void validateLoggedUser() {
        dsl.waitToBeClickabe(LOGOUT_BUTTON);
        dsl.waitToBeVisable(LOGGED_USER_ELEMENT.setValue("USER", user));
        dsl.waitToBeClickabe(CAROUSEL);
    }

    public void checkLoggedUser() {
        if (dsl.doubleCheck(PASSWORD_INPUT, LOGGED_USER_ELEMENT.setValue("USER", user), 60)) {
            this.insertLoginAndPassword();
            this.clickOnLoginButton();
            this.validateLoggedUser();
        } else {
            dsl.returnToHomePage();
        }
    }

    public void clickOnLogoutButton() {
        dsl.click(LOGOUT_BUTTON);
    }
}
