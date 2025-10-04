package pages.modules;

import locators.others.MenuLocator;
import pages.GeneralPage;

import java.util.ArrayList;
import java.util.Collections;

public class HomePage extends GeneralPage {

    public void navigateToPage(String module) {
        ArrayList<MenuLocator> path = new ArrayList<>();
        MenuLocator modulePath = MenuLocator.valueOf(module);
        path.add(modulePath);
        while (modulePath != null) {
            modulePath = modulePath.getPreviousModule();
            if (modulePath != null) {
                path.add(modulePath);
            }
        }
        Collections.reverse(path);
        for (MenuLocator currentModule : path) {
            dsl.click(currentModule);
        }
    }
}
