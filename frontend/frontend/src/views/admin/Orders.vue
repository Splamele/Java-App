<template>
  <div class="admin-orders">
    <h1>Commandes</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Utilisateur</th>
        <th>Date</th>
        <th>Total</th>
        <th>Statut</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="order in orders" :key="order.id">
        <td>{{ order.id }}</td>
        <td>{{ order.user.username }}</td>
        <td>{{ new Date(order.orderDate).toLocaleString() }}</td>
        <td>{{ order.totalAmount }}</td>
        <td>
          <select v-model="order.status">
            <option value="PENDING">PENDING</option>
            <option value="PROCESSING">PROCESSING</option>
            <option value="SHIPPED">SHIPPED</option>
            <option value="DELIVERED">DELIVERED</option>
          </select>
        </td>
        <td>
          <button @click="updateOrder(order)">💾 Mettre à jour</button>
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
      orders: []
    }
  },
  methods: {
    async fetchOrders() {
      const res = await api.get('/admin/orders')
      this.orders = res.data
    },
    async updateOrder(order) {
      await api.put(`/admin/orders/${order.id}`, order)
      this.fetchOrders()
    }
  },
  mounted() {
    this.fetchOrders()
  }
}
</script>

