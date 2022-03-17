package domain;

import java.time.LocalDateTime;

public class JoinWithThingsAndTagTb{
    
    protected Long thingsId;
    protected Long userId;
    protected LocalDateTime dateTime;
    protected String content;
    protected Long categoryId;
    protected int tagId;
    protected String tagName;

    public JoinWithThingsAndTagTb(Long thingsId, Long userId, LocalDateTime dateTime, String content, Long categoryId, int tagId, String tagName) {
        this.thingsId = thingsId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.content = content;
        this.categoryId = categoryId;
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Long getThingsId() {
        return thingsId;
    }
    public void setThingsId(Long thingsId) {
        this.thingsId = thingsId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getTagId() {
        return tagId;
    }
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
