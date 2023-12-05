<template>
  <label for="search">
    Type the name of a word to define
  </label>

  <input
    type="text"
    id="wordSearch"
    placeholder="Type here . . . "
    v-model="searchWord"
    @click="clearInputs()"
    @keyup.enter="getDefinition(searchWord)">

  <br />
  <br />

  <ul v-if="searchWords.length">
    <li class="liHeader">
      Showing {{ searchWords.length }} of {{ searchWords.length }} results
    </li>
    <li v-for="word in searchWords" @click="selectWord(word.text)">
      {{ word.text }}
    </li>
  </ul>

  <div v-if="definition.word">
    <p><b>Word:</b> {{ definition?.word }}</p>
    <p><b>Pronunciation: {{definition?.phonetics[0]?.text }}</b></p>
    <p v-if="definition?.phonetics[0]?.audio">Listen: <a target="_blank" :href="definition?.phonetics[0]?.audio">{{definition?.phonetics[0]?.text }} </a></p>
    <p><b>Definition(s):</b></p>
    <ol type="1">
      <li v-for="def in definition?.meanings[0]?.definitions">
        {{def.definition}}
      </li>
    </ol>
    <p></p>
  </div>

  <br />

</template>

<script>
import JsonDatabaseService from '@/services/JsonDatabaseService'
import DictionaryService from "@/services/DictionaryService";
import {def} from "@vue/shared";
export default {

    name: 'Search',
    components: {

    },
    data(){
        return {
            searchWord: '',
            jsonDatabaseService: new JsonDatabaseService(),
            dictionaryService: new DictionaryService(),
            words: [] ,
            selectedWord: '',
            definition: {}
        }
    },
    computed: {
      searchWords: function() {
        if (this.searchWord === '') {
          return [];
        }
        let matches = 0;
        return this.words.filter(word => {
          if (word.text.toLowerCase().includes(this.searchWord.toLowerCase()) && matches < 10) {
            matches++;
            return word;
          }
        })
      }
    },
    methods:{ def ,
      async fetchWords(){
        this.words = await this.jsonDatabaseService.fetchWords();
      },
      async getDefinition(word){
        const data = await this.dictionaryService.getMeaning(word);
        this.definition = data;
        const wordObject = {
            text: word
        }
        const res = await this.jsonDatabaseService.insertWord(wordObject);
        await this.fetchWords();
      },
      selectWord (word){
        this.selectedWord = word;
        this.searchWords = '';
        this.searchWord = word;
        this.getDefinition(word);
      },
      clearInputs(){
          this.selectedWord = '';
          this.definition = '';
      }
    },
    created(){
      this.fetchWords();
    }
}
</script>

<style scoped>
  ol {
      margin: 7px 20px 0px 20px;
      padding: 0;
      border: 1px solid black;
      border-radius: 5px;
  }
  li:nth-child(odd) {
    background: lightgray;
  }
  li:nth-child(even){
    background: white;
  }
  li:hover {
    color:darkblue;
    font-weight: bold;
  }
  audio {
    width: 200px;
  }

</style>