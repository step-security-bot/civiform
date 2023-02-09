# --- !Ups

<<<<<<< HEAD
alter table applications add column if not exists submitted_by_trusted_intermediary boolean;

# --- !Downs

alter table applications drop column if exists submitted_by_trusted_intermediary;
=======
alter table programs add column program_type varchar default 'default';

# --- !Downs

alter table programs drop column if exists program_type;
>>>>>>> 9b3bf93b (Add a new ProgramType enum to Programs. (#4187))
