package org.rssapijava.resource;

import org.rssapijava.entity.RssItem;
import org.rssapijava.type.RssSource;

import java.sql.Timestamp;

public class RssItemResource extends BaseResource {
    private Integer id;
    private Integer source;
    private String title;
    private String link;
    private Timestamp date;

    public RssItemResource() {}

    public RssItemResource(RssItem rssItem) {
        this.id = rssItem.getId();
        this.source = rssItem.getSource().getNumVal();
        this.title = rssItem.getTitle();
        this.link = rssItem.getLink();
        this.date = rssItem.getDate();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RssItem toEntity() {
        return new RssItem(
                this.id,
                RssSource.fromInt(this.source),
                this.title,
                this.link,
                this.date
        );
    }
}
