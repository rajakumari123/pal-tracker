package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping("/time")
    public String getTimeStatus() {
        return "running";
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.POST)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntry);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.GET)
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (timeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(timeEntry);
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();
        return ResponseEntity.status(HttpStatus.OK).body(timeEntries);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updateTimeEntry = timeEntryRepository.update(id, timeEntry);
        if (updateTimeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateTimeEntry);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
