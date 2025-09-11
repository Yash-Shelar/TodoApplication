package com.project.TodoApplication.service;

import com.project.TodoApplication.dto.FilterDto;
import com.project.TodoApplication.enums.TodoStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@SessionScope
public class FilterService {
    private TodoStatus status;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public void setFilter(FilterDto filterDto) {
        var status = filterDto.getStatus();
        if (status != null && !status.isEmpty())
            this.setStatus(TodoStatus.valueOf(status));
        else
            this.setStatus(null);

        var date = filterDto.getDate();
        if (date != null && !date.isEmpty())
            this.setDate(LocalDate.parse(date));
        else
            this.setDate(null);

        var timeFrom = filterDto.getTimeFrom();
        if (timeFrom != null && !timeFrom.isEmpty()) {
            String prefix = "";
            if (timeFrom.length() == 4)
                prefix = "0";
            this.setTimeFrom(LocalTime.parse(prefix + timeFrom));
        } else {
            this.setTimeFrom(null);
        }

        var timeTo = filterDto.getTimeTo();
        if (timeTo != null && !timeTo.isEmpty()) {
            String prefix = "";
            if (timeTo.length() == 4)
                prefix = "0";
            this.setTimeTo(LocalTime.parse(prefix + timeTo));
        } else {
            this.setTimeTo(null);
        }
    }

    public void resetFilter() {
        this.setStatus(null);
        this.setDate(null);
        this.setTimeFrom(null);
        this.setTimeTo(null);
    }

    public Optional<TodoStatus> getStatus() {
        if (status == null)
            return Optional.empty();
        return Optional.of(status);
    }

    private void setStatus(TodoStatus status) {
        this.status = status;
    }

    public Optional<LocalDate> getDate() {
        if (date == null)
            return Optional.empty();
        return Optional.of(date);
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public Optional<LocalTime> getTimeFrom() {
        if (timeFrom == null)
            return Optional.empty();
        return Optional.of(timeFrom);
    }

    private void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Optional<LocalTime> getTimeTo() {
        if (timeTo == null)
            return Optional.empty();
        return Optional.of(timeTo);
    }

    private void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }
}
