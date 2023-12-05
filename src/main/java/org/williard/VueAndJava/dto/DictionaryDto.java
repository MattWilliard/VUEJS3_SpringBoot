package org.williard.VueAndJava.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class DictionaryDto {

    public String word;
    public ArrayList<Phonetic> phonetics;
    public ArrayList<Meaning> meanings;
    public License license;
    public ArrayList<String> sourceUrls;

}
