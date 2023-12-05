<template>
  <button @click="onClick()" :style="{background: color} " class="btn">{{text}}</button>
</template>

<script>
  export default {
    name: 'Button',
    // Props are used specifically to have data passed down from a parent component into here.
    props: {
        text: String,
        color: String
    },
    methods:{
        onClick() {
            this.$emit('btn-click')
        }
    }
  }
</script>