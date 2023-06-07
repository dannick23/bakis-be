-- SET
-- GLOBAL max_allowed_packet=16777216;
CREATE TABLE settings
(
    id                 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    default_iteration  INT NOT NULL,
    send_sms_to_client bit DEFAULT 0
);

CREATE TABLE systems
(
    id          INT                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    settings_id INT                 NOT NULL,
    name        VARCHAR(128) UNIQUE NOT NULL,
    description VARCHAR(512) NULL,
    about_us    TEXT NULL,
    address     VARCHAR(128)        NOT NULL,
    FOREIGN KEY (settings_id) REFERENCES settings (id)
);

CREATE TABLE users
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    system_id    INT NULL,
    description  VARCHAR(256) NULL,
    first_name   VARCHAR(64)  NOT NULL,
    last_name    VARCHAR(64)  NOT NULL,
    email        VARCHAR(64)  NOT NULL,
    password     VARCHAR(128) NOT NULL,
    authority    VARCHAR(32)  NOT NULL,
    phone_number VARCHAR(16)  NOT NULL,
    image        MEDIUMBLOB NULL,
    FOREIGN KEY (system_id) REFERENCES systems (id)
);

CREATE TABLE registration
(
    id                  INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id             INT         NOT NULL,
    first_name          VARCHAR(64) NOT NULL,
    last_name           VARCHAR(64) NOT NULL,
    client_phone_number VARCHAR(16) NULL,
    description         VARCHAR(512) NULL,
    time_from           DATETIME    NOT NULL,
    time_to             DATETIME    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE working_hours
(
    id          INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    system_id   INT      NOT NULL,
    day_of_week INT      NOT NULL,
    time_from   DATETIME NOT NULL,
    time_to     DATETIME NOT NULL,
    FOREIGN KEY (system_id) REFERENCES systems (id)
);

CREATE TABLE worker_shift
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NULL,
    time_from  DATETIME    NOT NULL,
    time_to    DATETIME    NOT NULL,
    shift_type VARCHAR(64) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE tokens
(
    id          INT                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id     int                 NOT NULL,
    token       VARCHAR(256) UNIQUE NOT NULL,
    revoked     bit                 NOT NULL,
    expired     bit                 NOT NULL,
    expiry_date DATETIME            NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE image_collage
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    system_id   INT          NOT NULL,
    name        VARCHAR(128) NULL,
    description VARCHAR(512) NULL,
    image       MEDIUMBLOB   NOT NULL,
    FOREIGN KEY (system_id) REFERENCES systems (id)
);

CREATE TABLE shift_change_request
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    INT         NOT NULL,
    time_from  DATETIME    NOT NULL,
    time_to    DATETIME    NOT NULL,
    shift_type VARCHAR(64) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);