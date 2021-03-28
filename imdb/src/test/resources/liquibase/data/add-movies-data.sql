insert into title_basics(tconst, title_type, primary_title, original_title, is_adult, start_year, genres)
values ('t155', 'movie', 'fast', 'fast', false , 2000, 'Drama');

insert into title_basics(tconst, title_type, primary_title, original_title, is_adult, start_year, genres)
values ('t156', 'movie', 'fast', 'fast', false , 2005, 'Sport');

insert into title_basics(tconst, title_type, primary_title, original_title, is_adult, start_year, genres)
values ('t157', 'movie', 'test', 'test2', false , 1994, 'Sport');

insert into title_ratings(tconst, average_rating, num_votes) values ('t155', 10, 5);

insert into title_ratings(tconst, average_rating, num_votes) values ('t156', 9.5, 5);

insert into title_ratings(tconst, average_rating, num_votes) values ('t157', 9.5, 6);

insert into title_crew(tconst, directors, writers) values ('t155', 'n11', 'n12,n13');

insert into title_crew(tconst, directors, writers) values ('t156', 'n11', 'n12,n13');

insert into title_crew(tconst, directors, writers) values ('t157', 'n11,n12', 'n13');

insert into name_basics(nconst, primary_name, birth_year) values ('n11', 'name1', 1988);

insert into name_basics(nconst, primary_name, birth_year) values ('n12', 'name2', 1990);

insert into name_basics(nconst, primary_name, birth_year) values ('n13', 'name3', 1994);

insert into title_principals(tconst, ordering, nconst, category, job) values ('t155', 1, 'n11', 'category', 'job');

insert into title_principals(tconst, ordering, nconst, category, job) values ('t156', 2, 'n12', 'category', 'job');

insert into title_principals(tconst, ordering, nconst, category, job) values ('t157', 3, 'n13', 'category', 'job');
