# 🚀 WS-Discord Backend
**Une API RESTful robuste inspirée de Discord, conçue avec Jakarta EE 10.**
[Lien vers le frontend Angular](https://github.com/Blova-TB/WS4A_Client_Discord)
Ce projet est un backend complet (packaging WAR) permettant de gérer une plateforme de messagerie instantanée : serveurs, salons, messages en temps réel, réactions et conversations privées. Il met en avant une architecture logicielle propre et l'utilisation des dernières normes Java.

---

## 🛠  Stack
*   **Core Engine :** **Java 21** (LTS) & **Jakarta EE 10** (Servlets 6.0, JPA 3.0).
*   **Persistance de données :** **Hibernate 6.3** & **PostgreSQL**. Maîtrise des transactions (JTA), des relations complexes et de l'optimisation des requêtes.
*   **Sécurité :** Système d'authentification hybride (Basic Auth pour l'échange de Token Bearer) avec gestion de session sécurisée en mémoire.
*   **Architecture :** Architecture en couches (Controller / Service / DAO), utilisation rigoureuse de **DTOs & Mappers**, et validation via **Hibernate Validator**.
*   **Interopérabilité :** API JSON (Jackson) avec support complet des **CORS** pour une intégration fluide avec des front-ends modernes (Angular/React).

---

## 🏗 Architecture du Projet
Nous avons opté pour une séparation stricte des préoccupations afin de garantir la maintenabilité :
*   **Couche Web (API) :** Servlets Jakarta pour un contrôle granulaire du protocole HTTP.
*   **Couche Métier :** Services dédiés à la logique applicative et à la validation.
*   **Couche Data :** Pattern DAO avec JPA pour une abstraction totale de la base de données.
*   **Sécurité :** Filtres d'interception pour la validation de tokens et la protection des routes.

---

## 🚀 Déploiement Rapide

### 1. Préparation de la Base de Données (PostgreSQL)
1. Créez une base nommée `discord_ws`.
2. Exécutez les scripts dans cet ordre (présents dans `src/main/resources/`) :
    *   `create_db_user.sql` (Crée l'utilisateur `discord_app`)
    *   `create_tables.sql` (Structure JPA)
    *   `init_data.sql` (Jeu de données de test)

### 2. Configuration
Vérifiez vos identifiants dans le fichier `src/main/resources/META-INF/persistence.xml`.

### 3. Build & Lancement
Utilisez le Maven Wrapper inclus pour compiler le projet :
```powershell
./mvnw.cmd clean package
```

Déployez le fichier .war généré dans target/ sur un serveur Tomcat 10.1+.

### 📑 Aperçu de l'API
| Endpoint | Fonction | Auth | 
|---|---|---|
| POST /AuthServer/connect | Connexion & génération de Token | Basic |
| GET /user | Gestion des profils utilisateurs | Token |
| GET /subject | Gestion des serveurs (sujets) | Token |
| POST /message | Envoi de messages enrichis | Token |
| GET /privConv | Messagerie privée entre deux utilisateurs | Token |