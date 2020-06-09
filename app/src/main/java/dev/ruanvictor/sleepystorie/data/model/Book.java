package dev.ruanvictor.sleepystorie.data.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private String description;
    private int cover;
}
