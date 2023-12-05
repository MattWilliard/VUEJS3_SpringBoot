<template>
  <div class="boxed">
      <div class="counter">{{storeCounter.counter}}</div>
      <div class="buttons">
        <button class="add" @click="storeCounter.increaseCounter()">+</button>
        <button class="subtract" @click="storeCounter.decreaseCounter()">-</button>
      </div>
      <br>
      <hr>
      <div>
        This counter is: {{storeCounter.oddOrEven}}
      </div>
      <hr>
      <br>
        <div>
          <h3>Edit Counter: <input class="counterInput" v-model="storeCounter.counter" type="number"></h3>
        </div>
    </div>
</template>

<script lang="ts">
import { useCounterStore } from "@/stores/CounterStore";
  export default {
      name: 'Counter',
      data() {
        return {
          storeCounter: useCounterStore()
          }
      }
  }
</script>

<style scoped>
    .counter {
    font-size: 40px;
  }

  .buttons button{
    font-size: 40px;
  }

  button.add:hover {
    background: red;
  }

  button.subtract:hover {
      background: black;
      color: white;
    }

  .boxed {
    text-align: center;
    width: 250px;
    margin: 20px auto;
    overflow: auto;
    background: lightskyblue;
    border: 1px solid black;
    padding: 0px 20px 10px 20px;
    border-radius: 5px;
    }

  .counterInput {
    text-align: center;
    width: 75px;
  }
</style>