package org.smartregister.configurableviews.model;

public class Field {
    private String displayName;
    private String dbAlias;

    public Field() {
    }

    public Field(String displayName, String dbAlias) {
        this.displayName = displayName;
        this.dbAlias = dbAlias;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDbAlias() {
        return dbAlias;
    }

    public void setDbAlias(String dbAlias) {
        this.dbAlias = dbAlias;
    }
}
