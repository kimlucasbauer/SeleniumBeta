package pages.modules;

import pages.GeneralPage;
import utils.helpers.PropertiesLoader;

import static locators.modules.ContactUsLocator.*;

public class ContactUsPage extends GeneralPage {

    private final String email;
    private final String user;

    public ContactUsPage() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        email = propertiesLoader.getInstance().getValue("email");
        user = propertiesLoader.getInstance().getValue("user");
    }

    public void insertAllFieldsForContact() {
        String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        dsl.type(NAME_INPUT, user);
        dsl.type(EMAIL_INPUT, email);
        dsl.type(SUBJECT_INPUT, "Test Subject");
        dsl.type(MESSAGE_TEXTAREA, text);
        dsl.uploadFile(UPLOAD_INPUT, "file.txt");
    }

    public void clickOnSubmitButton() {
        dsl.click(SUBMIT_BUTTON);
    }

    public void validateTextAlert() {
        dsl.compare("Press OK to proceed!", dsl.getTextAlert());
    }

    public void clickOnOkButtonAlert() {
        dsl.acceptAlert();
    }

    public void validateSuccessMessage() {
        dsl.waitToBeVisable(SUCCESS_MESSAGE_ELEMENT);
        validateTextOnElement(SUCCESS_MESSAGE_ELEMENT, "Success! Your details have been submitted successfully.");
    }
}
