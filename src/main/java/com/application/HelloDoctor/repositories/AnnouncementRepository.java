package com.application.HelloDoctor.repositories;

import com.application.HelloDoctor.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    public Announcement findById(int id);
}
