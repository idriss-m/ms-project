package tn.esprit.springboot;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EtudiantService {
	
	@Autowired
	private EtudiantRepository er;
	
	private static final Logger l = LogManager.getLogger(EtudiantService.class);
	
	public Etudiant addEtudiant(Etudiant e) {
		return er.save(e);
	}
	
	public Etudiant updateEtudiant(int id, Etudiant e) {
		if(er.findById(id).isPresent()) {
			Etudiant etudiant = er.findById(id).get();
			etudiant.setNom(e.getNom());
			etudiant.setPrenom(e.getPrenom());
			etudiant.setEmail(e.getEmail());
			etudiant.setIdClass(e.getIdClass());
			return er.save(etudiant);
		} else {
			l.error("Etudiant with id {} is not found", e.getId());
			return null;
		}
	}
	
	public void deleteEtudiant(int id) {
		er.deleteById(id);
		l.info("Etudiant with id {} has been deleted", id);
	}
	
	public List<Etudiant> getAllEtudiant(){
		return (List<Etudiant>) er.findAll();
	}
	
	public Etudiant getEtudiant(int id) {
		l.info("Getting Etudiant with id {}", id);
		return er.findById(id).orElse(null);
	}

}
