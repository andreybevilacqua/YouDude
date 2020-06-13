-- User insertion
INSERT INTO model.user_youdude(user_id, name, creation_date)
values('33d9b2b2-ad94-11ea-a64d-0242ac130004', 'User 1', now());

INSERT INTO model.user_youdude(user_id, name, creation_date)
values('4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'User 2', now());

INSERT INTO model.user_youdude(user_id, name, creation_date)
values('50689150-ad94-11ea-a64d-0242ac130004', 'User 3', now());

-- Video insertion
INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('a36574a1-f447-4ea5-af49-3293f3585475', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-1', 'subject 1', 10, 'COMEDY');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('8179f1cc-005a-4fcf-978b-998cdc112b5e', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-2', 'subject 2', 15, 'EDUCATIONAL');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('e102d5f5-7643-47e2-8ce4-347a4eb5c262', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-3', 'subject 3', 20, 'VLOGS');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('3e6270e5-e518-4101-911b-648b1c5c8c36', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-4', 'subject 4', 25, 'UNBOXING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('81bbde73-cab2-4837-a290-c7f83c7d50e1', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-5', 'subject 5', 30, 'HOW_TO');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('373ad8cd-85a3-4392-8c7a-d036a05acf31', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-6', 'subject 6', 35, 'BEST_OF');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('d4939d55-3c5a-45e5-87a3-8a68b8b8dbf9', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'video-7', 'subject 7', 37, 'GAMING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('16c350bd-bf0e-4472-a952-866e243747e5', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'video-8', 'subject 8', 40, 'COMEDY');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('0c2ce364-e02b-40b8-95d4-4a5e9495bb64', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'video-9', 'subject 9', 44, 'EDUCATIONAL');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('185716e9-90b1-4c0a-b289-cd5ebf7dc0b2', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'video-10', 'subject 10', 39, 'UNBOXING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('739e2eed-0f22-4f18-8a4b-dc805b6cfbeb', '50689150-ad94-11ea-a64d-0242ac130004', 'video-11', 'subject 11', 7, 'HOW_TO');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('d2d9f6a4-401a-46d3-b031-779212fa2284', '50689150-ad94-11ea-a64d-0242ac130004', 'video-12', 'subject 12', 8, 'BEST_OF');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('2d8e3cab-98e1-417d-b032-f365aab043e2', '50689150-ad94-11ea-a64d-0242ac130004', 'video-13', 'subject 13', 37, 'GAMING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('275d90b0-1887-4acd-ae50-ae554a9b9a66', '50689150-ad94-11ea-a64d-0242ac130004', 'video-14', 'subject 14', 20, 'COMEDY');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('e5a82559-c9ad-4bc0-84e8-84f028a47294', '50689150-ad94-11ea-a64d-0242ac130004', 'video-15', 'subject 15', 58, 'BEST_OF');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('9672e641-20af-4b67-9ac5-bddcf9a2018c', '50689150-ad94-11ea-a64d-0242ac130004', 'video-16', 'subject 16', 13, 'UNBOXING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('216261e8-9880-4128-b69f-538b2f66dff9', '50689150-ad94-11ea-a64d-0242ac130004', 'video-17', 'subject 17', 11, 'BEST_OF');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('25745748-fa84-4b3e-9a17-ad4aab87d1dc', '50689150-ad94-11ea-a64d-0242ac130004', 'video-18', 'subject 18', 27, 'HOW_TO');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('fc2e9f5e-f240-4fca-9641-ac0d3819c869', '50689150-ad94-11ea-a64d-0242ac130004', 'video-19', 'subject 19', 51, 'EDUCATIONAL');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('35229118-196f-4009-80e8-918ca48882c0', '50689150-ad94-11ea-a64d-0242ac130004', 'video-20', 'subject 20', 90, 'COMEDY');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('9c010538-eb45-4f17-a0d6-cccdff44c518', '50689150-ad94-11ea-a64d-0242ac130004', 'video-21', 'subject 21', 31, 'GAMING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('428ee298-19c9-4711-8056-c2d3cfbdea7b', '50689150-ad94-11ea-a64d-0242ac130004', 'video-22', 'subject 22', 120, 'VLOGS');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('7c592aaf-2dd8-4b4e-b2cb-5929bd60deaa', '50689150-ad94-11ea-a64d-0242ac130004', 'video-23', 'subject 23', 250, 'VLOGS');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('f5f33f26-f5b0-45f8-a165-34df6dfa7d4e', '50689150-ad94-11ea-a64d-0242ac130004', 'video-24', 'subject 24', 15, 'EDUCATIONAL');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('82bbf16c-bded-4585-8b79-aa657cfcbbf9', '50689150-ad94-11ea-a64d-0242ac130004', 'video-25', 'subject 25', 68, 'UNBOXING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('edaacff9-ab60-457f-9d8f-6f500405ca72', '50689150-ad94-11ea-a64d-0242ac130004', 'video-26', 'subject 26', 37, 'UNBOXING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('3921fd27-cc40-462b-b89b-a8867dec6012', '50689150-ad94-11ea-a64d-0242ac130004', 'video-27', 'subject 27', 39, 'HOW_TO');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('5d77b407-557e-4b18-81d8-c4f64751f747', '50689150-ad94-11ea-a64d-0242ac130004', 'video-28', 'subject 28', 25, 'GAMING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('a5e47afc-8f0e-4de1-89ff-4466c5de3990', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-29', 'subject 29', 66, 'GAMING');

INSERT INTO model.video(video_id, user_id, name, subject, duration, category)
VALUES('0e309fe6-62ef-4286-b926-f5907f63238f', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-30', 'subject 30', 37, 'EDUCATIONAL');

-- Playlist insertion
INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('f9d34239-ccb4-4ad7-9a4b-b9b6172a7ef1', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'a36574a1-f447-4ea5-af49-3293f3585475', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('a9470c0d-2d48-4ece-9f98-44a4621952d9', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '8179f1cc-005a-4fcf-978b-998cdc112b5e', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('a8297703-bc85-4a40-9555-6bd5f8b02dea', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'e102d5f5-7643-47e2-8ce4-347a4eb5c262', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('24858670-2a86-42de-8eb8-0e5564052f23', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '3e6270e5-e518-4101-911b-648b1c5c8c36', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('09d0d594-6cd0-4efd-9004-404ab559eacb', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '81bbde73-cab2-4837-a290-c7f83c7d50e1', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('210fe12f-cc3f-419c-814c-9fe7f39480c8', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '373ad8cd-85a3-4392-8c7a-d036a05acf31', 'playlist-2');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('9b33c52a-c692-4de0-a1d6-69b29c00a8b3', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'd4939d55-3c5a-45e5-87a3-8a68b8b8dbf9', 'playlist-2');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('034c5a03-e7bf-484c-a70d-330433b423d1', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '16c350bd-bf0e-4472-a952-866e243747e5', 'playlist-2');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('b91f8f48-bdad-4478-b8de-51fb17f0a09e', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '0c2ce364-e02b-40b8-95d4-4a5e9495bb64', 'playlist-2');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('142ddf64-2acf-4d16-bc8b-c6db2266dfe2', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '185716e9-90b1-4c0a-b289-cd5ebf7dc0b2', 'playlist-3');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('568c065c-96df-43b3-9718-f74a446894ea', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '739e2eed-0f22-4f18-8a4b-dc805b6cfbeb', 'playlist-3');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('f0ae05fd-53be-4d6c-b950-f86cde344e95', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'd2d9f6a4-401a-46d3-b031-779212fa2284', 'playlist-4');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('1cfdfc75-aa27-41c1-8608-7ed4e10748d3', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '2d8e3cab-98e1-417d-b032-f365aab043e2', 'playlist-4');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('adcb098e-ad43-482f-96e2-4c0006751118', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '275d90b0-1887-4acd-ae50-ae554a9b9a66', 'playlist-4');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('46f9db23-aae5-475a-836f-f77af7d37c7c', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'e5a82559-c9ad-4bc0-84e8-84f028a47294', 'playlist-4');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('4ba1f875-0a6c-4c71-be9e-f5321d202964', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '9672e641-20af-4b67-9ac5-bddcf9a2018c', 'playlist-5');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('29a37f62-fdbf-48fe-aff6-41c3f67fbbc9', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '216261e8-9880-4128-b69f-538b2f66dff9', 'playlist-5');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('0482c782-137b-48f0-ac01-ab09e94d8660', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '25745748-fa84-4b3e-9a17-ad4aab87d1dc', 'playlist-5');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('eb7cdaa4-63c6-40b6-ac33-3eda04947483', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'fc2e9f5e-f240-4fca-9641-ac0d3819c869', 'playlist-5');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('77ce9da4-c151-46f9-9c98-58f83e03871b', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '35229118-196f-4009-80e8-918ca48882c0', 'playlist-6');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('144ec2e4-d120-4087-997b-201456f82a65', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '9c010538-eb45-4f17-a0d6-cccdff44c518', 'playlist-6');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('55d26252-1282-461c-8071-77d25b84e713', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '428ee298-19c9-4711-8056-c2d3cfbdea7b', 'playlist-6');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('200e524f-eebd-44b7-89cd-8ecbdd0c26dc', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '7c592aaf-2dd8-4b4e-b2cb-5929bd60deaa', 'playlist-6');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('c33e32e1-72e5-45f8-84a5-326796da41e0', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'f5f33f26-f5b0-45f8-a165-34df6dfa7d4e', 'playlist-6');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('ad1318ee-1df7-48b9-bd62-e5e805a295c7', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '82bbf16c-bded-4585-8b79-aa657cfcbbf9', 'playlist-7');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('9034314c-167e-4f3e-b5ea-12fb3f093876', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'edaacff9-ab60-457f-9d8f-6f500405ca72', 'playlist-7');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('d83a47d2-d908-44b3-82ca-5a6436e1d260', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '3921fd27-cc40-462b-b89b-a8867dec6012', 'playlist-7');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('b0f291a6-d283-4c09-bcaa-ca4d19fb9eaf', '50689150-ad94-11ea-a64d-0242ac130004', '5d77b407-557e-4b18-81d8-c4f64751f747', 'playlist-8');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('d44538c0-c3c3-47f5-81aa-cf0778fd2ae4', '50689150-ad94-11ea-a64d-0242ac130004', 'a5e47afc-8f0e-4de1-89ff-4466c5de3990', 'playlist-8');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('52257ac5-feec-49b4-b4d9-fcbde2a8cf1a', '50689150-ad94-11ea-a64d-0242ac130004', '0e309fe6-62ef-4286-b926-f5907f63238f', 'playlist-8');


