package com.ksquareinc.filesservice.model.responses;

import java.time.LocalDateTime;
import java.util.List;

public class Wrapper<T> {
    public List<T> content;
    public LocalDateTime time = LocalDateTime.now();

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
