package steps.modules;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import pages.modules.ContactUsPage;

public class ContactUsSteps {

    private final ContactUsPage contactUsPage = new ContactUsPage();

    @E("preencher todos os campos do formulario de contato com o suporte")
    public void preencherTodosOsCamposDoFormularioDeContatoComOSuporte() {
        contactUsPage.insertAllFieldsForContact();
    }

    @E("enviar o formulario de contato com o suporte")
    public void enviarOFormularioDeContatoComOSuporte() {
        contactUsPage.clickOnSubmitButton();
    }

    @Entao("um alerta de confirmacao do envio do formulario deve ser apresentado")
    public void umAlertaDeConfirmacaoDoEnvioDoFormularioDeveSerApresentado() {
        contactUsPage.validateTextAlert();
        contactUsPage.clickOnOkButtonAlert();
    }

    @E("uma mensagem de sucesso devera aparecer ao final do contato com o suporte")
    public void umaMensagemDeSucessoDeveraAparecerAoFinalDoContatoComOSuporte() {
        contactUsPage.validateSuccessMessage();
    }
}
