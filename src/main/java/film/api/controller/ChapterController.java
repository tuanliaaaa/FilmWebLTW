package film.api.controller;

import film.api.models.Chapter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequestMapping("/chapter-hot")
public class ChapterController {


        @GetMapping
        public ResponseEntity<?> getChapterHotFromDayToDay(
                @RequestParam(value = "fromDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDay,
                @RequestParam(value = "toDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDay) {

            if (fromDay == null) {
                fromDay = LocalDate.parse("1945-12-22", DateTimeFormatter.ISO_DATE);
            }
            if (toDay == null) {
                toDay = LocalDate.now();
            }

            List<ChapterHotDto> chapterHotList = new ArrayList<>();
            List<Map<String, Object>> chaptersHotCount = historyRepository.getChaptersHotCount(fromDay, toDay);

            for (Map<String, Object> chapterHotCount : chaptersHotCount) {
                Chapter chapterHot = chapterRepository.findById((Long) chapterHotCount.get("chapterId")).orElse(null);

                if (chapterHot != null) {
                    chapterHotList.add(new ChapterHotDto(
                            chapterHot.getId(),
                            (Double) chapterHotCount.get("avgRate"),
                            ((Number) chapterHotCount.get("viewCount")).intValue(),
                            chapterHot.getChapterImage(),
                            chapterHot.getChapterName()
                    ));
                }
            }

            return ResponseEntity.ok(chapterHotList);
        }

}
