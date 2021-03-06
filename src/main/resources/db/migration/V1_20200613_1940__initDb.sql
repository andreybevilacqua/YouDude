CREATE TABLE IF NOT EXISTS model.user_youdude (
    user_id UUID NOT NULL,
    name character varying(255) NOT NULL,
    creation_date timestamp WITH TIME ZONE NOT NULL,
    CONSTRAINT user_id_pkey PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS model.playlist (
    playlist_id UUID NOT NULL,
    user_id UUID REFERENCES model.user_youdude(user_id) NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT playlist_id_pkey PRIMARY KEY (playlist_id),
    UNIQUE (playlist_id, user_id)
);

CREATE TABLE IF NOT EXISTS model.video (
    video_id UUID NOT NULL,
    user_id UUID REFERENCES model.user_youdude (user_id) NOT NULL,
    playlist_id UUID REFERENCES model.playlist (playlist_id),
    name character varying(255) NOT NULL,
    subject character varying(255) NOT NULL,
    duration int NOT NULL,
    category character varying(255) NOT NULL,
    CONSTRAINT video_id_pkey PRIMARY KEY (video_id)
);
