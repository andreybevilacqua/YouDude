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
VALUES('e770850b-7b90-443f-8e73-f55d578057c50', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'video-30', 'subject 30', 37, 'EDUCATIONAL');

-- Playlist insertion
INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('f9d34239-ccb4-4ad7-9a4b-b9b6172a7ef1', '33d9b2b2-ad94-11ea-a64d-0242ac130004', 'a36574a1-f447-4ea5-af49-3293f3585475', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('f9d34239-ccb4-4ad7-9a4b-b9b6172a7ef1', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '8179f1cc-005a-4fcf-978b-998cdc112b5e', 'playlist-1');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('210fe12f-cc3f-419c-814c-9fe7f39480c8', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '', 'playlist-2');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('272b286f-9fbf-459b-91d2-344cf1cc1926', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '', 'playlist-3');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('46f9db23-aae5-475a-836f-f77af7d37c7c', '33d9b2b2-ad94-11ea-a64d-0242ac130004', '', 'playlist-4');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('d90d0f73-e52a-4fc3-9433-4021d833ff2e', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '', 'playlist-5');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('77ce9da4-c151-46f9-9c98-58f83e03871b', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '', 'playlist-6');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('d83a47d2-d908-44b3-82ca-5a6436e1d260', '4c4eb4e6-ad94-11ea-a64d-0242ac130004', '', 'playlist-7');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('b0f291a6-d283-4c09-bcaa-ca4d19fb9eaf', '50689150-ad94-11ea-a64d-0242ac130004', '', 'playlist-8');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('25ea8c96-9bce-4216-b32b-aae035172710', '50689150-ad94-11ea-a64d-0242ac130004', '', 'playlist-9');

INSERT INTO model.playlist(playlist_id, user_id, video_id, name)
VALUES ('1c812c2a-be8f-4ca8-a637-128873d35ad2', '50689150-ad94-11ea-a64d-0242ac130004', '', 'playlist-10');

