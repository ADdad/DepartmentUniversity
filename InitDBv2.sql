  DROP DATABASE IF EXISTS starostiuk_db;
  CREATE DATABASE starostiuk_db;
  USE starostiuk_db;

  DROP TABLE IF EXISTS sc_works_scientists;
  DROP TABLE IF EXISTS sc_works_sc_themes;
  DROP TABLE IF EXISTS sc_themes_scientists;
  DROP TABLE IF EXISTS science_themes;
  DROP TABLE IF EXISTS scientific_works;
  DROP TABLE IF EXISTS masters;
  DROP TABLE IF EXISTS teachers;
  DROP TABLE IF EXISTS postgraduates;
  DROP TABLE IF EXISTS scientists;
  DROP TABLE IF EXISTS cathedras;

  CREATE TABLE cathedras
  (
    id           varchar(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );


  INSERT INTO cathedras
    (id, name, phone_number)
  VALUES ('id1', 'Math', '322-533');
  INSERT INTO cathedras
    (id, name, phone_number)
  VALUES ('id2', 'Multimedia', '322-534');
  INSERT INTO cathedras
    (id, name, phone_number)
  VALUES ('id3', 'Informatics', '322-535');


  CREATE TABLE scientists
  (
    scientist_id VARCHAR(255) PRIMARY KEY,
    second_name  VARCHAR(255) NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    gender       VARCHAR(100) NOT NULL
  );

  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids2', 'Odiseev', '322-538', 'man');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids3', 'Mikituk', '32-538', 'man');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids4', 'Denisenko', '324-538', 'man');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids5', 'Simonenko', '325-538', 'man');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids6', 'Medinsky', '326-538', 'man');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids7', 'Losev', '327-538', 'woman');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids8', 'Makeev', '328-538', 'woman');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids9', 'Demin', '329-538', 'woman');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids10', 'Mykioda', '3223-538', 'woman');
  INSERT INTO scientists
    (scientist_id, second_name, phone_number, gender)
  VALUES ('ids11', 'Voytasik', '3221-538', 'woman');

  CREATE TABLE teachers
  (
    scientist_id VARCHAR(255) PRIMARY KEY,
    cathedra_id  VARCHAR(255) NOT NULL,
    position     VARCHAR(100) NOT NULL,
    degree       VARCHAR(100) NOT NULL,
    FOREIGN KEY (cathedra_id)
      REFERENCES cathedras (id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (scientist_id)
      REFERENCES scientists (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE
  );

  INSERT INTO teachers
  VALUES ('ids2', 'id1', 'Teacher', 'Doctor');
  INSERT INTO teachers
  VALUES ('ids3', 'id2', 'Chief', 'Candidate of science');
  INSERT INTO teachers
  VALUES ('ids4', 'id3', 'Teacher', 'Doctor');

  CREATE TABLE postgraduates
  (
    scientist_id           VARCHAR(255) PRIMARY KEY,
    cathedra_id            VARCHAR(255) NOT NULL,
    chief_id      VARCHAR(255) NULL,
    start_date             DATE         NOT NULL,
    end_date               DATE         NULL,
    thesis_theme           VARCHAR(300) NULL,
    thesis_protection_date DATE         NULL,
    FOREIGN KEY (cathedra_id)
      REFERENCES cathedras (id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (scientist_id)
      REFERENCES scientists (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (chief_id)
      REFERENCES teachers (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE
  );

  INSERT INTO postgraduates
  VALUES ('ids5', 'id1', 'ids2', '2018-01-09', NULL, 'Mobile developing', '2020-01-07');
  INSERT INTO postgraduates
  VALUES ('ids6', 'id2','ids3', '2018-02-04', '2019-11-25', 'Problems of software engineering', '2019-07-21');
  INSERT INTO postgraduates
  VALUES ('ids7', 'id3','ids4', '2017-04-18', NULL, 'Neural networks', '2020-03-22');


  CREATE TABLE masters
  (
    scientist_id  VARCHAR(255) PRIMARY KEY,
    cathedra_id   VARCHAR(255) NOT NULL,
    chief_id      VARCHAR(255) NULL,
    diploma_theme VARCHAR(255) NULL,
    start_date    DATE         NOT NULL,
    end_date      DATE         NULL,
    end_reason    VARCHAR(100) NULL,
    FOREIGN KEY (cathedra_id)
      REFERENCES cathedras (id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (scientist_id)
      REFERENCES scientists (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (chief_id)
      REFERENCES teachers (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE
  );

  INSERT INTO masters
  VALUES ('ids8', 'id1', 'ids2', 'Front-end developing', '2017-07-24', '2021-07-24', 'Family troubles');
  INSERT INTO masters
  VALUES ('ids9', 'id2', 'ids3', 'Back-end developing', '2019-07-24', '2021-02-22', 'Army');
  INSERT INTO masters
  VALUES ('ids10', 'id3', 'ids4', 'Full-stack developing', '2018-07-24', '2021-03-26', NULL);


  CREATE TABLE science_themes
  (
    id          VARCHAR(255) PRIMARY KEY,
    cathedra_id VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    customer    VARCHAR(255) NOT NULL,
    start_date  DATE         NOT NULL,
    end_date    DATE         NULL,
    FOREIGN KEY (cathedra_id)
      REFERENCES cathedras (id)
      ON DELETE CASCADE ON UPDATE CASCADE
  );

  INSERT INTO science_themes
  VALUES ('idst1', 'id1', 'Ontological systems', 'IBM', '2018-11-04', '2021-03-27');
  INSERT INTO science_themes
  VALUES ('idst2', 'id2', 'Quantum systems', 'Microsoft', '2013-04-24', NULL);
  INSERT INTO science_themes
  VALUES ('idst3', 'id3', 'Linear math', 'Apple', '2018-01-04', NULL);

  CREATE TABLE scientific_works
  (
    id          VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    work_type   VARCHAR(255) NOT NULL,
    year_of_job INT          NOT NULL,
    PRIMARY KEY (id)
  );

  INSERT INTO scientific_works
  VALUES ('idsw1', 'Javascript Frameworks', 'Monography', 2020);
  INSERT INTO scientific_works
  VALUES ('idsw2', 'Java Frameworks', 'Article', 2020);
  INSERT INTO scientific_works
  VALUES ('idsw3', 'C++ Frameworks', 'Book', 2008);

  CREATE TABLE sc_works_scientists
  (
    work_id   VARCHAR(255) NOT NULL,
    author_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (author_id)
      REFERENCES scientists (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (work_id) REFERENCES scientific_works (id)
  );

  INSERT INTO sc_works_scientists
  VALUES ('idsw1', 'ids10');
  INSERT INTO sc_works_scientists
  VALUES ('idsw2', 'ids9');
  INSERT INTO sc_works_scientists
  VALUES ('idsw3', 'ids5');

  CREATE TABLE sc_works_sc_themes
  (
    work_id  VARCHAR(255) NOT NULL,
    theme_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (work_id) REFERENCES scientific_works (id),
    FOREIGN KEY (theme_id) REFERENCES science_themes (id)
  );

  INSERT INTO sc_works_sc_themes
  VALUES ('idsw1', 'idst1');
  INSERT INTO sc_works_sc_themes
  VALUES ('idsw2', 'idst2');
  INSERT INTO sc_works_sc_themes
  VALUES ('idsw3', 'idst3');

  CREATE TABLE sc_themes_scientists
  (
    id               VARCHAR(255) NOT NULL,
    science_theme_id VARCHAR(255) NOT NULL,
    worker_id        VARCHAR(255) NOT NULL,
    name             VARCHAR(255) NOT NULL,
    start_date       DATE         NOT NULL,
    end_date         DATE         NULL,
    FOREIGN KEY (science_theme_id)
      REFERENCES science_themes (id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (worker_id)
      REFERENCES scientists (scientist_id)
      ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id)
  );

  INSERT INTO sc_themes_scientists
  VALUES ('idj1', 'idst1', 'ids8', 'Job1', '2018-01-02', NULL);
  INSERT INTO sc_themes_scientists
  VALUES ('idj2', 'idst2', 'ids5', 'Job1', '2019-11-07', NULL);
  INSERT INTO sc_themes_scientists
  VALUES ('idj3', 'idst3', 'ids6', 'Job1', '2020-04-22', NULL);