-- Cleanup script for ProductQueryControllerIntegrationTest
-- Delete in order to respect foreign key constraints

DELETE FROM p_product_image;
DELETE FROM p_product;
DELETE FROM p_product_category;
DELETE FROM p_store_rating;
DELETE FROM p_store;
DELETE FROM p_user_refresh_token;
DELETE FROM p_user_audit;
DELETE FROM p_user_profile;
DELETE FROM p_user;
