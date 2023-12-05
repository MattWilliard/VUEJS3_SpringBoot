package org.williard.VueAndJava.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Definition {
    public String definition;
    public ArrayList<Object> synonyms;
    public ArrayList<Object> antonyms;
    public String example;
}
