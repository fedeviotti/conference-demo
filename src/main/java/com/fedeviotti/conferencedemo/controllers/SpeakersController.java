package com.fedeviotti.conferencedemo.controllers;

import com.fedeviotti.conferencedemo.models.Speaker;
import com.fedeviotti.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    private final SpeakerRepository speakerRepository;

    public SpeakersController(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getOne(id);
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Also need to check for children before deleting.
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        // because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what will be updated
        // TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker,"speakerId");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
