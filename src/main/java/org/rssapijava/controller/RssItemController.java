package org.rssapijava.controller;

import org.rssapijava.repository.RssItemRepository;
import org.rssapijava.resource.RssItemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/rssitem")
public class RssItemController {
    @Autowired
    @Qualifier("rssItemRepository")
    private RssItemRepository rssItemRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    RssItemResource[] getAll() {

        return Arrays.stream(rssItemRepository.select())
                .map(rssItem -> new RssItemResource(rssItem))
                .toArray(RssItemResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RssItemResource get(@PathVariable Integer id) {
        return new RssItemResource(rssItemRepository.select(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RssItemResource post(@RequestBody RssItemResource rssItem) {
        return new RssItemResource(rssItemRepository.insert(rssItem.toEntity()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RssItemResource put(@PathVariable Integer id, @RequestBody RssItemResource rssItem) {
        return new RssItemResource(rssItemRepository.update(id, rssItem.toEntity()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RssItemResource delete(@PathVariable Integer id) {
        return new RssItemResource(rssItemRepository.delete(id));
    }
}
