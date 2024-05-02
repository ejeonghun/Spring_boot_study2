INSERT INTO article(title, content) VALUES('aaaa','1111');
INSERT INTO article(title, content) VALUES('bbbb','2222');
INSERT INTO article(title, content) VALUES('cccc','3333');
INSERT INTO coffee(name, price, kcal) VALUES('아메리카노',4500, 100);
INSERT INTO coffee(name, price, kcal) VALUES('카페라떼', 5000, 500);

INSERT INTO article(title, content) VALUES('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?', '댓글 고고고');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim', '아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Choi', '쇼생크 탈출');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Choi', '초밥');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim', '유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Choi', '독서');

INSERT INTO pizza(id, name, price) VALUES(1,'페페로니 피자', 25000);
INSERT INTO pizza(id, name, price) VALUES(2,'불고기 피자',29900);
INSERT INTO pizza(id, name, price) VALUES(3,'고구마 피자', 30900);
INSERT INTO pizza(id, name, price) VALUES(4,'포테이토 피자', 27900);
INSERT INTO pizza(id, name, price) VALUES(5,'치트 피자', 23900);

INSERT INTO pizza_comment(id, body, nickname) VALUES(2, '너무 맛있어요', 'park');
INSERT INTO pizza_comment(id, body, nickname) VALUES(5, '가격대비 좋아요', 'kim');
INSERT INTO pizza_comment(id, body, nickname) VALUES(4, '감동', 'choi');
