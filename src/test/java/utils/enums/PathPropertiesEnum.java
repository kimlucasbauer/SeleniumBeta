package utils.enums;

public enum PathPropertiesEnum {

    LOGIN("login.properties");

    private final String path;

    PathPropertiesEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
