<template>
 <!-- The title is not v-bind to any data element in the Vue file, it is just explicitly defined and assigned right in this element. -->
  <Header @btn-click="toggleAddTask"  title="Task Tracker" :showAddTask="showAddTask"/>
  <div>
    <AddTask v-if="showAddTask" @add-task="addTask" />
  </div>
<!--  The colon (:tasks) means it is v-bind to the data element 'tasks [{}]' below.  This data is then fed to the Tasks component which can be read from there.-->
  <div>
    <Tasks @toggle-reminder="toggleReminder" @delete-task="confirmDeleteTask" :tasks="tasks" />
  </div>

  <Counter />

  <div>
    <Footer />
  </div>

  <Modal @modal-no="closeDeleteTaskModal" @modal-yes="deleteTask" :modal-active="deleteTaskModal.active">
    <div class="modal-content">
      <h1>Delete Task</h1>
      <p>Are you sure you want to delete this task?</p>
    </div>
  </Modal>

  <Modal @modal-no="leaveAddTaskModalActive" @modal-yes="closeAddTaskConfirmationModal" :modal-active="confirmLeaveAddTask">
    <div class="modal-content">
      <h1>Quit adding task?</h1>
      <p>Any text you entered will be lost!</p>
    </div>
  </Modal>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import Tasks from '@/components/Tasks.vue'
import AddTask from "@/components/AddTask.vue";
import { Task } from '@/types/Task'
import { DeleteTaskModal } from "@/types/DeleteTaskModal";
import { toast } from 'vue3-toastify'
import 'vue3-toastify/dist/index.css'
import Modal from '@/components/Modal.vue'
import JsonDatabaseService from '@/services/JsonDatabaseService'
import Counter from '@/components/Counter.vue'

export default defineComponent({
  name: 'Home',
  components: {
    Header,
    Footer,
    Tasks,
    AddTask,
    Modal,
    Counter
  },
  data(){
      return {
         tasks: [] as Task[],
         showAddTask: false,
         deleteTaskModal: {} as DeleteTaskModal,
         jsonDatabaseService: new JsonDatabaseService(),
         confirmLeaveAddTask: false,
      }
  },
  computed:{

  },
  methods: {
      async addTask(task:Task){
        const response = await this.jsonDatabaseService.addTask(task);
        this.tasks.push(response);
        toast.success('Task Added', {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 3000
        })
        this.showAddTask = false;
      },
      leaveAddTaskModalActive(){
          this.confirmLeaveAddTask = false;
      },
      closeAddTaskConfirmationModal(){
          this.confirmLeaveAddTask = false;
          this.showAddTask = false;
      },
      closeDeleteTaskModal(){
        this.deleteTaskModal.active = false;
      },
      confirmDeleteTask(id: number){
         this.deleteTaskModal.active = true;
         this.deleteTaskModal.id = id;
      },
      async deleteTask() {
        const response = await this.jsonDatabaseService.deleteTask(this.deleteTaskModal.id || 0)
        if (response.status === 200){
           await this.fetchTasks()
           toast.success(`Deleted task # ${this.deleteTaskModal.id}`, {
             position: toast.POSITION.TOP_CENTER,
             autoClose: 3000
           })
        } else {
          toast.error(`Error deleting task id ${this.deleteTaskModal.id}`, {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 3000
          })
        }
        this.resetDeleteTaskModal();
      },
      resetDeleteTaskModal(){
        this.deleteTaskModal.active = false;
        this.deleteTaskModal.id = null;
      },
      async toggleReminder(id:number){
        const data: Task = await this.jsonDatabaseService.updateTaskReminder(id);
        await this.fetchTasks();
        if(data.id === id){
          toast.info(`Reminder set to ${data.reminder} for task # ${data.id}`,{
              position: toast.POSITION.TOP_CENTER,
              autoClose: 3000
          })
        } else {
          toast.error(`Error setting reminder for task # ${data.id}`,{
            position: toast.POSITION.TOP_CENTER,
            autoClose: 3000
          })
      }
    },
    async toggleAddTask(task : Task){
      if(this.showAddTask){
        this.confirmLeaveAddTask = true;
      } else {
        this.showAddTask = !this.showAddTask;
      }
    },
    async fetchTasks(){
      this.tasks = await this.jsonDatabaseService.fetchTasks();
    }
  },
  created(){
    this.fetchTasks();
  }
});
</script>


