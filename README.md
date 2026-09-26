# Apni Shop Backend (Spring Boot + MySQL)

Ye backend aapke React (Vite) project ke json-server (port **8000**, `db.json`)
ka **drop-in replacement** hai. Isi wajah se server port bhi **8000** hi rakha
gaya hai — aapke `.env` mein `VITE_APP_BACKEND_SERVER=http://localhost:8000`
ko **badalne ki zaroorat nahi** padegi.

## Aapke frontend se match kiya gaya structure

Aapke `Redux/Sagas/Services/*.jsx` files padhkar ye 14 collections /
endpoints banaye gaye hain, exactly wahi naam jo aapka `createRecord`,
`getRecord`, `updateRecord`, `deleteRecord` use kar rahe hain:

| Collection     | Endpoint          |
|----------------|-------------------|
| MainCategory   | `/maincategory`   |
| SubCategory    | `/subcategory`    |
| Brand          | `/brand`          |
| Product        | `/product`        |
| Feature        | `/feature`        |
| Faq            | `/faq`            |
| Setting        | `/setting`        |
| ContactUs      | `/contactus`      |
| Newsletter     | `/newsletter`     |
| User           | `/user`           |
| Cart           | `/cart`           |
| Wishlist       | `/wishlist`       |
| Checkout       | `/checkout`       |
| Testimonial    | `/testimonial`    |

Har endpoint pe standard REST methods hain: `GET /collection`,
`GET /collection/{id}`, `POST /collection`, `PUT /collection/{id}`,
`DELETE /collection/{id}` — bilkul json-server jaisa hi.

## Fields — aapke Admin forms se match

- **Product**: name, maincategory, subcategory, brand, color[], size[],
  basePrice, discount, finalPrice, stock, stockQuantity, pic[], status,
  description
- **MainCategory / SubCategory / Brand**: name, pic, status
- **Feature**: name, icon, shortDescription, status
- **Faq**: question, answer, status
- **Setting**: siteName, map1, map2, address, email, phone, whatsapp,
  twitter, facebook, linkdin, instagram, youtube, privacyPolicy,
  termsCondition, refundPolicy
- **ContactUs**: name, email, phone, subject, message, status, date
- **Newsletter**: email, status
- **User**: name, username, email, phone, role, password, address[]
  (name, email, phone, address, pin, city, state — same as aapke
  `Address.jsx` form)
- **Cart / Wishlist**: user, product, color, size, qty, total, name, brand,
  stockQuantity, price, pic (bilkul aapke `ProductPage.jsx` ke `addToCart`
  aur `addToWishlist` jaisa)
- **Checkout**: user, orderStatus, paymentMode, paymentStatus,
  deliveryAddress, subtotal, shipping, total, date, products[] (aapke
  `Cart.jsx` ke `placeOrder()` jaisa)
- **Testimonial**: user, product, username, pname, message, star, date

## Setup Steps

### 1. MySQL database banayein (ya khud ban jayega)
```sql
CREATE DATABASE apnishop_db;
```

### 2. `application.properties` update karein
`src/main/resources/application.properties` mein apna MySQL
username/password daalein:
```
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

### 3. Project run karein
```
mvn spring-boot:run
```
Ya IDE mein `BackendApplication.java` run karein. Pehli baar run hone par
Hibernate saari tables khud bana dega.

### 4. Frontend ke .env mein kuch badalna nahi hai
```
VITE_APP_BACKEND_SERVER = http://localhost:8000
```
Ye already sahi hai — bas apna React app (`npm run dev`) alag terminal mein
chalayein aur backend (`mvn spring-boot:run`) doosre terminal mein.

### 5. CORS
`config/CorsConfig.java` mein `http://localhost:5173` (aapka Vite dev
server) already allow kiya hua hai.

## Purana json-server data (db.json) import karna

Aapke `db.json`/`data.json` mein IDs strings hain (jaise `"8378"`,
`"6271"`), aur cart/checkout/wishlist/testimonial mein user aur product ka
reference bhi inhi string IDs se hai. Isliye ye backend bhi **String id**
use karta hai (MySQL ka auto-increment number nahi) — taaki purana data
import karte waqt saare references (kaun sa order kis user ka hai, kaunsa
review kis product ka hai) automatically sahi bane rahein, bina kisi
id-remapping ke.

**Import karne ke steps:**

1. Pehle backend start karein (`mvn spring-boot:run`), MySQL tables khud
   ban jayengi.
2. Project folder mein already `data.json` (aapka upload kiya hua data)
   rakha hua hai. Terminal mein project folder ke andar jaakar:
   ```
   node migrate.js
   ```
   (Node.js already installed hona chahiye — aapke React project mein bhi
   hai.)
3. Script har collection (maincategory, product, user, cart, checkout,
   etc.) ko backend ke respective endpoint par POST karega, aur console
   mein kitne records successfully import hue ye dikhayega.
4. Agar koi record fail ho (jaise duplicate email/username jo `unique`
   constraint todta hai), console mein uska error dikhega — baaki records
   par asar nahi padega.

Agar aap kabhi bhi koi doosra `db.json` import karna chahein, toh:
```
node migrate.js path/to/other-file.json
```

## Purana Data Migrate Karna (db.json / data.json se)

Aapki `data.json` already `migration/data.json` folder mein rakh di gayi hai,
aur ek ready-made script bhi di gayi hai:

1. Pehle backend chalayein (`mvn spring-boot:run`), taaki `localhost:8000`
   live ho.
2. Terminal mein `migration` folder ke andar jaayein:
   ```
   cd migration
   node migrate.js
   ```
   (Node.js 18+ chahiye — ye built-in `fetch` use karta hai.)
3. Script khud saari 14 collections ko sahi order mein migrate karega
   (pehle maincategory/brand/product/user jaise independent records, fir
   cart/wishlist/checkout/testimonial jo unse link hote hain) aur **purane
   json-server ids ko as-is preserve karega** — isliye product/user
   references automatically sahi rahenge, koi remapping ki zaroorat nahi.
4. Console mein har collection ke liye kitne records migrate hue, dikh
   jayega. Koi record fail ho toh uska error bhi print hoga.
5. Migration ke baad apna React app (`npm run dev`) chalakar check kar lein
   ki data sahi se dikh raha hai.

## Ab json-server Hatana

- Jahan bhi aap `json-server db.json --port 8000` (ya isi jaisi) command
  chalate the, ab wo band kar dein — ye ab zaroorat nahi.
- Agar `json-server` npm package kisi project mein `devDependencies` mein
  install hai, toh: `npm uninstall json-server`
- Purani `db.json` / `data.json` file ko safe jagah backup rakh lein (jab
  tak migration confirm na ho jaye ki sab sahi migrate hua), fir delete
  kar sakte hain.
- `.env` mein `VITE_APP_BACKEND_SERVER` already `http://localhost:8000` hai
  — kuch badalna nahi hai, bas ab wahi URL is naye Spring Boot backend ki
  taraf point karega.

## Important Notes / Trade-offs

- **id type**: `String` id use kiya hai (json-server jaisa hi), taaki purana
  data bina id-remapping ke import ho sake. Naya record create hone par
  (jab frontend id nahi bhejta), backend khud ek random string id generate
  kar deta hai.
- **Image upload**: aapke frontend mein abhi sirf `createRecord`
  (JSON-based) use ho raha hai (`createMultipartRecord` commented hai), toh
  ye backend bhi filhal sirf JSON accept karta hai — file ka naam (string
  path jaisे `"product/abc.jpg"`) hi store hota hai, actual image upload
  nahi. Agar real file upload chahiye (multipart/form-data), bataiye, main
  ek `/upload` endpoint bhi bana dunga jo image ko disk par save kare aur
  aapke `public/images` jaisi folder mein serve kare.
- **Password**: abhi plain text save ho raha hai (aapke current
  data/frontend flow jaisa hi). Production ke liye ise BCrypt se hash
  karna recommended hai — bata dijiye toh wo bhi add kar dunga.
- **DELETE response**: json-server jaisा hi, DELETE call par `{ "id": ... }`
  JSON return hota hai (khaali body nahi), kyunki aapka
  `Services/index.js` har response par `.json()` call karta hai.

## Project Structure
```
src/main/java/com/apnishop/backend/
 ├── BackendApplication.java
 ├── config/CorsConfig.java
 ├── entity/          (14 entities — Product, User, Cart, Checkout, etc.)
 ├── repository/       (Spring Data JPA repositories)
 └── controller/        (REST endpoints, ek-ek entity ke liye)
```
