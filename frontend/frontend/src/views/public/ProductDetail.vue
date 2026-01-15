<template>
  <div class="product-detail" v-if="product">
    <h1>{{ product.name }}</h1>
    <img :src="product.lienImage" alt="Image produit" />
    <p><strong>Catégorie :</strong> {{ product.category }}</p>
    <p><strong>Description :</strong> {{ product.description }}</p>
    <p><strong>Prix :</strong> {{ product.price }} €</p>
    <p><strong>Stock :</strong> {{ product.quantiteStock }}</p>

    <div class="order-section" v-if="product.quantiteStock > 0">
      <label>Quantité :</label>
      <input type="number" v-model.number="quantity" min="1" :max="product.quantiteStock" />
      <button @click="addToCart">Ajouter au panier</button>
    </div>
    <p v-else>Produit en rupture de stock</p>
  </div>
</template>

<script>
import api from '@/api/axios'

export default {
  name: 'ProductDetail',
  data() {
    return {
      product: null,
      quantity: 1
    }
  },
  async mounted() {
    const id = this.$route.params.id;
    try {
      const res = await api.get(`/products/${id}`);
      this.product = res.data;
    } catch (err) {
      console.error(err);
      alert('Impossible de récupérer le produit');
    }
  },
  methods: {
    addToCart() {
      // Ici on pourra envoyer la commande ou ajouter au panier
      alert(`Ajouter ${this.quantity} produit(s) au panier`);
    }
  }
}
</script>

<style scoped>
.product-detail img {
  width: 400px;
  height: auto;
  margin-bottom: 1rem;
}
.order-section {
  margin-top: 1rem;
}
.order-section input {
  width: 60px;
  margin-right: 1rem;
}
</style>
