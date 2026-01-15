<template>
  <div class="home">
    <h1>Nos produits</h1>

    <div class="filters">
      <input v-model="searchName" placeholder="Rechercher par nom" />
      <input v-model="searchCategory" placeholder="Filtrer par catégorie" />
      <button @click="fetchProducts">Rechercher</button>
    </div>

    <div class="products-list">
      <ProductCard v-for="prod in products" :key="prod.id" :product="prod" />
    </div>
  </div>
</template>

<script>
import api from '@/api/axios'
import ProductCard from '@/components/ProductCard.vue'

export default {
  name: "Home",
  components: { ProductCard },
  data() {
    return {
      products: [],
      searchName: '',
      searchCategory: ''
    }
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        let query = [];
        if (this.searchName) query.push(`name=${this.searchName}`);
        if (this.searchCategory) query.push(`category=${this.searchCategory}`);
        const queryString = query.length ? `?${query.join('&')}` : '';
        const res = await api.get(`/products/search${queryString}`);
        this.products = res.data;
      } catch (err) {
        console.error(err);
        alert('Impossible de récupérer les produits');
      }
    }
  }
}
</script>

<style scoped>
.filters {
  margin-bottom: 2rem;
}
.filters input {
  margin-right: 0.5rem;
  padding: 0.5rem;
}
.products-list {
  display: flex;
  flex-wrap: wrap;
}
</style>
