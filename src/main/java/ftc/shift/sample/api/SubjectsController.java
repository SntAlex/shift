package ftc.shift.sample.api;

import ftc.shift.sample.models.Subject;
import ftc.shift.sample.services.SubjectsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(description = "Запросы для работы с предметами")
public class SubjectsController {
    private static final String SUBJECTS_PATH = "/api/v001/days/";
    private SubjectsService subjectsService;

    @Autowired
    public SubjectsController(SubjectsService subjectsService){
        this.subjectsService = subjectsService;
    }

    @PostMapping(SUBJECTS_PATH+"/{dayName}")
    @ApiOperation(value = "Добавление предмета")
    public ResponseEntity<?> addSubject(
            @PathVariable String dayName,
            @RequestBody Subject subject) {
        subjectsService.addSubject(dayName, subject);
        return ResponseEntity.ok().build();
    }

    @GetMapping(SUBJECTS_PATH+"/{dayName}")
    @ApiOperation(value = "Получить все предметы в пределах дня")
    public ResponseEntity<Collection<Subject>> getAllSubjects(
            @PathVariable String dayName
    ) {
        Collection<Subject>subjectCollection = subjectsService.getAllSubjects(dayName);
        return ResponseEntity.ok(subjectCollection);
    }

    @PatchMapping(SUBJECTS_PATH+"/{dayName}")
    @ApiOperation(value = "Удаление всех предметов в пределах дня")
    public ResponseEntity<?> clearSubjects(
            @PathVariable String dayName
    ) {
        subjectsService.clearSubjects(dayName);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(SUBJECTS_PATH+"/{dayName}/{id}")
    @ApiOperation(value = "Удаление предмета")
    public ResponseEntity<?> clearSubject(
            @PathVariable String dayName,
            @PathVariable Integer id
    ){
        subjectsService.clearSubject(dayName, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(SUBJECTS_PATH+"/{dayName}")
    @ApiOperation(value = "Изменение предмета")
    public ResponseEntity<?> changeSubject(
            @PathVariable String dayName,
            @RequestBody Subject subject
    ) {
        subjectsService.changeSubject(dayName, subject);
        return ResponseEntity.ok().build();
    }

    @GetMapping(SUBJECTS_PATH+"/{dayName}/{id}")
    @ApiOperation(value = "Получить предмет из дня")
    public ResponseEntity<Subject> fetchSubject(
            @PathVariable String dayName,
            @PathVariable Integer id
    ){
        Subject subject = subjectsService.fetchSubject(dayName, id);
        return ResponseEntity.ok(subject);
    }
}
