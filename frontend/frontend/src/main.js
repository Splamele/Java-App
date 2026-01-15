import { createApp } from "vue";
import App from "./App.vue"; // bien relatif au fichier main.js
import router from "./router"; // si index.js est dans ./router, pas besoin de mettre /index.js

createApp(App).use(router).mount("#app");
