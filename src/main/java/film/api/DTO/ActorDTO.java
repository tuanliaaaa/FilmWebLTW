package film.api.DTO;

import film.api.models.Actor;
import lombok.Data;

@Data
public class ActorDTO {

    private Long id;

    private String ActorName;

    private int Age;

    private String nativeLand;

    private int Sex;

    public ActorDTO(Actor actor) {
        this.id = actor.getId();
        this.ActorName = actor.getActorName();
        this.Age = actor.getAge();
        this.nativeLand = actor.getNativeLand();
        this.Sex = actor.getSex();
    }
}
