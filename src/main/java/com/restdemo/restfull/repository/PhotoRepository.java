package com.restdemo.restfull.repository;

import com.restdemo.restfull.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
