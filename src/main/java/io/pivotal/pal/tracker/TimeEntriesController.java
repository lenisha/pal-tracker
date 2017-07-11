package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pivotal on 7/11/17.
 */

@RestController
@RequestMapping("/timeEntries")
public class TimeEntriesController {

        private TimeEntryRepository timeEntriesRepo;

        public TimeEntriesController(TimeEntryRepository timeEntriesRepo) {
            this.timeEntriesRepo = timeEntriesRepo;
        }

        @PostMapping
        public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
            TimeEntry createdTimeEntry = timeEntriesRepo.create(timeEntry);

            return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
        }

        @GetMapping("{id}")
        @ResponseBody
        public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
            TimeEntry timeEntry = timeEntriesRepo.get(id);
            if (timeEntry != null) {
                return new ResponseEntity<>(timeEntry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping
        public ResponseEntity<List<TimeEntry>> list() {
            return new ResponseEntity<>(timeEntriesRepo.list(), HttpStatus.OK);
        }

        @PutMapping("{id}")
        public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
            TimeEntry updatedTimeEntry = timeEntriesRepo.update(id, timeEntry);
            if (updatedTimeEntry != null) {
                return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("{id}")
        public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
            timeEntriesRepo.delete(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


