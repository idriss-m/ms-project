package tn.esprit.springboot;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="classe")
public class Classe {
	
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	
	private long id;
	

	@Indexed(unique=true)
	@Field(value="nomClasse")
	private String nomClasse;
	

	@Field(value="niveau")
	private Niveau niveau;
	
	@Field(value="specialite")
	private Specialite specialite;

	public long getId() {
		return id;
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public Classe(long id, Niveau niveau, Specialite specialite) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.specialite = specialite;
	}

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
