package org.williard.VueAndJava.dto;

import lombok.Data;

@Data
public class Phonetic{
    public String audio;
    public String sourceUrl;
    public License license;
    public String text;
}
