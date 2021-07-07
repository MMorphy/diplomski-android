package hr.petkovic.diplomski.android.data.model;

import java.util.Date;

public class Entry {

    private Long id;
    private Long amount;
    private Date createDate;
    private String description;
    private EntryType type;
    private User createdBy;

    public Entry() {
    }

    public Entry(Long id, Long amount, Date createDate, String description, EntryType type, User createdBy) {
        this.id = id;
        this.amount = amount;
        this.createDate = createDate;
        this.description = description;
        this.type = type;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
