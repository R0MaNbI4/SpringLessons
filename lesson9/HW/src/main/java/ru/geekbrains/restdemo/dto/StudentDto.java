package ru.geekbrains.restdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StudentDto {

    @JsonIgnore
    Long id;

    @NotNull
    @NotEmpty (message = "{validation.NotEmpty.message}")
    String name;

    @NotNull
    @Min(value = 0, message = "{validation.Min.message}")
    @Max(value = 100, message = "{validation.Max.message}")
    Integer score;

    public StudentDto() {
    }

    public StudentDto(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
