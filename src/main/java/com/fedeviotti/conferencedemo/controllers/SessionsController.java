package com.fedeviotti.conferencedemo.controllers;

import com.fedeviotti.conferencedemo.models.Session;
import com.fedeviotti.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private final SessionRepository sessionRepository;

    public SessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Also need to check for children before deleting.
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what will be updated
        // TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession,"sessionId");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
