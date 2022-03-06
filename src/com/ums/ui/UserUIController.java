package com.ums.ui;


import javax.swing.JOptionPane;

import com.ums.UMSApplication;
import com.ums.db.UMSDBException;
import com.ums.model.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Label;

public class UserUIController {

	@FXML
	private TableView<User> userTable;

	@FXML
	private TableColumn<User, String> nomColumn;
	@FXML
	private TableColumn<User, String> prenomColumn;
	

	@FXML
	private Label nomLabel;

	@FXML
	private Label prenomLabel;

	@FXML
	private Label telephoneLabel;
	
	@FXML
	private Label emailLabel;

	@FXML
	private Label loginLabel;

	@FXML
	private Label passwordLabel;
	
	@FXML
	private Label roleLabel;
	
	@FXML
	private TextField rechercherField;


	public UserUIController() {}

	@FXML
	private void initialize() {
		// Initialise la table des utilisateurs
		
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenom());

		// Add observable list data to the table
		userTable.setItems(UMSApplication.getInstance().getDataSource().getUsers());

		
		// Clear the user details form
		displayUserDetails(null);
		// Add a changeListener to the userTable
		addChangeListener();
		
		rechercher();
		
	}
	
	
	private void displayUserDetails(User user) {

		if (user != null) {
		// Fill the labels with info from the user object.
		this.nomLabel.setText(user.getNom().get());
		this.prenomLabel.setText(user.getPrenom().get());
		this.emailLabel.setText(user.getEmail().get());
		this.telephoneLabel.setText(user.getTelephone().get());
		this.loginLabel.setText(user.getLogin().get());
		this.passwordLabel.setText("********");
		this.roleLabel.setText(user.getRole().get());
		} else {
		// User is null, remove all the text.
		this.nomLabel.setText(null);
		this.prenomLabel.setText(null);
		this.emailLabel.setText(null);
		this.telephoneLabel.setText(null);
		this.loginLabel.setText(null);
		this.passwordLabel.setText(null);
		this.roleLabel.setText(null);
		     }
		}
	
	/**
	* Surveille les changements sur la table et affiche les informations dans le formulaire
	*/
	private void addChangeListener() {
	userTable.getSelectionModel().selectedItemProperty().addListener(

	        (observable, oldValue, newValue) -> displayUserDetails(newValue));

	}
	
	
	/**
	* Called when the user clicks on the Supprimer button.
	*/
	@FXML
	private void handleSupprimerUser() {
		//User selectedUser = userTable.getSelectionModel().getSelectedItem();
		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			try {
				//System.out.println(selectedUser.getId());
				//System.out.println(selectedUser.getNom().get()+ "  bien Supprimer");
				UMSApplication.getInstance().getDataSource().DeleteUser(selectedIndex);
				//UMSApplication.getInstance().getDataSource().DeleteUser(selectedUser.getId());
				//UMSApplication.getInstance().getDataSource().getUsers().remove(selectedIndex);
				
			
				
				
				
			} catch (UMSDBException e) {
				
				e.printStackTrace();
				
			};
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning ...");
			alert.setHeaderText("Alerte !");
			alert.setContentText("Veuillez sélectionner un utilisateur svp !");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNouveauUser() {
		User user = new User();
		boolean validerClicked = UMSApplication.getInstance().showUserEditUI(user);
		if (validerClicked) {
			try {
				UMSApplication.getInstance().getDataSource().AddUser(user);
			} catch (UMSDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	@FXML
	private void handleModifierUser() {
		
		User selectedUser = userTable.getSelectionModel().getSelectedItem();
		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
		
		if (selectedUser != null) {
			boolean validerClicked = UMSApplication.getInstance().showUserEditUI(selectedUser);
			if (validerClicked) {
				
				displayUserDetails(selectedUser);
					try {
						UMSApplication.getInstance().getDataSource().UpdateUser(selectedUser,selectedIndex);
					} catch (UMSDBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					userTable.refresh();
				
			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aucune sélection");
			alert.setHeaderText("Aucun utilisateur n'a été sélectionné !");
			alert.setContentText("Veuillez choisir un utilisateur svp !.");
			alert.showAndWait();
		}
	}
	
	
    private void rechercher() {
		
		FilteredList<User> filteredData = new FilteredList<>(UMSApplication.getInstance().getDataSource().getUsers(), b -> true);
		
		
	 rechercherField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getNom().get().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
				     return true; 
				}  
				if (Integer.valueOf(user.getId()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				return false;
				
			});
		});
		
		
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(userTable.comparatorProperty());
		
		
		userTable.setItems(sortedData);
               
	}

   @FXML
    private void handlerechercher() {
	
	//SimpleStringProperty id = new SimpleStringProperty(rechercherField.getText());
	 String response = JOptionPane.showInputDialog(null, "Veuillez saisir l'id l'utilisateur a rechercher :",
			 "Rechercher un utilisaleur ....",
			 JOptionPane.QUESTION_MESSAGE);
	 
	 
	 try {
		 Integer id = Integer.parseInt(response);
		try {
			User user = UMSApplication.getInstance().getDataSource().ReadUser(id);

			if(user == null) {
				JOptionPane.showMessageDialog(null, "L' utilisateur rechercher n. existe pas !",
						"Rechercher un utilisaleur ....",
						JOptionPane.INFORMATION_MESSAGE);
				
				
			}
			else {
				UMSApplication.getInstance().showUserEditUI(user);			
			}
		} catch (UMSDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "Veuillez saisir nombre entier svp !",
				e.getClass().toString(),
				JOptionPane.ERROR_MESSAGE);
	}; 

	
	
}


}
