
create table users (
  id serial primary key,
  email text unique not null,
  password text not null
);

create table companies (
  id serial primary key,
  user_id int not null references users(id) on delete cascade,
  name text not null,
  industry text,
  description text,
  logo_url text
);

create table goods_and_services (
  id serial primary key,
  company_id int not null references companies(id) on delete cascade,
  name text not null
);

create table tenders (
  id serial primary key,
  company_id int not null references companies(id) on delete cascade,
  title text not null,
  description text,
  deadline date,
  budget int
);

create table applications (
  id serial primary key,
  tender_id int not null references tenders(id) on delete cascade,
  company_id int not null references companies(id) on delete cascade,
  proposal text
);
