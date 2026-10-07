insert into article(title, content) values ('가가가가','1111');
insert into article(title, content) values ('나나나나','2222');
insert into article(title, content) values ('다다다다','3333');

/*
 트러블 슈팅 -> 스프링부트는 data.sql을활용한 데이터 초기화를 권장하지 않기 떄문이다.
 */

 insert into article(id, title, content) values(4,'당신의 인생 영화는?','댓글로 남겨 주세요!');
insert into article(id, title, content) values(5,'당신의 소울 푸드는?','댓글로 남겨 주세요!!');
insert into article(id, title, content) values(6,'당신의 취미는?','댓글로 남겨 주세요!!!');

-- comment 더미 데이터
-- 4번 게시물
insert into comment(id,article_id,nickname,body) Values(1,4,'Park','어벤져스');
insert into comment(id,article_id,nickname,body) Values(2,4,'Kim','호프');
insert into comment(id,article_id,nickname,body) Values(3,4,'Lee','오디세이');
-- 5번 게시물
insert into comment(id,article_id,nickname,body) Values(4,5,'Park','김밥');
insert into comment(id,article_id,nickname,body) Values(5,5,'Kim','치킨');
insert into comment(id,article_id,nickname,body) Values(6,5,'Lee','초밥');
-- 6번 게시물
insert into comment(id,article_id,nickname,body) Values(7,6,'Park','조깅');
insert into comment(id,article_id,nickname,body) Values(8,6,'Kim','유튜브');
insert into comment(id,article_id,nickname,body) Values(9,6,'Lee','독서');