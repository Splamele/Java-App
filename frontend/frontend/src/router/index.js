import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/public/Home.vue';
import Login from '@/views/auth/Login.vue';
import Register from '@/views/auth/Register.vue';
import ProductDetail from '@/views/public/ProductDetail.vue';
import AdminDashboard from '@/views/admin/Dashboard.vue'
import AdminProducts from '@/views/admin/Products.vue'
import AdminUsers from '@/views/admin/Users.vue'
import AdminOrders from '@/views/admin/Orders.vue'
import Profile from '@/views/user/Profile.vue'
import Orders from '@/views/user/Orders.vue'
import CreateOrder from '@/views/user/CreateOrder.vue'

const routes = [
    { path: '/', component: Home },
    { path: '/product/:id', component: ProductDetail, name: 'ProductDetail' },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    {
        path: '/user/profile',
        component: Profile
    },
    {
        path: '/user/orders',
        component: Orders
    },
    {
        path: '/user/order',
        component: CreateOrder
    },

    // ADMIN ROUTES
    { path: '/admin', component: AdminDashboard, meta: { requiresAdmin: true } },
    { path: '/admin/products', component: AdminProducts, meta: { requiresAdmin: true } },
    { path: '/admin/users', component: AdminUsers, meta: { requiresAdmin: true } },
    { path: '/admin/orders', component: AdminOrders, meta: { requiresAdmin: true } },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const user = JSON.parse(localStorage.getItem('user')); // ou ton store/vuex

    if (to.meta.requiresAdmin) {
        if (user && user.roles.includes('ADMIN')) {
            next();
        } else {
            next('/'); // redirige vers l'accueil si pas admin
        }
    } else {
        next();
    }
});


export default router;
