import {DictionaryDto} from "@/types/dictionaryService/DictionaryDto";

export default class DictionaryService {

    apiBaseUrl = process.env.NODE_ENV == 'local' ? '/java' : 'http://localhost:8081';
    getMeaning = async (word: String) => {

        const res= await fetch(`${this.apiBaseUrl}/api/v1/define/${word}`,{
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });
        const data: DictionaryDto[] = await res.json();
        return data;
    }
}