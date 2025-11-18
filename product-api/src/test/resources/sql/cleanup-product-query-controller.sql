-- Cleanup script for ProductQueryControllerIntegrationTest
-- Delete in order to respect foreign key constraints

DELETE FROM p_product_image;
DELETE FROM p_product;
DELETE FROM p_product_category;
DELETE FROM p_product_audit;
DELETE FROM p_ai_prompt_audit;
