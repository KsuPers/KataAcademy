public enum RomanNumSeconds {
    X("X", "10"), XX("XX", "20"), XXX("XXX", "30"),
    XL("XL", "40"), L("L", "50"), LX("LX", "60"),
    LXX("LXX", "70"), LXXX("LXXX", "80"), XC("XC", "90");

    private final String key;
    private final String value;

    RomanNumSeconds(String key, String value) {
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
