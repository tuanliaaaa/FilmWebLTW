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

    public Actor updateActor(Long id, Actor updateActor) {
        Actor actor = actorRepository.findById(id).get();
        actor.setActorName(updateActor.getActorName());
        actor.setAge(updateActor.getAge());
        actor.setNativeLand(updateActor.getNativeLand());
        actor.setSex(actor.getSex());
        return actorRepository.save(actor);
    }


    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }

    public List<Actor> searchActors(String actorName) {
        return actorRepository.searchActors(actorName);
    }
}
