package film.api.service;

import film.api.models.Chapter;
import film.api.models.History;
import film.api.repository.ChapterRepository;
import film.api.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> getList(){
        return chapterRepository.findAll();
    }
    public List <Chapter> getChapterByFilmID(Long filmID){
        return chapterRepository.getChapterByFilmID(filmID);
    }



}
