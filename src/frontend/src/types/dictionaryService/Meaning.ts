import {Definition} from "@/types/dictionaryService/Definition";

export interface Meaning {
    partOfSpeech: string;
    definitions: Definition[];
    synonyms: string[];
    antonyms: string[];
}