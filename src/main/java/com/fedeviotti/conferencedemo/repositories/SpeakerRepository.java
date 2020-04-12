package com.fedeviotti.conferencedemo.repositories;

import com.fedeviotti.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
