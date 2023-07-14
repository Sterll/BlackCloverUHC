package fr.yanis.blackcloveruhc.list;

public enum CampList {

    ROYAUME("Royaume"),
    ELFES("Elfes"),
    TRIADE_SOMBE("Triade sombre"),
    SOLO("Solo");

    private String displayName;

    CampList(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
