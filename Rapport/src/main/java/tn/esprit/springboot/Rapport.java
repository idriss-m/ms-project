package tn.esprit.springboot;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rapport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private int idEtudiant;
	
	@Column
	private int idClass;
	
	@Column
	private int idMatiere;
	
	@Column
	private float noteMatiere;

	public float getNoteMatiere() {
		return noteMatiere;
	}

	public void setNoteMatiere(float noteMatiere) {
		this.noteMatiere = noteMatiere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public int getIdClass() {
		return idClass;
	}

	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}

	public int getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}

	public Rapport(int id, int idEtudiant, int idClass, int idMatiere) {
		super();
		this.id = id;
		this.idEtudiant = idEtudiant;
		this.idClass = idClass;
		this.idMatiere = idMatiere;
	}

	public Rapport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rapport(int id, int idEtudiant, int idClass, int idMatiere, float noteMatiere) {
		super();
		this.id = id;
		this.idEtudiant = idEtudiant;
		this.idClass = idClass;
		this.idMatiere = idMatiere;
		this.noteMatiere = noteMatiere;
	}
	

}
