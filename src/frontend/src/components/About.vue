<template>
 <Counter />

 <Search />

 <WebSocket />
</template>

<script>

import Counter from "@/components/Counter.vue"
import Search from "@/components/Search.vue"
import WebSocket from "@/components/WebSocket.vue"
  export default {
      name: 'About',
      components:{
          WebSocket,
          Counter,
          Search
      }
  }
</script>