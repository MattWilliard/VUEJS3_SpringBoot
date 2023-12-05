import {Phonetic} from "@/types/dictionaryService/Phonetic";
import {Meaning} from "@/types/dictionaryService/Meaning";
import {License} from "@/types/dictionaryService/License";

export interface DictionaryDto {
    word: string;
    phonetics: Phonetic[];
    meanings: Meaning[];
    license: License;
    sourceUrls: string[];
}