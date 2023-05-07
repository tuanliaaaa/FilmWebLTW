package film.api.service;

import film.api.DTO.UserByAdminDTO;
import film.api.models.Film;
import film.api.models.User;
import film.api.repository.FilmRepository;
import film.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    public Film findById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }


    public List<Film> findUsersByFilmNameContain(String filmName) {
        return filmRepository.findUsersByFilmNameContain(filmName);
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }
//    public Film updateFilm(Long id, UserByAdminDTO userPatchDTO) {
//        Object s=userRepository.findById(id);
//        User user = userRepository.findById(id).orElse(null);
//
//        if(userPatchDTO.getFullname() != null) {
//            user.setFullname(userPatchDTO.getFullname());
//        }
//
//        if(userPatchDTO.getUsername() != null) {
//            user.setUsername(userPatchDTO.getUsername());
//        }
//
//        return userRepository.save(user);
//    }
}
