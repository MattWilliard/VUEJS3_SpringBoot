import {License} from "@/types/dictionaryService/License";

export interface Phonetic {
    audio: string;
    sourceUrl: string;
    licence: License;
    text: string;
}