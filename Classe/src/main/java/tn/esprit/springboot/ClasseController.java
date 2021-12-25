package tn.esprit.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/classe")
@RestController
public class ClasseController {

	@Autowired
	ClasseService cs;
	
	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping("/add-classe")
	@ResponseBody
	public Classe addEtudiant(@RequestBody Classe c){
		c.setId(sequenceGeneratorService.generateSequence(Classe.SEQUENCE_NAME));
		return cs.addClasse(c);
	}
	
	@PutMapping("edit-classe/{id}")
	@ResponseBody
	public Classe updateClasse(@PathVariable("id") long id, @RequestBody Classe e){
		return cs.updateEtudiant(id, e);
	}
	
	@DeleteMapping("delete-classe/{id}")
	@ResponseBody
	public void deleteClasse(@PathVariable("id") long id) {
		cs.deleteClasse(id);
		
	}
	
	@GetMapping("get-classe/{id}")
	@ResponseBody
	public Classe getClasse(@PathVariable("id") long id){
		return cs.getClasse(id);
	}
	
	@GetMapping("get-all-classe")
	@ResponseBody
	public List<Classe> getAllClasses(){
		 return cs.getAllClasse();
	}
}
