DELIMITER @
CREATE TRIGGER before_delete_competition BEFORE DELETE
ON competition FOR EACH ROW 
BEGIN
		DELETE FROM inscrire 
		WHERE inscrire.id_comp = OLD.id_comp;
END ;
@
DELIMITER ;
