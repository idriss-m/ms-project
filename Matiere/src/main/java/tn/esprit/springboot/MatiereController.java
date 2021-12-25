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

@RequestMapping(value = "/api/matiere")
@RestController
public class MatiereController {
	
	@Autowired
	MatiereService ms;
	
	@PostMapping("/add-matiere")
	@ResponseBody
	public Matiere addMatiere(@RequestBody Matiere m){
		return ms.addMatiere(m);
	}
	
	@PutMapping("edit-matiere/{id}")
	@ResponseBody
	public Matiere updateMatiere(@PathVariable("id") int id, @RequestBody Matiere m){
		return ms.updateMatiere(id, m);
	}
	
	@DeleteMapping("delete-matiere/{id}")
	@ResponseBody
	public void deleteMatiere(@PathVariable("id") int id) {
		ms.deleteMatiere(id);
		
	}
	
	@GetMapping("get-matiere/{id}")
	@ResponseBody
	public Matiere getMatiere(@PathVariable("id") int id){
		return ms.getMatiere(id);
	}
	
	@GetMapping("get-all-etudiant")
	@ResponseBody
	public List<Matiere> getAllEtudiants(){
		 return ms.getAllMatiere();
	}
}
