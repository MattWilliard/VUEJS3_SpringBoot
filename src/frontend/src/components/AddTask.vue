<template>
  <form @submit="onSubmit" class = "add-form">
    <div class="form-control">
        <label>Task</label>
        <!-- v-model binds the input to the data() attributes below.  It is a 2 way binding. -->
        <input v-model="text" type="text" name="task" placeholder="Add Task" />
      </div>
    <div class="form-control">
      <label>Day & Time</label>
      <input v-model="day" type="text" name="day" placeholder="Add Day & Time" />
    </div>
    <div class="form-control form-control-check">
      <label>Set Reminder</label>
      <input v-model="reminder" type="checkbox" name="reminder" />
    </div>
    <input type="submit" value = "Save Task" class="btn btn-block" />
  </form>
</template>

<script>
  import {toast} from "vue3-toastify";
  import { Task } from "../types/Task";
export default {
    name: 'AddTask',
    data(){
        return{
          text: '',
          day: '',
          reminder: false
        }
    },
    methods: {
        onSubmit(e){
            e.preventDefault();

            if(!this.text){
              toast.error('Please add a task!', {
                  autoClose: false,
                  hideProgressBar: true,
                  position: toast.POSITION.TOP_CENTER
              })
            } else {
              const newTask = {
                  text: this.text,
                  day: this.day,
                  reminder: this.reminder
              };
              this.$emit("add-task", newTask);
            }

            this.clearForm();

        },
        clearForm(){
            this.text='';
            this.day='';
            this.reminder=false;
        }
    }
  }
</script>

<style scoped>
  .add-form {
    margin-bottom: 40px;
  }

  .form-control {
    margin: 20px 0;
  }

  .form-control label {
    display: block;
  }

  .form-control input {
    width: 100%;
    height: 40px;
    margin: 5px;
    padding: 3px 7px;
    font-size: 17px;
  }

  .form-control-check {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .form-control-check label {
    flex: 1;
  }

  .form-control-check input {
    flex: 2;
    height: 20px;
  }
</style>