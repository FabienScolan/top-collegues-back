package dev.top.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Action;
import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
@RequestMapping("/collegues")
public class CollegueCtrl {
	@Autowired
	private CollegueRepo collegueRepo;

	@GetMapping
	public List<Collegue> findAll() {
		return this.collegueRepo.findAll();
	}

	@RequestMapping(value = "/collegues/{pseudo}", method = RequestMethod.PATCH)
	public ResponseEntity<?> patchCollegue(@PathVariable("pseudo") String pseudo, @RequestBody Action action) {
		// logger.info("Fetching User with id {}", id);
		Collegue collegue = null;
		List<Collegue> listeCollegues = collegueRepo.findAll();
		Iterator<Collegue> it = listeCollegues.iterator();
		while (it.hasNext()) {
			Collegue comp = it.next();
			if (comp.getPseudo().equals(pseudo))
				collegue = comp;
		}
		if (collegue == null) {
			// logger.error("User with id {} not found.", id);
			return new ResponseEntity("User with pseudo " + pseudo + " not found", HttpStatus.NOT_FOUND);
		}
		if (action == Action.AIMER) {
			collegue.setScore(collegue.getScore() + 100);
		} else if (action == Action.DETESTER) {
			collegue.setScore(collegue.getScore() - 50);
		}
		return new ResponseEntity<Collegue>(collegue, HttpStatus.OK);
	}

}
