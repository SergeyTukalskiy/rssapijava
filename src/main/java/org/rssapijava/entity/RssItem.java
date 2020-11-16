package org.rssapijava.entity;

import java.sql.Timestamp;

public class RssItem extends BaseEntity {
    private Integer sourceId;
    private String title;
    private String link;
    private Timestamp date;

    public RssItem(Integer id, Integer sourceId, String title, String link, Timestamp date) {
        super(id);
        this.sourceId = sourceId;
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getDate() { return date; }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
