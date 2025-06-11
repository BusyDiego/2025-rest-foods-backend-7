
INSERT INTO book (id, price, language, title)
VALUES
    ('d9971633-5bb9-4994-a36a-002c1208d396' , 30, 'English', 'Fortnite Guide'),
    ('a1e27f61-af93-4228-b778-c946908bb151',34,  'German', 'Minecraft Guide');

INSERT INTO review (id, num_stars, comment, is_verified, book_id)
VALUES
    ('26440a29-a357-4edb-a5a3-159aea8b0a86', 10, 'Excellent book, highly recommend!', true, 'd9971633-5bb9-4994-a36a-002c1208d396'),
    ('96b18a9b-2762-40d6-93e1-95e776f9b3b0', 9, 'Almost Perfect', true, 'a1e27f61-af93-4228-b778-c946908bb151');
