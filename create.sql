DROP TABLE IF EXISTS personne;

CREATE TABLE personne
(id_candP int(5) NOT NULL,
nom varchar(25),
prenom varchar(25),
mail varchar(50),
constraint pk_pers primary key(id_candP)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS equipe;

CREATE TABLE equipe
(id_candE int(5) NOT NULL,
nom varchar(25),
constraint pk_equi primary key(id_candE)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS candidat;

CREATE TABLE candidat
(id_cand int(5) NOT NULL AUTO_INCREMENT,
datecreation varchar(25),
constraint pk_cand primary key(id_cand)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS competition;

CREATE TABLE competition
(id_comp int(5) NOT NULL AUTO_INCREMENT,
nom varchar(25),
datecloture date,
enEquipe boolean,
constraint pk_comp primary key(id_comp)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS appartenir;

CREATE TABLE appartenir
(id_candP int(5) NOT NULL,
id_candE int(5) NOT NULL,
constraint pk_appart primary key(id_candP, id_candE)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS inscrire;

CREATE TABLE inscrire
(id_cand int(5) NOT NULL,
id_comp int(5) NOT NULL,
constraint pk_inscr primary key(id_cand, id_comp)
)ENGINE=InnoDB;

ALTER TABLE personne
ADD constraint fk_pers_cand foreign key (id_candP) references candidat(id_cand);

ALTER TABLE equipe
ADD constraint fk_equi_cand foreign key (id_candE) references candidat(id_cand);

ALTER TABLE appartenir
ADD constraint fk_appart_pers foreign key(id_candP) references personne(id_candP);

ALTER TABLE appartenir
ADD constraint fk_appart_equi foreign key(id_candE) references equipe(id_candE);

ALTER TABLE inscrire
ADD constraint fk_inscr_cand foreign key(id_cand) references candidat(id_cand);

ALTER TABLE inscrire
ADD constraint fk_inscr_comp foreign key(id_comp) references competition(id_comp);
