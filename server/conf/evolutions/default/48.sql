# --- !Ups

alter table applications add column if not exists submitted_by_trusted_intermediary boolean;

# --- !Downs

alter table applications drop column if exists submitted_by_trusted_intermediary;
