package film.api.controller;

import film.api.models.Actor;
import film.api.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/list")
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @PostMapping("add")
    public ResponseEntity<?> addActor(@RequestBody Actor actor) {
        Actor savedActor = actorRepository.save(actor);
        return new ResponseEntity<>(savedActor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        if (!actor.isPresent()) {
            return new ResponseEntity<>("Không tồn tại có id = " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateActor(@PathVariable Long id, @RequestBody Actor updateActor) {
        Optional<Actor> actor = actorRepository.findById(id);
        if (!actor.isPresent()) {
            return new ResponseEntity<>("Không tồn tại có id = " + id, HttpStatus.OK);
        }
        actorRepository.delete(actor.get());
        actorRepository.save(updateActor);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteActor(@PathVariable Long id) {
//        actorRepository.deleteById(id);
//        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
//    }
}

