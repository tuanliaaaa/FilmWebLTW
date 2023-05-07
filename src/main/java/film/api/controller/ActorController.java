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
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllActors() {
        String nhi = "toi te";

        return new ResponseEntity<>(actorService.getList(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> addActor(@RequestBody ActorDTO actorPost) {

        Actor savedActor = actorService.addActor(Actor.builder()
                .ActorName(actorPost.getActorName())
                .Age(actorPost.getAge())
                .nativeLand(actorPost.getNativeLand())
                .Sex(actorPost.getSex()).build());
        return new ResponseEntity<>(savedActor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Long id) {
        Optional<Actor> actor = actorService.findById(id);

        if (!actor.isPresent()) {
            return new ResponseEntity<>("Không tồn tại có id = " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ActorDTO(actor.get()), HttpStatus.OK);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Object> updateActor(@PathVariable Long id, @RequestBody ActorDTO updateActor) {
//        Optional<Actor> actor = actorService.findById(id);
//        if (!actor.isPresent()) {
//            return new ResponseEntity<>("Không tồn tại có id = " + id, HttpStatus.OK);
//        }
//        actorService.updateActor(actor.get(), updateActor);
//        return new ResponseEntity<>(actor, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteActor(@PathVariable Long id) {
//        actorRepository.deleteById(id);
//        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
//    }
}

