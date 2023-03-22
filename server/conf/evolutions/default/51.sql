# --- !Ups
create type singleton_enum as enum ('singleton');

create table if not exists settings (
  id bigserial primary key,
  settings jsonb not null,
  -- This unique column guarantees that no additional rows are added to the table.
  singleton singleton_enum not null default 'singleton'
);
