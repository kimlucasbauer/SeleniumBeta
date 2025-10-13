package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.StringTokenizer;

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

    public static Integer getAmountCharacter(String string, String character) {
        return StringUtils.countMatches(string, character);
    }

    public static String getLastNameInBars(String string) {
        int amountBars = getAmountCharacter(string, "/");
        return getStringDelimiter(string, "/", amountBars);
    }

    /**
     * @param item            is the string to be delimited.
     * @param character       is the string delimiter.
     * @param delimiterAmount is the number of cuts to be performed on the item. (starts at 0)
     * @return string delimited
     */
    public static String getStringDelimiter(String item, String character, Integer delimiterAmount) {
        String characterDuplicate = character + character;
        while (item.contains(characterDuplicate)) {
            item = item.replace(characterDuplicate, character + " " + character);
        }
        StringTokenizer st = new StringTokenizer(item);
        for (int i = 0; i <= delimiterAmount; i++) {
            item = st.nextToken(character);
        }
        return item;
    }

}
