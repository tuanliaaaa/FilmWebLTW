package film.api.service;

import film.api.DTO.ActorDTO;
import film.api.models.Actor;
import film.api.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;
    public List<Actor> getList(){

        return actorRepository.findAll();
    }

    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Optional<Actor> findById(Long id) {
        return actorRepository.findById(id);
    }

    public void updateActor(Actor actor, Actor updateActor) {
        actorRepository.delete(actor);
        actorRepository.save(updateActor);
    }


}
