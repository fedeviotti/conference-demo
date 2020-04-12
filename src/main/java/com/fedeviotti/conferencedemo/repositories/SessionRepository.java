package com.fedeviotti.conferencedemo.repositories;

import com.fedeviotti.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
