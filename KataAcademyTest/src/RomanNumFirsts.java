public enum RomanNumFirsts {
    I("I", "1"), II("II", "2"), III("III", "3"),
    IV("IV", "4"), V("V", "5"), VI("VI", "6"),
    VII("VII", "7"), VIII("VIII", "8"), IX("IX", "9"),
    X("X", "10");

    private final String key;
    private final String value;

    RomanNumFirsts(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
