package film.api.service;

import film.api.DTO.ChapterHotDTO;
import film.api.models.Actor;
import film.api.models.Chapter;
import film.api.models.History;
import film.api.repository.ChapterRepository;
import film.api.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    public List<History> getList(){

        return historyRepository.findAll();
    }

        public List<ChapterHotDTO> getChaptersHotCount(LocalDateTime fromDay, LocalDateTime toDay) {
            List<Object[]> results = historyRepository.getChaptersHotCount(fromDay, toDay);
            List<ChapterHotDTO> chapterHotDTOs = new ArrayList<>();
            for (Object[] result : results) {
                ChapterHotDTO chapterHotDTO = new ChapterHotDTO();
                BigInteger bigInt = (BigInteger) result[0];
                Long chapterID = bigInt.longValue();
                Optional<Chapter> chapter=chapterRepository.findById(chapterID);
                chapterHotDTO.setId((BigInteger) result[0]);
                chapterHotDTO.setCountView((BigInteger) result[1]);
                chapterHotDTO.setRateAvg((BigDecimal) result[2]);
                chapterHotDTO.setChapterImage(chapter.get().getChapterImage());
                chapterHotDTO.setChapterName(chapter.get().getChapterName());
                chapterHotDTOs.add(chapterHotDTO);
            }
            return chapterHotDTOs;
        }


}
