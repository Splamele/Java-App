<template>
  <div class="auth-form">
    <h2>Inscription</h2>
    <form @submit.prevent="register">
      <input v-model="username" placeholder="Nom d'utilisateur" />
      <input v-model="email" placeholder="Email" />
      <input type="password" v-model="password" placeholder="Mot de passe" />
      <button type="submit">S'inscrire</button>
    </form>
  </div>
</template>

<script>
import api from '@/api/axios'

export default {
  name: "Register",
  data() {
    return {
      username: '',
      email: '',
      password: ''
    }
  },
  methods: {
    async register() {
      try {
        await api.post('/auth/register', {
          username: this.username,
          email: this.email,
          password: this.password
        });
        alert('Inscription réussie !');
        this.$router.push('/login');
      } catch (err) {
        alert('Erreur lors de l\'inscription');
        console.error(err);
      }
    }
  }
}
</script>
