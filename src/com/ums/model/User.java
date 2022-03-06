package com.ums.model;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty email;
	private StringProperty telephone;
	private StringProperty login;
	private StringProperty password;
	private StringProperty role;
	private int  id;
    
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User(int id, String nom, String prenom, String email, String telephone, String login, String password,
			String role) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.telephone = new SimpleStringProperty(telephone);
        this.password = new SimpleStringProperty(password);
        this.login = new SimpleStringProperty(prenom.trim().toLowerCase() + "." + nom.trim().toLowerCase());
        this.role = new SimpleStringProperty(role);
        
	}
	
	
    public User(String nom, String prenom, String email, String telephone, Role role) {
          this.nom = new SimpleStringProperty(nom);
          this.prenom = new SimpleStringProperty(prenom);
          this.email = new SimpleStringProperty(email);
          this.telephone = new SimpleStringProperty(telephone);
          
          // login et password par défaut ...
          this.login = new SimpleStringProperty(prenom.trim().toLowerCase() + "." + nom.trim().toLowerCase());
          
          this.password = new SimpleStringProperty("p@Ss3R");
          this.role = new SimpleStringProperty(role.getValue());
    }
    
    // générer ensuite les getters and setters 

    public User() {
    	this("", "", "", "", Role.SIMPLE_USER);
    	this.login = new SimpleStringProperty("");
    	this.password = new SimpleStringProperty("");
    	}

	
	public StringProperty getNom() {
		return nom;
	}

	public void setNom(StringProperty nom) {
		this.nom = nom;
	}

	public StringProperty getPrenom() {
		return prenom;
	}

	public void setPrenom(StringProperty prenom) {
		this.prenom = prenom;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getTelephone() {
		return telephone;
	}

	public void setTelephone(StringProperty telephone) {
		this.telephone = telephone;
	}

	public StringProperty getLogin() {
		return login;
	}

	public void setLogin(StringProperty login) {
		this.login = login;
	}

	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}

	public StringProperty getRole() {
		return role;
	}

	public void setRole(StringProperty role) {
		this.role = role;
	}
    
 
    
    
    
    

}