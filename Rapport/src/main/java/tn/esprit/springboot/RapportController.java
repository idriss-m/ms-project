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

@RequestMapping(value = "/api/rapport")
@RestController
public class RapportController {

	@Autowired
	RapportService rs;
	
	@PostMapping("/add-rapport")
	@ResponseBody
	public Rapport addRapport(@RequestBody Rapport r){
		return rs.addRapport(r);
	}
	
	@PutMapping("edit-rapport/{id}")
	@ResponseBody
	public Rapport updateRapport(@PathVariable("id") int id, @RequestBody Rapport r){
		return rs.updateRapport(id, r);
	}
	
	@DeleteMapping("delete-rapport/{id}")
	@ResponseBody
	public void deleteRapport(@PathVariable("id") int id) {
		rs.deleteRapport(id);
		
	}
	
	@GetMapping("get-rapport/{id}")
	@ResponseBody
	public Rapport getRapport(@PathVariable("id") int id){
		return rs.getRapport(id);
	}
	
	@GetMapping("get-all-rapport")
	@ResponseBody
	public List<Rapport> getAllRapports(){
		 return rs.getAllRapport();
	}
}
