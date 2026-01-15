<template>
  <div>
    <h2>Connexion</h2>
    <form @submit.prevent="loginUser">
      <input v-model="email" placeholder="Email" />
      <input type="password" v-model="password" placeholder="Mot de passe" />
      <button type="submit">Connexion</button>
    </form>
  </div>
</template>

<script>
import api from '@/api/axios'
import { store } from '@/store'

export default {
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async loginUser() {
      try {
        const res = await api.post('/auth/login', { email: this.email, password: this.password })
        const { token, email: userEmail, roles } = res.data

        // Stocker dans le store
        store.login({ email: userEmail, roles }, token)

        // Redirection vers l'accueil ou admin
        if (store.isAdmin()) {
          this.$router.push('/admin/products')
        } else {
          this.$router.push('/')
        }
      } catch (err) {
        alert('Email ou mot de passe incorrect')
      }
    }
  }
}
</script>
