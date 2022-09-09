package com.example.demo.repository.tutorials;

import java.util.List;

import com.example.demo.model.Tutorial;

public interface TutorialRepository {
    int save(Tutorial tutorial);

    int update(Tutorial tutorial);

    Tutorial findById(Long id);

    int deleteById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    int deleteAll();

}
