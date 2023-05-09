package film.api.controller;

import film.api.DTO.ChapterHotDTO;
import film.api.DTO.UserDTO;
import film.api.exception.ExceptionResponse;
import film.api.DTO.HistoryDTO;
import film.api.DTO.HistoryRequestDTO;
import film.api.configuration.security.JWTUtil;
import film.api.exception.NotFoundException;
import film.api.models.Chapter;
import film.api.models.History;
import film.api.models.User;
import film.api.service.ChapterService;
import film.api.service.HistoryService;
import film.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ApiV1")
public class HistoryController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;
    @GetMapping("/ChapterHotFromDaytoDay")
    public ResponseEntity<?> getChapterHotFromDayToDay(
            @RequestParam(value = "fromDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDay,
            @RequestParam(value = "toDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDay) {

        if (fromDay == null) {
            fromDay = LocalDate.parse("1945-12-22", DateTimeFormatter.ISO_DATE);
        }
        if (toDay == null) {
            toDay = LocalDate.now();
            toDay.plusDays(1);
        }

        List<ChapterHotDTO> chapterHotList = new ArrayList<>();


        return ResponseEntity.ok(historyService.getChaptersHotCount(fromDay.atStartOfDay(),toDay.atStartOfDay()));
    }
    @GetMapping("/HistoryByChapterIDAndUserLogin/{chapterId}")
    public ResponseEntity<?> getHistoryByChapterIDAndUserLogin(HttpServletRequest request, @PathVariable Long chapterId) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtUtil.getUsernameFromToken(token);
        Long userID = historyService.getUserID(username);
        History history = historyService.getHistory(chapterId, userID);
        if(history==null){
           throw  new NotFoundException("User chưa xem Chapter này.");
        }
    return new ResponseEntity<>(new HistoryDTO(history), HttpStatus.OK);
    }
    @PostMapping("/HistoryByChapterIDAndUserLogin/{chapterId}")
    public ResponseEntity<?> addHistoryByChapterIDAndUserLogin(HttpServletRequest request, @PathVariable Long chapterId){
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtUtil.getUsernameFromToken(token);
        Long userID = historyService.getUserID(username);

        History history =new History   (null,0,chapterService.getChapter(chapterId),userService.findById(userID),0,LocalDateTime.now());
        historyService.saveHistory(history);
        return new ResponseEntity<>(new HistoryDTO(history), HttpStatus.CREATED);
    }
    @PatchMapping("/HistoryByChapterIDAndUserLogin/{chapterId}")
    public ResponseEntity<?> updateHistoryByChapterIDAndUserLogin(HttpServletRequest request, @PathVariable Long chapterId, @RequestBody HistoryRequestDTO historyRequestDTO){
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtUtil.getUsernameFromToken(token);
        User user =userService.findByUsername(username);
        if(user ==null){
            throw new NotFoundException("User này không tồn tại");
        }
        Chapter chapter=chapterService.findByID(chapterId);
        if(chapter ==null){
            throw new NotFoundException("Chapter này không tồn tại");
        }
        History history=historyService.updateHistory(user,chapter,historyRequestDTO);
        return new ResponseEntity<>(new HistoryDTO(history), HttpStatus.OK);
    }

}
