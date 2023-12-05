<template>
  <transition name="modal-animation">
    <div v-show="modalActive" class="modal">
      <transition name="modal-animation-inner">
        <div v-show="modalActive" class="modal-inner">
          <i @click="$emit('modal-no')" class="far fa-times-circle"></i>
            <!-- Modal Content -->
          <slot />
          <button class="btn" @click="$emit('modal-yes')">Yes</button>
          <button class="btn" @click="$emit('modal-no')">No</button>
        </div>
      </transition>
    </div>
  </transition>
</template>

<script>
export default {
  props:['modalActive']
};

</script>

<style scoped>
 .modal {
  display: flex;
  justify-content: center;
  align-items: baseline;
  height: 100vh;
  width: 100vw;
  position: fixed;
  top: 20px;
  left: 0;
  background-color:rgb(255, 255, 255, 0.7);
 }

 .modal-inner {
  position: relative;
  max-width: 400px;
  width: 80%;
  box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1), 0 2px 4px -1px rgba(0,0,0,0.6);
  background: white;
  padding: 10px 10px;
 }

 .btn {
    padding: 5px 10px;
    border: none;
    font-size: 16px;
    background-color: cornflowerblue;
    cursor: pointer;
 }

 i {
    position: absolute;
    top: 15px;
    right: 15px;
    cursor: pointer;
    font-size: 20px;
 }

 i:hover {
    color:red;
 }

  .modal-animation-enter-active,
  .modal-animation-leave-active {
     transition: opacity .5s cubic-bezier(.52, 0.02, 0.19, 1.02);
  }

   .modal-animation-enter-from,
   .modal-animation-leave-to{
     opacity: 0;
   }

  .modal-animation-inner-enter-active{
    transition: all .5s cubic-bezier(.52, 0.02, 0.19, 1.02) 0.15s;
  }

  .modal-animation-inner-leave-active{
     transition: all .5s cubic-bezier(.52, 0.02, 0.19, 1.02);
  }

  .modal-animation-inner-enter-from {
    opacity: 0;
    transform: scale(0.8);
  }

  .modal-animation-inner-leave-to {
    transform: scale(0.8);
  }

</style>