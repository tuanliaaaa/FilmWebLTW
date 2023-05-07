package film.api.controller;

import film.api.DTO.FilmDTO;
import film.api.DTO.UserByAdminDTO;
import film.api.models.Film;
import film.api.models.User;
import film.api.service.CategoryService;
import film.api.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/ApiV1")

public class FilmController {
    @Autowired
    private FilmService filmService;
    @GetMapping("/AllFilm")
    public ResponseEntity<?> getFilmList(){
        List<Film> films=filmService.findAll();
        List<FilmDTO> filmListDTO =new ArrayList<>();
        for(Film film:films){
            filmListDTO.add(new FilmDTO(film));
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
    @GetMapping("/FilmByName/{filmName}")
    public ResponseEntity<?> getFilmByID(@PathVariable("filmName") String filmName){
        List<Film> films=filmService.findUsersByFilmNameContain(filmName);
        List<FilmDTO> filmListDTO=new ArrayList<>();
        for (Film film:films){
            filmListDTO.add(new FilmDTO(film));
        }

        return ResponseEntity.ok(filmListDTO);
    }
    @DeleteMapping("/FilmByID/{FilmID}")
    public ResponseEntity<?> DeleteFilm(@PathVariable("FilmID") Long FilmID){
        filmService.deleteById(FilmID);
        return new ResponseEntity<>("Xoa thanh cong", HttpStatus.NO_CONTENT);
    }

}
