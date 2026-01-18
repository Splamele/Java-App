<template>
  <div>
    <h1>Profil utilisateur</h1>
    <div v-if="user">
      <p><strong>Nom :</strong> {{ user.username }}</p>
      <p><strong>Email :</strong> {{ user.email }}</p>
      <p><strong>Rôle :</strong> {{ user.roles.join(', ') }}</p>
    </div>
  </div>
</template>

<script>
import axios from '@/api/axios.js'

export default {
  data() {
    return {
      user: null
    }
  },
  async created() {
    try {
      const res = await axios.get('/api/user/profile')
      this.user = res.data
    } catch (err) {
      console.error(err)
    }
  }
}
</script>
