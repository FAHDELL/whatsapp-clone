# 💬 WhatsApp Clone

Une application de messagerie en temps réel inspirée de WhatsApp, développée avec **Spring Boot** (Backend) et **Angular** (Frontend).

---

## 🚀 Fonctionnalités

- 🔐 Authentification des utilisateurs (JWT / Keycloak)
- 💬 Envoi et réception de messages en temps réel
- 👥 Gestion des utilisateurs
- 📁 Envoi de fichiers (images, documents…)
- 🔔 Notifications
- 📜 Historique des conversations
- 🟢 Statut en ligne / hors ligne

---

## 🛠️ Technologies utilisées

### Backend
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- PostgreSQL
- WebSocket (pour le temps réel)

### Frontend
- Angular
- TypeScript
- Bootstrap / CSS

### Autres outils
- Docker (optionnel)
- Git & GitHub
- Postman

---

## 📂 Structure du projet

```
whatsapp-clone/
│
├── whatsapp-clone     # Backend (Spring Boot)
├── whatsapp-clone-ui      # Frontend (Angular)
└── README.md
```

---

## ⚙️ Installation et lancement

### 1️⃣ Cloner le projet

```bash
git clone https://github.com/FAHDELL/whatsapp-clone.git
cd whatsapp-clone
```

---
### 2️⃣ up docker compose.yml

''' docker compose up -d '''


### 3️⃣ Backend (Spring Boot)

```bash
cd whatsapp-clone-api
```

Configurer `application.yml` :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/whatsapp_clone
spring.datasource.username=postgres
spring.datasource.password=your_password
```

Lancer l’application :

```bash
mvn spring-boot:run
```

---

### 3️⃣ Frontend (Angular)

```bash
cd whatsapp-clone-ui
npm install
ng serve
```

Accéder à l’application :

```
http://localhost:4200
```

---

## 🔐 Authentification

 Keycloak (OAuth2)
 
---

## 📸 Screenshots
<img width="1903" height="1010" alt="Capture d&#39;écran 2026-04-15 171227" src="https://github.com/user-attachments/assets/11a51d5e-9fbb-464d-bb97-88a7bad90ee6" />

<img width="1902" height="1021" alt="Capture d&#39;écran 2026-04-15 171401" src="https://github.com/user-attachments/assets/8f978295-8426-4d00-a610-be9a937f972c" />

---

## 📌 API Endpoints (exemple)

| Méthode | Endpoint            | Description              |
|--------|--------------------|--------------------------|
| POST   | /auth/login        | Authentification         |
| GET    | /users             | Liste des utilisateurs   |
| POST   | /messages          | Envoyer un message       |

---

## 🧠 Concepts clés utilisés

- Communication temps réel avec WebSocket
- Architecture REST
- Sécurité avec Spring Security
- Communication Angular ↔ Spring Boot
- Gestion des relations JPA (User, Chat, Message)

---

## 🤝 Contribution

Les contributions sont les bienvenues !

1. Fork le projet
2. Créer une branche (`feature/ma-feature`)
3. Commit tes changements
4. Push et créer une Pull Request

---

## 📄 Licence

Ce projet est open-source.

---

## 👨‍💻 Auteur

**Fahd Ellahia**

- GitHub: https://github.com/FAHDELL

---

## ⭐ Support

Si tu aimes ce projet, n'oublie pas de mettre une ⭐ sur GitHub !
