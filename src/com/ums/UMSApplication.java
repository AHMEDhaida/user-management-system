package com.ums;

import java.io.IOException;

import com.ums.db.UMSDBException;
import com.ums.model.User;
import com.ums.ui.UserEditUIController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class UMSApplication extends Application {
	private Stage primaryStage;
	private BorderPane mainUI;
	private AnchorPane userUI;
	private DataSource dataSource;
	private static UMSApplication instance;

	@Override
	public void start(Stage primaryStage) {
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("User Management system");
		try {
			dataSource = new DataSource();
			initRootLayout();
			showUserUI();
		} catch (UMSDBException e) {
			System.err.println(e.getMessage());
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	// Shows the user UI inside the root layout.
	public void showUserUI() {
		try {
			// Load userUI
			userUI = (AnchorPane) FXMLLoader.load(getClass().getResource("ui/Userui.fxml"));

			// Set userUI into the center of root layout.
			mainUI.setCenter(userUI);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Initializes the root layout.
	private void initRootLayout() {
		// TODO Auto-generated method stub
		try {
			// Load root layout from fxml file.
			mainUI = (BorderPane) FXMLLoader.load(getClass().getResource("ui/MainUI.fxml"));
			// Show the scene containing the root layout.
			Scene scene = new Scene(mainUI);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean showUserEditUI(User user) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(UMSApplication.class.getResource("ui/UserEditUI.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter/Modifier un utilisateur");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(new Scene(page));
			UserEditUIController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setUser(user); // Set the user into the controller.
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.isValiderClicked();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static UMSApplication getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
}
