<template>
  <div class="admin-dashboard">
    <h1>Tableau de bord Admin</h1>
    <div class="stats">
      <div>Total Produits: {{ stats.totalProducts }}</div>
      <div>Total Utilisateurs: {{ stats.totalUsers }}</div>
      <div>Total Commandes: {{ stats.totalOrders }}</div>
    </div>
  </div>
</template>

<script>
import api from '@/api/axios'

export default {
  data() {
    return {
      stats: {
        totalProducts: 0,
        totalUsers: 0,
        totalOrders: 0
      }
    }
  },
  async mounted() {
    try {
      const products = await api.get('/admin/products');
      const users = await api.get('/admin/users');
      const orders = await api.get('/admin/orders');

      this.stats.totalProducts = products.data.length;
      this.stats.totalUsers = users.data.length;
      this.stats.totalOrders = orders.data.length;
    } catch (err) {
      console.error(err);
    }
  }
}
</script>

<style scoped>
.stats {
  display: flex;
  gap: 2rem;
  margin-top: 1rem;
}
</style>
