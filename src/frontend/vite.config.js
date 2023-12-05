import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig(({command, mode}) => {
    const env = loadEnv(mode, process.cwd(), '');
    return {
        base: process.env.VITE_BASE_URL,
        plugins: [vue(),],
        build:{
            outDir: "dist"
        }
    }
})
