package steps.others;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.others.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Entao("a pagina de login deve ser apresentada")
    @Dado("que estou na pagina de login")
    public void queEstouNaPaginaDeLogin() {
        loginPage.validateLoginPage();
    }

    @Quando("preencho os campos usuario e senha")
    public void preenchoOsCamposUsuarioESenha() {
        loginPage.insertLoginAndPassword();
    }

    @E("clico no botao Login")
    public void clicoNoBotaoLogin() {
        loginPage.clickOnLoginButton();
    }

    @Entao("a pagina inicial deve ser apresentada")
    public void aPaginaInicialDeveSerApresentada() {
        loginPage.validateLoggedUser();
    }

    @Dado("que estou logado na plataforma")
    public void queEstouLogadoNaPlataforma() {
        loginPage.checkLoggedUser();
    }

    @Quando("realizar o logout do sistema")
    public void realizarOLogoutDoSistema() {
        loginPage.clickOnLogoutButton();
    }
}
