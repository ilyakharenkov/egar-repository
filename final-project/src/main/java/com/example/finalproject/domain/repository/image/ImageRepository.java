package com.example.finalproject.domain.repository.image;

import com.example.finalproject.domain.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
