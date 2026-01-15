import { reactive } from 'vue'

export const store = reactive({
    user: null,
    token: localStorage.getItem('token') || null,

    login(userData, token) {
        this.user = userData
        this.token = token
        localStorage.setItem('token', token)
    },

    logout() {
        this.user = null
        this.token = null
        localStorage.removeItem('token')
    },

    isAdmin() {
        return this.user && this.user.roles.includes('ADMIN')
    },

    isUser() {
        return this.user && this.user.roles.includes('USER')
    }
})
