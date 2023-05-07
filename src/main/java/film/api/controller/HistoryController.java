package film.api.controller;

import film.api.DTO.ChapterHotDTO;
import film.api.repository.CategoryRepository;
import film.api.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ApiV1")
public class HistoryController {

    @Autowired
    private HistoryService historyService;
    @GetMapping("/ChapterHotFromDaytoDay")
    public ResponseEntity<?> getChapterHotFromDayToDay(
            @RequestParam(value = "fromDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDay,
            @RequestParam(value = "toDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDay) {

        if (fromDay == null) {
            fromDay = LocalDate.parse("1945-12-22", DateTimeFormatter.ISO_DATE);
        }
        if (toDay == null) {
            toDay = LocalDate.now();
        }

        List<ChapterHotDTO> chapterHotList = new ArrayList<>();


        return ResponseEntity.ok(historyService.getChaptersHotCount(fromDay.atStartOfDay(),toDay.atStartOfDay()));
    }

}
