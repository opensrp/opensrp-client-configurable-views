package org.smartregister.configurableviews.model;

import org.smartregister.view.contract.IField;

public class Field implements IField {
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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Field)) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Field f = (Field) o;
        return this.displayName != null && this.dbAlias != null && this.displayName.equals(f.displayName) && this.dbAlias.equals(f.dbAlias);
    }
}
