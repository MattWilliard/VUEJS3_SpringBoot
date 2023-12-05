import { Task } from "@/types/Task"
export default class JsonDatabaseService {

    apiBaseUrl = process.env.NODE_ENV == 'local' ? '/json/db' : 'http://localhost:5000';

    fetchTasks = async () => {
        // const res = await fetch('/json/db/tasks')
        console.log(process.env.NODE_ENV);
        const res = await fetch(`${this.apiBaseUrl}/tasks`)
        const data: Task[] = await res.json();
        return data;
    }

    fetchTask = async(id: number): Promise<Task> => {
        // const res = await fetch(`/json/db/tasks/${id}`)
        const res = await fetch(`${this.apiBaseUrl}/tasks/${id}`)
        const data: Task = await res.json();
        return data;
    }
    addTask = async(task:Task): Promise<Task> => {
        const response = await fetch(`${this.apiBaseUrl}/tasks`,{
            method: 'POST',
            headers:{
                'Content-type': 'application/json',
            },
            body: JSON.stringify(task)
        })
        const data = await response.json()
        return data;
    }

    deleteTask = async(id: number)=> {
        const response = await fetch(`${this.apiBaseUrl}/tasks/${id}`, {
            method: 'DELETE'
        })
        return response;
    }

    updateTaskReminder = async(id: number)=> {
        const taskToToggle: Task = await this.fetchTask(id);
        const taskToUpdate = {...taskToToggle, reminder: !taskToToggle.reminder}

        const response =  await fetch(`${this.apiBaseUrl}/tasks/${id}`, {
            method: 'PATCH',
            headers:{
                'Content-type': 'application/json',
            },
            body: JSON.stringify(taskToUpdate)
        })
        const data: Task = await response.json();
        return data;
    }

    fetchWords = async () => {
        const res = await fetch(`${this.apiBaseUrl}/words`)
        const data = await res.json();
        return  data;
    }

    insertWord = async (word: any) => {
        const res: [] = await this.fetchWords();
        const exists = res.filter((w: any) => {
            return w.text === word.text;
        })
        if (exists.length) {
            return false;
        } else {
            const res = await fetch(`${this.apiBaseUrl}/words`, {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(word)
            })
            const data = await res.json();
            return true;
        }
    }


}