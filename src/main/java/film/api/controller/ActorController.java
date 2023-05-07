package film.api.controller;

import film.api.DTO.ActorDTO;
import film.api.models.Actor;
import film.api.repository.ActorRepository;
import film.api.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ApiV1", produces = "application/json")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/AllActor")
    public ResponseEntity<?> getAllActors() {
        String nhi = "toi te";

        return new ResponseEntity<>(actorService.getList(), HttpStatus.OK);
    }

    @PostMapping("AllActor")
    public ResponseEntity<?> addActor(@RequestBody ActorDTO actorPost) {

        Actor savedActor = actorService.addActor(Actor.builder()
                .ActorName(actorPost.getActorName())
                .Age(actorPost.getAge())
                .nativeLand(actorPost.getNativeLand())
                .Sex(actorPost.getSex()).build());
        return new ResponseEntity<>(savedActor, HttpStatus.OK);
    }

    @GetMapping("/ActorByID/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Long id) {
        Optional<Actor> actor = actorService.findById(id);

        if (!actor.isPresent()) {
            return new ResponseEntity<>("Không tồn tại có id = " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ActorDTO(actor.get()), HttpStatus.OK);
    }

    @PatchMapping("/ActorByID/{ActorByID}")
    public ResponseEntity<Object> updateActor(@PathVariable("ActorByID") Long id, @RequestBody ActorDTO actorPost) {
        Actor updateActor = actorService.addActor(Actor.builder()
                .ActorName(actorPost.getActorName())
                .Age(actorPost.getAge())
                .nativeLand(actorPost.getNativeLand())
                .Sex(actorPost.getSex()).build());
        Actor actor = actorService.updateActor(id, updateActor);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @DeleteMapping("/ActorByID/{ActorByID}")
    public ResponseEntity<?> deleteActor(@PathVariable Long ActorByID) {
        actorService.deleteById(ActorByID);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @GetMapping("/ActorByName/{ActorName}")
    public ResponseEntity<List<Actor>> searchActors(@PathVariable("ActorName") String actorName) {
        List<Actor> actors = actorService.searchActors(actorName);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

}

