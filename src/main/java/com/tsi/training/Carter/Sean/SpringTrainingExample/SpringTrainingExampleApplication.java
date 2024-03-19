package com.tsi.training.Carter.Sean.SpringTrainingExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class SpringTrainingExampleApplication {

	@Autowired
	private FilmRepository filmRepo;
	@Autowired
	private ActorRepository actorRepo;
	@Autowired
	private FilmActorRepository filmActorRepo;

	@Autowired
	private LanguageRepository languageRepo;

	public SpringTrainingExampleApplication(ActorRepository actorRepo, FilmRepository filmRepo,
											FilmActorRepository filmActorRepo, LanguageRepository languageRepo) {
		this.actorRepo = actorRepo;
		this.filmRepo = filmRepo;
		this.filmActorRepo = filmActorRepo;
		this.languageRepo = languageRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringTrainingExampleApplication.class, args);
	}

    //Returns a list of all actors names and IDs
	@GetMapping("/allActors")
	public Iterable<Actor> getAllActors() {
		return actorRepo.findAll();
	}

	//Returns an actor based on the id in the url
	@GetMapping("/actor/{id}")
	public Actor getActorByID(@PathVariable("id") Integer actor_ID) {
		return actorRepo.findById(actor_ID).
				orElseThrow(() -> new ResourceAccessException("Actor not found."));
	}

	//Finds a list of all actors with the entry given somewhere in their name.
	@GetMapping("/findActors")
	public Iterable<Actor> getActorsFromName(@RequestBody String actor_name) {
		ArrayList<Actor> wantedActors = new ArrayList<>();
		Iterable<Actor> allActors = actorRepo.findAll();
		allActors.forEach((actor) -> {
			String name = actor.getFirstName() + " " +  actor.getLastName();
			if (name.contains(actor_name.toUpperCase())) {
				wantedActors.add(actor);
			}
		});
		return wantedActors;
	}

	//Add a new actor to the table, needs a JSON input (in the body when using Postman)
	@PostMapping("/addActor")
	@ResponseStatus(HttpStatus.CREATED)
	public Actor addNewActor(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}

	//Adds multiple new actors at once, JSON needs to be in a list (like when retrieving all actors)
    @PostMapping("/addActors")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewActors(@RequestBody Iterable<Actor> actorList) {
        actorList.forEach(
                (actor) -> {actorRepo.save(actor);});
    }

	//Combines the get and add to update an actor's information
    @PutMapping("/updateActor/{id}")
    public void updateActor(@RequestBody Actor newActor, @PathVariable("id") Integer actor_ID) {
        actorRepo.findById(actor_ID)
                .map(actor -> {
                    actor.setFirstName(newActor.getFirstName());
                    actor.setLastName(newActor.getLastName());
                    return actorRepo.save(actor);
                })
                .orElseGet(() -> actorRepo.save(newActor));
    }

	//Deletes an actor from the table
    @DeleteMapping("/deleteActor/{id}")
    public void deleteActor(@PathVariable("id") Integer actor_ID) {
		Iterable<FilmActor> filmActorList = filmActorRepo.findAll();
		filmActorList.forEach((film) -> {
			if (Objects.equals(film.getActor_ID(), actor_ID)) {
				filmActorRepo.delete(film);
			}
		});
		actorRepo.deleteById(actor_ID);
    }

	//Returns a list of all films, with their rating and descriptions
	@GetMapping("/allFilms")
	public Iterable<Film> getAllFilms() {
		return filmRepo.findAll();
	}

	//Returns an individual film based on the id entered
	@GetMapping("/film/{id}")
	public Film getFilmByID(@PathVariable("id") Integer film_ID) {
		return filmRepo.findById(film_ID).
				orElseThrow(() -> new ResourceAccessException("Film not found."));
	}

	//Adds new films to the database, may not cope with language ID, but we'll see
	@PostMapping("/addFilms")
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewFilms(@RequestBody Iterable<Film> filmList) {
		filmList.forEach(
				(film) -> {filmRepo.save(film);});
	}

	//Edits a film entry (just make sure the updated info isn't in a list, duh)
	@PutMapping("/updateFilm/{id}")
	public void updateFilm(@RequestBody Film newFilm, @PathVariable("id") Integer film_ID) {
		filmRepo.findById(film_ID)
				.map(film -> {
					film.setFilmTitle(newFilm.getFilmTitle());
					film.setFilmDescription(newFilm.getFilmDescription());
					film.setFilmLanguage_ID(newFilm.getFilmLanguage_ID());
					return filmRepo.save(film);
				})
				.orElseThrow(() -> new ResourceAccessException("Film not found."));
	}

	@GetMapping("/filmsFromActor/{id}")
	public Iterable<Film> getActorsFilms(@PathVariable("id") Integer actor_ID) {
		ArrayList<Film> films = new ArrayList<>();
		Iterable<FilmActor> filmActorList = filmActorRepo.findAll();
		filmActorList.forEach((film) -> {
			if (Objects.equals(film.getActor_ID(), actor_ID)) {
				Film newFilm = filmRepo.findById(film.getFilm_ID()).
						orElseThrow(() -> new ResourceAccessException("Film not found."));
				;
				films.add(newFilm);
			}
		});
		return films;
	}
}

