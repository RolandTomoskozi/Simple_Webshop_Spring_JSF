create sequence if not exists hibernate_sequence start with 1000;

create table bestellung
(
    id IDENTITY NOT NULL CONSTRAINT bestellung_pk PRIMARY KEY,
    bestellnummer integer not null,
    datum date,
    bezeichnung varchar (200)
);

create table produkt
(
    id IDENTITY NOT NULL CONSTRAINT produkt_pk PRIMARY KEY,
    bestellnummer integer not null,
    bestellung_id integer,
    position integer,
    bezeichnung varchar (200),
    preis integer
);

alter table produkt
    add constraint produkt_bestellung_id foreign key(bestellung_id) references bestellung(id);

alter table bestellung
    add constraint un_bestellnummer UNIQUE (bestellnummer)