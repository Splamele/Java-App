# Spring Boot JWT Shop

Ce projet est une application **Spring Boot** avec backend REST sécurisé via **JWT** et frontend en **Vue.js**.  
Il implémente une boutique avec gestion des utilisateurs, produits, commandes et backoffice admin.

---

## Prérequis

- Java 17+
- Maven
- Node.js & npm
- MySQL

---

## Lancer le backend

1. Cloner le projet
2. Configurer la base de données dans `application.properties` :

properties

spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_ta_bdd
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
Créer les tables users et role (avec les rôles USER et ADMIN) si elles n’existent pas.

Lancer le backend avec :

mvn spring-boot:run
Le backend sera accessible sur : http://localhost:8080

## Lancer le frontend (qui ne marche pas )
Se rendre dans le dossier frontend :

cd frontend
Installer les dépendances :

npm install
Lancer le frontend :

npm run dev
Le frontend sera accessible sur : http://localhost:5173

## Endpoints disponibles
Auth (public)

POST /api/auth/register – inscription

POST /api/auth/login – connexion

## Produits (public)

GET /api/products – liste paginée

GET /api/products/{id} – détail produit

GET /api/products/search – recherche par nom/catégorie

## Admin (ADMIN uniquement)

CRUD /api/admin/products

GET /api/admin/users – liste des utilisateurs

PUT /api/admin/users/{id} – modifier rôle utilisateur

GET /api/admin/orders – voir toutes les commandes

## Utilisateur (authentifié)

GET /api/user/profile – profil utilisateur

GET /api/user/orders – historique commandes

POST /api/user/orders – passer une commande

## Problèmes connus
La gestion des tokens JWT ne fonctionne pas encore correctement pour le moment.

Même en créant un utilisateur avec le rôle ADMIN, l’accès aux endpoints /api/admin/** retourne un 403 Forbidden.

Il est possible de se connecter et de récupérer le token, mais l’authentification via ce token dans les requêtes reste bloquée.

## Notes
Les mots de passe sont cryptés avec BCrypt.

Les rôles doivent être pré-remplis dans la table role : USER et ADMIN.

Pour tester les endpoints, utiliser Postman ou Insomnia avec un header Authorization: Bearer <token>.