# Tasks

Work the way you would day to day: explore the code, run the application and the tests, ask whatever you need and
think out loud. We don't expect you to finish everything; we want to see how you work.

---

## BUG-142 — Cancellation does not return stock

**Reported by:** Operations team
**Priority:** high

> "When we cancel an order, the status changes to CANCELLED, but the product's stock stays decremented.
> We already have products showing as sold out on the site when they aren't."

**How to reproduce**

1. `GET /products/3` → note the stock.
2. Create an order with 1 unit of product 3.
3. `POST /orders/{id}/cancel` → the response shows `"status": "CANCELLED"`.
4. `GET /products/3` → the stock has **not** gone back to its original value.

**Expected:** fix the problem and make sure, with a test, that it doesn't come back.

---

## FEAT-87 — Duplicate orders when the app retries

**Requested by:** Mobile team
**Priority:** high

> "On a bad network, the app times out on `POST /orders` and tries again. Sometimes the first request was processed,
> so the customer ends up with two identical orders and the stock is decremented twice."

The Mobile team already sends (or can start sending) an `Idempotency-Key` header (a UUID generated in the app)
on every order creation attempt. We want the API to use this header to ensure that **a single purchase
intent results in a single order**.

**Part A — Planning (no code):** explain how you would solve this. API behavior, data modeling,
edge cases, what happens with concurrent requests, what you would leave for later.

**Part B — Implementation:** implement the minimal version you argued for, with tests.
