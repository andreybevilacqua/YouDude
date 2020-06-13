CREATE TABLE IF NOT EXISTS model.user_youdude (
    user_id UUID NOT NULL,
    name character varying(255) NOT NULL,
    creation_date timestamp WITH TIME ZONE NOT NULL,
    CONSTRAINT user_id_pkey PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS model.video (
    video_id UUID NOT NULL,
    user_id UUID NOT NULL REFERENCES model.user_youdude (user_id),
    name character varying(255) NOT NULL,
    subject character varying(255) NOT NULL,
    duration int NOT NULL,
    category character varying(255) NOT NULL,
    CONSTRAINT video_id_pkey PRIMARY KEY (video_id)
);

CREATE TABLE IF NOT EXISTS model.playlist (
    playlist_id UUID NOT NULL,
    user_id UUID REFERENCES model.user_youdude(user_id) NOT NULL,
    video_id UUID REFERENCES model.video(video_id) NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT playlist_id_pkey PRIMARY KEY (playlist_id)
);

INSERT INTO model.user_youdude(user_id, name, creation_date)
values('33d9b2b2-ad94-11ea-a64d-0242ac130004', 'User 1', now());

INSERT INTO model.user_youdude(user_id, name, creation_date)
values('4c4eb4e6-ad94-11ea-a64d-0242ac130004', 'User 2', now());

INSERT INTO model.user_youdude(user_id, name, creation_date)
values('50689150-ad94-11ea-a64d-0242ac130004', 'User 3', now());


