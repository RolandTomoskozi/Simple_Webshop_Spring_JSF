create sequence if not exists hibernate_sequence start with 1000;

create table kunde
(
    id IDENTITY NOT NULL CONSTRAINT kunde_pk PRIMARY KEY,
    name varchar(200) not null,
    kundennummer varchar(36) not null
)

create table bestellung
(
    id IDENTITY NOT NULL CONSTRAINT bestellung_pk PRIMARY KEY,
    bestellnummer varchar (36) not null,
    kunde_id integer,
    datum date,
    bezeichnung varchar (200)
);

create table produkt
(
    id IDENTITY NOT NULL CONSTRAINT produkt_pk PRIMARY KEY,
    bestellnummer varchar (100) not null,
    artikelnummer varchar(36) not null,
    bestellung_id integer,
    position integer,
    bezeichnung varchar (200),
    preis integer
);

alter table produkt
    add constraint produkt_bestellung_id foreign key(bestellung_id) references bestellung(id);

alter table bestellung
    add constraint bestellung_kunde_id foreign key(kunde_id) references kunde(id);

alter table bestellung
    add constraint un_bestellnummer UNIQUE (bestellnummer)