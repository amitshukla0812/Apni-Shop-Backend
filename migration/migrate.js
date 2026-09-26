// migrate.js
//
// Purana json-server data (data.json / db.json) ko naye Spring Boot backend
// mein daalne ke liye.
//
// USAGE:
//   1. Pehle Spring Boot backend chalu karein (mvn spring-boot:run) - port 8000 par.
//   2. Is file ke bagal mein apna data.json (ya db.json) rakhein.
//   3. Terminal mein: node migrate.js
//
// Requires Node.js 18+ (built-in fetch use karta hai).

import fs from "fs";

const BACKEND = "https://apni-shop-backend-production.up.railway.app";
const DATA_FILE = "./data.json"; // apni file ka naam yahan badal sakte hain

// Migration order zaroori hai taaki dependent records (cart, wishlist,
// checkout, testimonial) se pehle unke referenced records (user, product)
// already ban chuke hon.
const COLLECTIONS_IN_ORDER = [
  "maincategory",
  "subcategory",
  "brand",
  "feature",
  "faq",
  "setting",
  "contactus",
  "newsletter",
  "user",
  "product",
  "cart",
  "wishlist",
  "testimonial",
  "checkout",
];

async function postRecord(collection, record) {
  const res = await fetch(`${BACKEND}/${collection}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(record),
  });

  if (!res.ok) {
    const text = await res.text();
    throw new Error(`  FAILED [${collection} / id=${record.id}]: ${res.status} ${text}`);
  }
  return res.json();
}

async function migrateCollection(collection, records) {
  if (!records || !records.length) {
    console.log(`- ${collection}: 0 records, skipping`);
    return;
  }

  let success = 0;
  let failed = 0;

  for (const record of records) {
    try {
      await postRecord(collection, record);
      success++;
    } catch (err) {
      failed++;
      console.error(err.message);
    }
  }

  console.log(`- ${collection}: ${success} migrated, ${failed} failed (of ${records.length})`);
}

async function main() {
  console.log(`Reading ${DATA_FILE} ...`);
  const raw = fs.readFileSync(DATA_FILE, "utf-8");
  const data = JSON.parse(raw);

  console.log(`Migrating into ${BACKEND} ...\n`);

  for (const collection of COLLECTIONS_IN_ORDER) {
    await migrateCollection(collection, data[collection]);
  }

  // Agar db.json mein koi aisi collection ho jo upar ki list mein nahi hai,
  // usko bhi migrate kar dete hain (order zaroori nahi agar independent ho).
  const known = new Set(COLLECTIONS_IN_ORDER);
  for (const key of Object.keys(data)) {
    if (!known.has(key)) {
      console.log(`\n(extra collection found: ${key})`);
      await migrateCollection(key, data[key]);
    }
  }

  console.log("\nDone! Apna React app (npm run dev) chalakar data check kar lein.");
}

main().catch((err) => {
  console.error("Migration failed:", err);
  process.exit(1);
});
