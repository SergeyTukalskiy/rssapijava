package org.rssapijava.controller;

import org.rssapijava.entity.RssItem;
import org.rssapijava.repository.RssItemRepository;
import org.rssapijava.repository.RssSourceRepository;
import org.rssapijava.resource.RssItemResource;
import org.rssapijava.resource.RssSourceResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/rssitem")
public class RssItemController {
    private final RssItemRepository rssItemRepository;

    private final RssSourceRepository rssSourceRepository;

    public RssItemController(RssItemRepository rssItemRepository, RssSourceRepository rssSourceRepository) {
        this.rssItemRepository = rssItemRepository;
        this.rssSourceRepository = rssSourceRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    RssItemResource[] getAll(@RequestParam(required = false) Integer sourceId,
                             @RequestParam(required = false) Object expand) {
        RssItem[] entities = sourceId == null ?
            rssItemRepository.select() :
            rssItemRepository.selectBySourceId(sourceId);
        return Arrays.stream(entities)
            .map(entity -> {
                RssItemResource resource = new RssItemResource(entity);
                if (expand != null)
                    resource.setSource(new RssSourceResource(
                            rssSourceRepository.select(entity.getSourceId()))
                    );
                return resource;
            })
            .toArray(RssItemResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RssItemResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        RssItem entity = rssItemRepository.select(id);
        if (entity == null) return null;
        RssItemResource resource = new RssItemResource(entity);
        if (expand != null)
            resource.setSource(
                    new RssSourceResource(rssSourceRepository.select(entity.getSourceId()))
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RssItemResource post(@RequestBody RssItemResource resource) {
        RssItem entity = rssItemRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new RssItemResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RssItemResource put(@PathVariable Integer id,
                        @RequestBody RssItemResource resource) {
        RssItem entity = rssItemRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new RssItemResource(entity);
         return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RssItemResource delete(@PathVariable Integer id) {
        RssItem entity = rssItemRepository.delete(id);
        if (entity == null) return null;
        RssItemResource resource = new RssItemResource(entity);
        return resource;
    }
}
