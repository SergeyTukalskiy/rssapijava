package org.rssapijava.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.rssapijava.entity.RssItem;
import org.rssapijava.entity.RssSource;

import java.sql.Timestamp;

public class RssItemResource extends BaseResource {
    private Integer id;
    private Integer sourceId;
    private String title;
    private String link;
    private Timestamp date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RssSourceResource source;

    public RssItemResource() {}

    public RssItemResource(RssItem rssItem) {
        this.id = rssItem.getId();
        this.sourceId = rssItem.getSourceId();
        this.title = rssItem.getTitle();
        this.link = rssItem.getLink();
        this.date = rssItem.getDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public RssSourceResource getSource() { return this.source; }

    public void setSource(RssSourceResource source) { this.source = source; }

    public RssItem toEntity() {
        return new RssItem(
                this.id,
                this.sourceId,
                this.title,
                this.link,
                this.date
        );
    }
}
