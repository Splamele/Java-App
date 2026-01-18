<template>
  <div>
    <h1>Mes commandes</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Total</th>
        <th>Statut</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="order in orders" :key="order.id">
        <td>{{ order.id }}</td>
        <td>{{ order.orderDate }}</td>
        <td>{{ order.totalAmount }}</td>
        <td>{{ order.status }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from '@/api/axios.js'

export default {
  data() {
    return {
      orders: []
    }
  },
  async created() {
    try {
      const res = await axios.get('/api/orders/my-orders')
      this.orders = res.data
    } catch (err) {
      console.error(err)
    }
  }
}
</script>
