package hr.petkovic.diplomski.android.data.model;

import java.io.Serializable;

public class EntryType implements Serializable {
    private Long id;
    private String mainType;
    private String subType;

    public EntryType() {
    }

    public EntryType(Long id, String mainType, String subType) {
        this.id = id;
        this.mainType = mainType;
        this.subType = subType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return mainType + '-' + subType;
    }
}
