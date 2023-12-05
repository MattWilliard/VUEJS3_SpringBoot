package org.williard.VueAndJava.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Meaning{
    public String partOfSpeech;
    public ArrayList<Definition> definitions;
    public ArrayList<String> synonyms;
    public ArrayList<String> antonyms;
}
