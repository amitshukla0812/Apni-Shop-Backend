/**
 * Migration script: imports your old json-server data (db.json / data.json)
 * into the new Spring Boot + MySQL backend.
 *
 * Usage:
 *   1. Make sure the Spring Boot backend is running on http://localhost:8000
 *   2. node migrate.js path/to/your-data.json
 *      (if no path given, it looks for ./data.json in the same folder)
 *
 * How it works:
 *   - Reads every collection (maincategory, product, user, cart, etc.)
 *   - POSTs each record as-is to the matching endpoint, e.g. POST /product
 *   - The backend keeps the SAME id that was in your old data (it only
 *     generates a new id if none is sent), so all existing references
 *     between collections (cart.product, checkout.user, etc.) keep working.
 *   - Order doesn't matter - there are no strict foreign key constraints,
 *     so collections can be imported in any order.
 */

const fs = require("fs");
const path = require("path");

const BACKEND_URL = "http://localhost:8000";

// Order is just for nicer log output; not functionally required.
const COLLECTIONS = [
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
  "checkout",
  "testimonial",
];

async function importCollection(collectionName, records) {
  if (!Array.isArray(records)) {
    // "setting" might be a single object in some db.json exports
    records = [records];
  }

  let success = 0;
  let failed = 0;

  for (const record of records) {
    try {
      const res = await fetch(`${BACKEND_URL}/${collectionName}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(record),
      });

      if (res.ok) {
        success++;
      } else {
        failed++;
        const text = await res.text();
        console.error(
          `  FAILED (${collectionName}, id=${record.id}): ${res.status} ${text}`
        );
      }
    } catch (err) {
      failed++;
      console.error(`  ERROR (${collectionName}, id=${record.id}): ${err.message}`);
    }
  }

  console.log(`${collectionName}: ${success} imported, ${failed} failed`);
}

async function main() {
  const filePath = process.argv[2] || path.join(__dirname, "data.json");

  if (!fs.existsSync(filePath)) {
    console.error(`File not found: ${filePath}`);
    console.error("Usage: node migrate.js path/to/your-data.json");
    process.exit(1);
  }

  console.log(`Reading data from ${filePath} ...`);
  const raw = fs.readFileSync(filePath, "utf-8");
  const data = JSON.parse(raw);

  console.log(`Importing into backend at ${BACKEND_URL} ...\n`);

  for (const collectionName of COLLECTIONS) {
    if (data[collectionName] === undefined) {
      console.log(`${collectionName}: not found in file, skipping`);
      continue;
    }
    await importCollection(collectionName, data[collectionName]);
  }

  // Import any other top-level keys in the file that weren't in our list above
  const knownKeys = new Set(COLLECTIONS);
  for (const key of Object.keys(data)) {
    if (!knownKeys.has(key)) {
      console.log(`\nFound extra collection "${key}" not in the standard list, importing anyway...`);
      await importCollection(key, data[key]);
    }
  }

  console.log("\nDone! Check the logs above for any failures.");
}

main();
