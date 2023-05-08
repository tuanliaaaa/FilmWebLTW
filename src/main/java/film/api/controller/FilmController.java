package film.api.controller;

import film.api.DTO.*;
import film.api.models.Chapter;
import film.api.models.Film;
import film.api.models.User;
import film.api.service.ActorService;
import film.api.service.CategoryService;
import film.api.service.ChapterService;
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
    @Autowired
    private ActorService actorService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/AllFilm")
    public ResponseEntity<?> getFilmList(){
        List<Film> films=filmService.findAll();
        List<FilmDTO> filmListDTO =new ArrayList<>();
        for(Film film:films){
            filmListDTO.add(new FilmDTO(film));
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
    @GetMapping("/FilmByID/{FilmID}")
    public ResponseEntity<?> GetFilmByID(@PathVariable("FilmID") Long FilmID){
        Film film = filmService.findById(FilmID);
        FilmChapterActorDTO filmChapterActorDTO= new FilmChapterActorDTO();
        filmChapterActorDTO.loadData(film,chapterService,actorService,categoryService);
        return new ResponseEntity<>(filmChapterActorDTO, HttpStatus.OK);
    }

    @PostMapping("/AllFilm")
    public ResponseEntity<?> postFilm(@ModelAttribute  FilmRequestDTO filmPost){
        Film film =filmService.saveFilm(filmPost);
        return new ResponseEntity<>("đ", HttpStatus.CREATED);
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
        filmService.deleteFilmByID(FilmID);
        return new ResponseEntity<>("Xoa thanh cong", HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/FilmByID/{FilmID}")
    public ResponseEntity<?> PatchFilm(@PathVariable("FilmID") Long FilmID,@ModelAttribute FilmRequestDTO filmRequestDTO){
        Film film =filmService.updateFilm(FilmID,filmRequestDTO);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }
}
