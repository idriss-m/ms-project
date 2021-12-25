package tn.esprit.springboot;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatiereService {
	
	@Autowired
	MatiereRepository mr;
	
private static final Logger l = LogManager.getLogger(MatiereService.class);
	
	public Matiere addMatiere(Matiere m) {
		return mr.save(m);
	}
	
	public Matiere updateMatiere(int id, Matiere m) {
		if(mr.findById(id).isPresent()) {
			Matiere matiere = mr.findById(id).get();
			matiere.setNomMatiere(m.getNomMatiere());
			matiere.setNiveau(m.getNiveau());
			matiere.setVolumeHoraire(m.getVolumeHoraire());
			return mr.save(matiere);
		} else {
			l.error("Matiere with id {} is not found", m.getId());
			return null;
		}
	}
	
	public void deleteMatiere(int id) {
		mr.deleteById(id);
		l.info("Matiere with id {} has been deleted", id);
	}
	
	public List<Matiere> getAllMatiere(){
		return (List<Matiere>) mr.findAll();
	}
	
	public Matiere getMatiere(int id) {
		l.info("Getting Matiere with id {}", id);
		return mr.findById(id).orElse(null);
	}
	
}
