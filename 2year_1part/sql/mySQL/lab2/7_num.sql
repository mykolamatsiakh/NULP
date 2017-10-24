SELECT model, COUNT(model) AS Qty_model, AVG(price) AS Avg_price
FROM PC
GROUP BY model
HAVING AVG(price) < 800;