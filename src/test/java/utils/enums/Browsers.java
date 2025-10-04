package utils.enums;

public enum Browsers {

    CHROME, EDGE, FIREFOX;

    public static Browsers getBrowser(String name) {
        try {
            return Browsers.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Invalid Browser: " + name, iae);
        }
    }

}
