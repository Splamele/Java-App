<template>
  <div class="admin-users">
    <h1>Gestion des Utilisateurs</h1>
    <table>
      <thead>
      <tr>
        <th>Email</th>
        <th>Username</th>
        <th>Roles</th>
        <th>Enabled</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id">
        <td>{{ user.email }}</td>
        <td>{{ user.username }}</td>
        <td>
          <select v-model="user.roles[0]">
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
        </td>
        <td>
          <input type="checkbox" v-model="user.enabled"/>
        </td>
        <td>
          <button @click="updateUser(user)">💾 Sauvegarder</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import api from '@/api/axios'

export default {
  data() {
    return {
      users: []
    }
  },
  methods: {
    async fetchUsers() {
      const res = await api.get('/admin/users')
      this.users = res.data
    },
    async updateUser(user) {
      await api.put(`/admin/users/${user.id}`, user)
      this.fetchUsers()
    }
  },
  mounted() {
    this.fetchUsers()
  }
}
</script>
