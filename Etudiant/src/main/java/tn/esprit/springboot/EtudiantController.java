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

@RequestMapping(value = "/api/etudiant")
@RestController
public class EtudiantController {

	@Autowired
	EtudiantService es;
	
	@PostMapping("/add-etudiant")
	@ResponseBody
	public Etudiant addEtudiant(@RequestBody Etudiant e){
		return es.addEtudiant(e);
	}
	
	@PutMapping("edit-etudiant/{id}")
	@ResponseBody
	public Etudiant updateEtudiant(@PathVariable("id") int id, @RequestBody Etudiant e){
		return es.updateEtudiant(id, e);
	}
	
	@DeleteMapping("delete-etudiant/{id}")
	@ResponseBody
	public void deleteEtudiant(@PathVariable("id") int id) {
		es.deleteEtudiant(id);
		
	}
	
	@GetMapping("get-etudiant/{id}")
	@ResponseBody
	public Etudiant getEtudiant(@PathVariable("id") int id){
		return es.getEtudiant(id);
	}
	
	@GetMapping("get-all-etudiant")
	@ResponseBody
	public List<Etudiant> getAllEtudiants(){
		 return es.getAllEtudiant();
	}
}
