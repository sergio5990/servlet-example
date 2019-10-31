CREATE TABLE auth_user
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    login varchar(255),
    password varchar(255),
    role varchar(45),
    user_id bigint
);
create table user
(
  id         bigint auto_increment
    primary key,
  first_name varchar(255) null,
  lastName   varchar(255) null,
  phone      varchar(63)  null,
  email      varchar(63)  null
);