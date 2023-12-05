import { defineStore } from 'pinia'

export const useCounterStore = defineStore('CounterStore', {
    state: () => ({
        counter: 0,
    }),
    actions:{
        increaseCounter(){
            this.counter++
        },
        decreaseCounter(){
           this.counter--
        }
    },
    getters:{
        oddOrEven: (state) => {
            if(state.counter % 2 === 0){
                return 'Even'
            } else {
                return 'Odd'
            }
        }
    }
})