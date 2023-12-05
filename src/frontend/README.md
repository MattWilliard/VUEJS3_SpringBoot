<style>
    .green {
        color: green;
    }
    .blue {
        color: blue;
    }
    .orange {
      color: orange;
    }
   .purple {
      color: rebeccapurple;
   }
   .cream {
      color: lightgoldenrodyellow;
   }
   .red {
      color: red;
   }
</style>
# VUE JS 3 VUE-TASK-TRACKER

## <u>Project setup</u>
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### <u>My VUE notes</u>

<span class="green">Props{ }</span>: Used to pass data from a Parent Vue down to a child Vue.  A Parent Vue uses ***:propName*** to pass the parameters down to the child.<br>

<span class="green">@click="$emit('delete-task', task.id)"</span> : Used to pass data up from a child element to the immediate parent.
   - The 1st parameter is the name of the emitted action.
   - The 2nd parameter is the data we can pass up the chain.

<span class="green">v-model="someRef"</span>:  Use for two-way binding to the variable named "someRef" in this instance.

### <u>VUE JS Router Set-up</u>

<i>This installs the latest version of the Vue router</i><br>
<b>Install</b>:  <span class="orange">npm i vue-router@next</span>
1. Within the <span class="green">src</span> folder, create a folder named '<span class="green">router</span>'.
   1. Within the router folder, create a file named <span class="blue">index.js</span>.
   2. Add the import:  <span class="orange">import</span> { <span class="cream">createRouter, createWebHistory</span>} <span class="orange">from</span> <span class="green">"vue-router"</span>;
   3. Import any Vue components that will be used:  <span class="orange">import</span> { <span class="purple">About</span>, <span class="purple">Home</span> } <span class="orange">from</span> <span class="green">"../components/About.vue"</span>;
    ```
    const routes = [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/about',
            name: 'About',
            component: About
        }
    ]
    
    const router = createRouter({
        //Allows the user to use the Browser's Forward and Back button to navigate.
        history: createWebHistory(process.env.BASE_URL),
        routes
    })
    
    export default router
    ```
2. Within the <span class="blue">main.ts</span> file, we must import and "Use" the router. 
   1. <span class="orange">import</span> <span class="purple">router<span class="orange"> from</span> <span class="green">"./router"</span>;
   2. <span class="purple">createApp</span>(<span class="purple">App</span>).<span class="cream">use</span>(<span class="purple">router</span>).<span class="cream">use</span>(<span class="cream">createPinia</span>()).<span class="cream">mount</span>(<span class="green">'#app'</span>)
3. Within the <span class="blue">App.vue</span> we must add the Router Links
    ```
     <template>
         <div class="container">
         <router-link to="/">Home</router-link> | <router-link to="/about">About</router-link>
         <router-view/>
       </div>
     </template>
    ```

### <u>JSON Server</u>
This allows you to mock JSON responses from a local DB/File to mimic Rest API calls.

Install: <span class="orange">npm i json-server</span>

Configuration: 
   1. Create a JSON file at the root level of the project.  In this project is it named db.json.
   2. Within package.json create script to reference the file above and start the server: json-server --watch db.json --port 5000

### <u>Concurrently</u>
Concurrently allows you to chain NPM commands together from within a single script.

Install: <span class="orange">npm install concurrently --save</span>

Configuration:
1. Within the <span class="blue">package.json</span> file.
2. Add a script to the <span class="green">"scripts"</span> section.  In the example below I added the script <span class="green">"dev"</span>.  
3. Start with the key word/library "concurrently" and then add each individual npm command within escaped double quotes \"  \"
4. In the example below, we are running the npm run backend and then the npm run serve commands
```
      "scripts": {
         "local": "concurrently \"npm run backend\" \"npm run serve -- --mode=local\" ",
         "dev": "concurrently \"npm run backend\" \"npm run serve -- --mode=development\" ",
         "test": "concurrently \"npm run backend\" \"npm run serve -- --mode=test\" "
      }
```
5. To run this script, type: <span class="orange">npm run dev</span>

### <u>Environment Configuration</u>
1. Create .env.<name> files at the root level of the project.  
 - <span class="blue">env.local</span>
 - <span class="blue">env.development</span> {for some reason you cannot use <span class="green">env.dev</span>, it must be a reserved name}
 - <span class="blue">env.test</span>
2. Any custom named variable must be prefixed with <span clas="green">VUE_APP_{name}</span>
 - VUE_APP_ENV_NAME=Test
 - VUE_APP_API_URL=https://someurl:8080/api
3. To start the server based upon one of the aforementioned environment variables:
 - <span class="orange">npm run serve -- --mode=local</span>
 - <span class="orange">npm run serve -- --mode=development</span>
 - <span class="orange">npm run serve -- --mode=test</span>
4. To reference the environment variables from within the code:
 - ${<span class="purple">process.env.</span>VUE_APP_ENV_NAME}

### <u>Proxy Configuration</u>
1. Create a file at the root level of the project named <span class="blue">vue.config.js</span>
   2. <span class="red">NOTE</span>: This is what allows us to get around the browser's CORS restriction and communicate to services on a different URL host:port.
```
module.exports = {
 devServer:{
     proxy: {
         '^/TaskTracker/db/api': {
             target: 'http://localhost:5000' ,
             changeOrigin: true,
             logLevel: 'debug',
             pathRewrite: {
                 '^/TaskTracker/db/api': '/'
             },
         },
         '/java': {
             target: 'http://localhost:8081',
             changeOrigin: true,
             pathRewrite: {
                 '/java': '/'
             },
         }
     }
 },
 publicPath: process.env.PUBLIC_PATH
 ```
- <span class="green">^/TaskTracker/db/api: {}'</span> ==>  When the path '/TaskTracker/db/api' is requested, the base URL of 'http://localhost:5000' is used as the prefix.
   - This means the full URL will end up being:  http://localhost:5000/TaskTracker/db/api
- <span class="green">pathRewrite:{}</span> ==> re-writes '/TaskTracker/db/api' to '/' so that ultimately the call is made to 'http://localhost:5000/'

## <u>Pinia</u> 
##### <i>State management that replaces VUEX</i>
Install: <span class="orange">npm install pinia</span>

Within the <span class="blue">main.ts</span> file, we must import and "Use" Pinia.
1. <span class="orange">import</span> { <span class="cream">createPinia</span> } <span class="orange"> from</span> <span class="green">"pinia"</span>;
2. <span class="purple">createApp</span>(<span class="purple">App</span>).<span class="cream">use</span>(<span class="purple">router</span>).<u><span class="cream">use</span>(<span class="cream">createPinia</span>())</u>.<span class="cream">mount</span>(<span class="green">'#app'</span>)

Next we can create a Pinia State-Store to manage our data across components.
1. Create a folder named <span class="green">stores</span> under the <span class="green">src</span> directory.
2. In this example we create a file named <span class="blue">CounterStore.js</span> within the <span class="green">stroes</span> directory.
3. We need to import the DefinePinia function from the pinia library: <span class="orange">import</span> { <span class="cream">defineStore</span> } <span class="orange"> from</span> <span class="green">"pinia"</span>;
   1. Export a const that returns the defineStore method.
      1. <span class="green">State()</span>: contains the individual variables.
      2. <span class="green">Actions</span>: contains the <u>Methods</u>. They can combine data, fetch data and run JavaScript logic.
      3. <span class="green">Getters</span>: return <u>Computed</u> properties of the variables in the store.  They receive the <u>state</u> as the first parameter to encourage the usage of arrow function:
         ```
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
         ```
Referencing the Store within a component.
1. Import the Store within the component:  <span class="orange">import</span> { <span class="purple">useCounterStore</span> } <span class="orange"> from</span> <span class="green">"@/stores/CounterStore"</span>;
2. Create a variable within the Data method of the component and assign it to the State Store.
   ```
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
   ```
   1. 1-way Databinding to state variables or getters: {{<span class="purple">storeCounter.counter</span>}}
   2. Referencing actions:  @click<span class="green">="</span><span class="purple">storeCounter</span>.<span class="cream">decreaseCounter</span>()<span class="green">"</span>


### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
