--A listing with the number of chorbies per country and city.
select c.coordinates.country,count(c) from Chorbi c group by c.coordinates.country;
select c.coordinates.city,count(c) from Chorbi c group by c.coordinates.country;

--The minimum, the maximum, and the average ages of the chorbies.
select avg(c.age) from Chorbi c
select max(c.age) from Chorbi c
select min(c.age) from Chorbi c

--The ratio of chorbies who have not registered a credit card or have registered an invalid credit card.
select count(c) from Chorbi c where c.creditcard = null or c.creditcard.year >

--The ratios of chorbies who search for “activities”, “friendship”, and “love”.
select count(c)/(select count(c) from Chorbi c) from Chorbi c where c.searchTemplate.relationship.value like 'activities'
select count(c)/(select count(c) from Chorbi c) from Chorbi c where c.searchTemplate.relationship.value like 'friendship'
select count(c)/(select count(c) from Chorbi c) from Chorbi c where c.searchTemplate.relationship.value like 'love'

--The list of chorbies, sorted by the number of likes they have got.
select cl.c from ChorbiLike cl group by cl.c order by count(cl) desc

--The minimum, the maximum, and the average number of likes per chorbi.
select count(cl)*1.0/(select count(c) from Chorbi) from ChorbiLike c
select count(cl) from ChorbiLike cl group by cl.chorbi order by count(cl) desc
select count(cl) from ChorbiLike cl group by cl.chorbi order by count(cl) asc

--The minimum, the maximum, and the average number of chirps that a chorbi receives from other chorbies.
select count(cp)*1.0/(select count(c) from Chorbi) from Chirp cp
select count(cp) from Chirp cp group by cp.chirped order by count(cp) desc
select count(cp) from Chirp cp group by cp.chirped order by count(cp) asc

--The minimum, the maximum, and the average number of chirps that a chorbi sends to other chorbies.
select count(cp)*1.0/(select count(c) from Chorbi) from Chirp cp
select count(cp) from Chirp cp group by cp.chirper order by count(cp) desc
select count(cp) from Chirp cp group by cp.chirper order by count(cp) asc

--The chorbies who have got more chirps.
--The chorbies who have sent more chirps.
