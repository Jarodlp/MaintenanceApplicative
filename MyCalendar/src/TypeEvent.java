public enum TypeEvent {
    RDV_PERSONNEL("RDV_PERSONNEL"), REUNION("REUNION"), PERIODIQUE("PERIODIQUE");

    private final String stringValue;

    TypeEvent(String string) {
        stringValue = new String(string);
    }
}
