-- Cleanup all tables in correct order (respecting foreign key constraints)
-- Order: child tables first, parent tables last

-- Product related tables
DELETE FROM p_product_image;
DELETE FROM p_product;
DELETE FROM p_product_category;

-- Store related tables
DELETE FROM p_store_audit;
DELETE FROM p_store_rating;
DELETE FROM p_store;

-- User related tables
DELETE FROM p_user_refresh_token;
DELETE FROM p_user_audit;
DELETE FROM p_user_profile;
DELETE FROM p_user;
