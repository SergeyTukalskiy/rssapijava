package org.rssapijava.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.rssapijava.entity.RssSource;

public class RssSourceResource {
    private Integer id;
    private String name;
    private String link;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RssItemResource[] rssItems;

    public RssSourceResource() {}

    public RssSourceResource(RssSource rssSource) {
        this.id = rssSource.getId();
        this.name = rssSource.getName();
        this.link = rssSource.getLink();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public RssItemResource[] getRssItems() { return rssItems; }

    public void setRssItems(RssItemResource[] rssItems) { this.rssItems = rssItems; }

    public RssSource toEntity() {
        return new RssSource(
                this.id,
                this.name,
                this.link
        );
    }
}
