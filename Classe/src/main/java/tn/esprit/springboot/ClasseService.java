package tn.esprit.springboot;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClasseService {
	
	@Autowired
	private ClasseRepository cr;
	
	private static final Logger l = LogManager.getLogger(ClasseService.class);
	
	public Classe addClasse(Classe c) {
		return cr.insert(c);
	}
	
	public Classe updateEtudiant(long id, Classe c) {
		if(cr.findById(id).isPresent()) {
			Classe classe = cr.findById(id).get();
			classe.setNomClasse(c.getNomClasse());;
			classe.setNiveau(c.getNiveau());;
			classe.setSpecialite(c.getSpecialite());
			return cr.save(c);
		} else {
			l.error("Classe with id {} is not found", c.getId());
			return null;
		}
	}
	
	public void deleteClasse(long id) {
		cr.deleteById(id);
		l.info("Classe with id {} has been deleted", id);
	}
	
	public List<Classe> getAllClasse(){
		return cr.findAll();
	}
	
	public Classe getClasse(long id) {
		l.info("Getting Classe with id {}", id);
		return cr.findById(id).orElse(null);
	}

}
