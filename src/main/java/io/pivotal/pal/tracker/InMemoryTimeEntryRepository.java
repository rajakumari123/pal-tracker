package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private HashMap<String, TimeEntry> timeEntries;
    private long id ;

    public InMemoryTimeEntryRepository(){
        timeEntries = new HashMap<>();
        id = 1L;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntries.put(String.valueOf(id), timeEntry);
        id++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(String.valueOf(id));
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntrieList = new ArrayList<>(timeEntries.values());
        return timeEntrieList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry existingTimeEntry = find(id);
        if(existingTimeEntry != null) {
            timeEntries.put(String.valueOf(id), timeEntry);
            timeEntry.setId(id);
            return timeEntry;
        }
        return existingTimeEntry;
    }

    @Override
    public TimeEntry delete(long id) {
        TimeEntry timeEntry = find(id);
        if(timeEntry != null){
            timeEntries.remove(String.valueOf(id));
        }
        return timeEntry;
    }
}
