package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.tutorials.TutorialRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        System.out.println("Request to list all tutorials.");
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            if (title == null) {
                tutorialRepository.findAll().forEach(tutorials::add);
            } else {
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
            }
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<String> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>("Tutorial was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")

    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Tutorial tutorial = tutorialRepository.findById(id);
        System.out.println("ID:" + id);
        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/tutorials/{id}")

    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.findById(id);
        if (_tutorial != null) {
            _tutorial.setId(id);
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            tutorialRepository.update(_tutorial);
            return new ResponseEntity<>("Tutorial was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find tutorial with id= " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {
            int result = tutorialRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot delete tutorial with id=" + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Tutorial was delete successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")

    ResponseEntity<String> deleteAll() {
        try {
            int result = tutorialRepository.deleteAll();
            return new ResponseEntity<>("Deleted Arrow " + result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorial/published/{status}")

    ResponseEntity<List<Tutorial>> findTutorialByPublished(@PathVariable("status") String status) {
        try {
            System.out.println("Status : " + status);
            boolean published = Boolean.parseBoolean(status) ? true : false;
            List<Tutorial> tutorials = tutorialRepository.findByPublished(published);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
