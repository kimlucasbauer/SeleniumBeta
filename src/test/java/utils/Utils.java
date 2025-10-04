package utils;

import org.apache.commons.lang3.StringUtils;

public class Utils {

    public static String getEnvOrDefault(String systemVariable, String defaultValue) {
        String value = System.getenv(systemVariable);
        if (StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }

    public static String removeLateralSpaces(String string) {
        string = removeSpaceLeft(string);
        return removeSpaceRight(string);
    }

    public static String removeSpaceLeft(String string) {
        return string.replaceAll("^\\s+", "");
    }

    public static String removeSpaceRight(String string) {
        return string.replaceAll("\\s+$", "");
    }

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String normalizeStringForFileName(String name) {
        return name.replaceAll("[\\\\/:*?\"<>|]", "");
    }

    public static String inputDataQA(String dataQA) {
        return "//input[@data-qa=\"" + dataQA + "\"]";
    }

}
