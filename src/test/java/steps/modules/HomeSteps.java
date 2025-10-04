package steps.modules;

import io.cucumber.java.pt.Quando;
import pages.modules.HomePage;

public class HomeSteps {

    private final HomePage homePage = new HomePage();

    @Quando("acessar o modulo (Contact Us|Products|Cart)$")
    public void acessarOModuloContactUs(String module) {
        homePage.navigateToPage(module.toUpperCase().replaceAll(" ", "_"));
    }
}
