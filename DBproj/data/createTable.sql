create table person_basics(
    personId varchar(10),
    primaryName varchar(150),
    birthYear numeric(4, 0),
    deathYear numeric(4, 0),
    primary key(personId)
);

create table title_basics(
    titleId varchar(10),
    titleType varchar(20) not null,
    primaryTitle varchar(500) not null,
    originalTitle varchar(500) not null,
    isAdult numeric(1, 0) not null,
    startYear numeric(4, 0),
    endYear numeric(4, 0),
    runtimeMinutes numeric(5, 0),
    primary key(titleId)
);

create table title_akas(
    titleId varchar(10) not null,
    ordering numeric(5, 0) not null,
    title varchar(500) not null,
    region varchar(10),
    language varchar(10),
    types varchar(100),
    attributes varchar(100),
    isOriginalTitle numeric(1, 0) not null,
    primary key(titleId, ordering)
);

create table title_genres(
    titleId varchar(10) not null,
    genres varchar(20) not null,
    primary key(titleId, genres)
);

create table title_directors(
    titleId varchar(10) not null,
    directors varchar(10) not null,
    primary key(titleId, directors)
);

create table title_writers(
    titleId varchar(10) not null,
    writers varchar(10) not null,
    primary key(titleId, writers)
);

create table title_episode(
    titleId varchar(10),
    parentTitleId varchar(10),
    seasonNumber numeric(4, 0),
    episodeNumber numeric(6, 0),
    primary key(titleId)
);

create table title_principals(
    titleId varchar(10) not null,
    ordering numeric(5, 0) not null,
    personId varchar(10) not null,
    category varchar(20),
    job varchar(500),
    characters varchar(500),
    primary key(titleId, ordering, personId)
);

create table title_ratings(
    titleId varchar(10),
    averageRating numeric(3, 1),
    numVotes numeric(10, 0),
    primary key(titleId)
);

create table person_primaryProfession(
    personId varchar(10) not null,
    primaryProfession varchar(100) not null,
    primary key(personId, primaryProfession)
);

create table person_knownForTitles(
    personId varchar(10) not null,
    knownForTitles varchar(10) not null,
    primary key(personId, knownForTitles)
);
