DELIMITER @
CREATE TRIGGER before_delete_persequi BEFORE DELETE
ON candidat FOR EACH ROW 
BEGIN
		DELETE FROM inscrire 
		WHERE inscrire.id_cand = OLD.id_cand;

		DELETE FROM appartenir
		WHERE appartenir.id_candP = OLD.id_cand;

		DELETE FROM personne
		WHERE personne.id_candP = OLD.id_cand;

		DELETE FROM appartenir
		WHERE appartenir.id_candE = OLD.id_cand;

		DELETE FROM equipe
		WHERE equipe.id_candE = OLD.id_cand;
END ;
@
DELIMITER ;
