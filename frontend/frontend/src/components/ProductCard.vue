<template>
  <div class="product-card" @click="goToDetail">
    <img :src="product.lienImage" alt="image produit" />
    <h3>{{ product.name }}</h3>
    <p>{{ product.description }}</p>
    <p>Prix : {{ product.price }} €</p>
  </div>
</template>

<script>
export default {
  name: "ProductCard",
  props: { product: Object },
  methods: {
    goToDetail() {
      this.$router.push({ name: 'ProductDetail', params: { id: this.product.id } });
    }
  }
}
</script>


<style scoped>
.product-card {
  border: 1px solid #ddd;
  padding: 1rem;
  border-radius: 8px;
  width: 250px;
  margin: 1rem;
}
.product-card img {
  width: 100%;
  height: auto;
}
</style>
