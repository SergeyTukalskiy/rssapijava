package org.rssapijava.controller;

import org.rssapijava.entity.RssSource;
import org.rssapijava.repository.RssItemRepository;
import org.rssapijava.repository.RssSourceRepository;
import org.rssapijava.resource.RssItemResource;
import org.rssapijava.resource.RssSourceResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/rsssource")
public class RssSourceController {
    private final RssSourceRepository rssSourceRepository;
    private final RssItemRepository rssItemRepository;

    public RssSourceController(RssSourceRepository rssSourceRepository, RssItemRepository rssItemRepository) {
        this.rssSourceRepository = rssSourceRepository;
        this.rssItemRepository = rssItemRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    RssSourceResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(rssSourceRepository.select())
            .map(entity -> {
                RssSourceResource resource = new RssSourceResource(entity);
                if (expand != null)
                    resource.setRssItems(
                        Arrays.stream(rssItemRepository.selectBySourceId(entity.getId()))
                            .map(e -> new RssItemResource(e))
                            .toArray(RssItemResource[]::new)
                    );
                return resource;
            })
            .toArray(RssSourceResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RssSourceResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        RssSource entity = rssSourceRepository.select(id);
        if (entity == null) return null;
        RssSourceResource resource = new RssSourceResource(entity);
        if (expand != null)
            resource.setRssItems(
                Arrays.stream(rssItemRepository.selectBySourceId(entity.getId()))
                    .map(e -> new RssItemResource(e))
                    .toArray(RssItemResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RssSourceResource post(@RequestBody RssSourceResource resource) {
        RssSource entity = rssSourceRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new RssSourceResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RssSourceResource put(@PathVariable Integer id,
                          @RequestBody RssSourceResource resource) {
        RssSource entity = rssSourceRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new RssSourceResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RssSourceResource delete(@PathVariable Integer id) {
        RssSource entity = rssSourceRepository.delete(id);
        if (entity == null) return null;
        return new RssSourceResource(entity);
    }
}
