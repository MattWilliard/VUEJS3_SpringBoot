<template>
  <div :key="task.id" v-for="task in tasks">
    <Task @toggle-reminder="$emit('toggle-reminder', task.id)" @delete-task="$emit('delete-task', task.id)" :task="task"/>
  </div>
</template>

<script>
  import Task from './Task.vue'
  export default {
      name: 'Tasks',
      // Props are used specifically to have data passed down from a parent component into here.
      props: {
          tasks: Array
      },
      components: {
          Task
      },
      emits:['delete-task','toggle-reminder'],
  }
</script>