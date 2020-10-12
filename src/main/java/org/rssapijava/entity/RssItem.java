package org.rssapijava.entity;

import org.rssapijava.type.RssSource;

import java.sql.Timestamp;

public class RssItem extends BaseEntity {
    private RssSource source;
    private String title;
    private String link;
    private Timestamp date;

    public RssItem(Integer id, RssSource source, String title, String link, Timestamp date) {
        super(id);
        this.source = source;
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public RssSource getSource() {
        return source;
    }

    public void setSource(RssSource source) {
        this.source = source;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
