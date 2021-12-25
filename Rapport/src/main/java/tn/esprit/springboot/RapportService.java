package tn.esprit.springboot;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RapportService {
	
	@Autowired
	private RapportRepository rr;
	
	private static final Logger l = LogManager.getLogger(RapportService.class);
	
	public Rapport addRapport(Rapport r) {
		return rr.save(r);
	}
	
	public Rapport updateRapport(int id, Rapport r) {
		if(rr.findById(id).isPresent()) {
			Rapport rapport = rr.findById(id).get();
			rapport.setIdMatiere(r.getIdMatiere());
			rapport.setIdEtudiant(r.getIdEtudiant());
			rapport.setIdClass(r.getIdClass());
			rapport.setNoteMatiere(r.getNoteMatiere());
			return rr.save(rapport);
		} else {
			l.error("Rapport with id {} is not found", r.getId());
			return null;
		}
	}
	
	public void deleteRapport(int id) {
		rr.deleteById(id);
		l.info("Rapport with id {} has been deleted", id);
	}
	
	public List<Rapport> getAllRapport(){
		return (List<Rapport>) rr.findAll();
	}
	
	public Rapport getRapport(int id) {
		l.info("Getting Rapport with id {}", id);
		return rr.findById(id).orElse(null);
	}

}
