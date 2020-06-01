import java.io.File;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Stage window;
	private Scene channel1, channel2, channel3, channel4;
	private Media longMusic;
	private MediaPlayer musicPlayer;
	private int sceneWidth = 800;
	private int sceneHeight = 578;
	private int numPlatforms = 60;
	
	public void start(Stage stage) throws Exception {
		PlayerWorld world = new PlayerWorld();
		window = stage;
		stage.setTitle("Doodle King");
		stage.setWidth(800);
		stage.setHeight(600);
		
		//channel1: Title Scene
		BorderPane rootNode1 = new BorderPane();
		channel1 = new Scene(rootNode1);
		stage.setScene(channel1);
		Glow glow = new Glow();
		Text titleStuff = new Text("Doodle King");
		titleStuff.setEffect(glow);
		
		// music playing
		longMusic = new Media(new File("src/IndivProj/Music.mp3").toURI().toString()); 
		musicPlayer =  new MediaPlayer(longMusic);
		musicPlayer.setCycleCount(Integer.MAX_VALUE);
		
		Button startMusicBtn = new Button("Play Music");
		startMusicBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				musicPlayer.play();
			}
		});
		Button stopMusicBtn = new Button("Stop Music");
		stopMusicBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				musicPlayer.stop();
			}
		});
		
		RotateTransition rt = new RotateTransition();
		rt.setDuration(Duration.seconds(1));
		rt.setByAngle(360);
		rt.setNode(titleStuff);
		
		// title scene background
		Image img1 = new Image(getClass().getClassLoader().getResource("IndivProj/TitleBackground.png").toString());
		BackgroundImage im = new BackgroundImage(img1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(sceneWidth, sceneHeight, false, false, false, true));
		rootNode1.setBackground(new Background(im));		
		
		// adding to world
		titleStuff.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
		Text spacing = new Text("\n\n\n\n");
		Text spacing2 = new Text("\n\n\n\n\n\n\n\n\n\n\n\n");
		Text controls = new Text("Controls:");
		Text actualControls = new Text("Left Arrow: Move Left\nRight Arrow: Move Right\nUp Arrow: Shoot\nSpace: Jump (hold for larger jump)");
		VBox vBox1 = new VBox();
		Text goal = new Text("Goal: Get to your loved one without getting hit by a monster!");
		Button startButton = new Button("Start");
		controls.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		actualControls.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		goal.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				world.restart();
				window.setScene(channel2);
				
			}
			
		});
		vBox1.getChildren().addAll(titleStuff, goal, spacing2, controls, actualControls, startButton, startMusicBtn, stopMusicBtn);
		vBox1.setAlignment(Pos.CENTER);
		rootNode1.setCenter(vBox1);
		stage.show();
		rt.play();
		world.requestFocus();
		
		//channel2: Actual Game
		BorderPane rootNode2 = new BorderPane();
		rootNode2.setCenter(world);
		channel2 = new Scene(rootNode2);
		
		// creating actors
		Platform ground = new Platform();
		ground.setFitWidth(800);
		ground.setFitHeight(100);
		ground.setX(sceneWidth/2 - ground.getWidth()/2);
		ground.setY(sceneHeight - ground.getHeight());
		world.add(ground);
		
		Player p = new Player(5);
		p.setX(sceneWidth/2 - p.getWidth()/2);
		p.setY(sceneHeight - p.getHeight() - ground.getHeight());
		world.add(p); 
		
		// actual game background
		Image img2 = new Image(getClass().getClassLoader().getResource("IndivProj/GameBackground.png").toString());
		BackgroundImage im2 = new BackgroundImage(img2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(sceneWidth, sceneHeight, false, false, false, true));
		rootNode2.setBackground(new Background(im2));
		
		for(int i = 0; i < numPlatforms; i++) {
			if(i != numPlatforms - 1) {
				world.createNewPlatform(sceneWidth, sceneHeight, p.getWidth(), false);
			} else {
				world.createNewPlatform(sceneWidth, sceneHeight, p.getWidth(), true);
			}	
		}
		
		world.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				world.addKeyCode(event.getCode());
			}
			
		});
		
		world.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getCode().getName().equals("Up")) {
					p.setIsUp(false);
				}
				if(event.getCode().getName().equals("Space")) {
					p.setReleased(true);
				}
				world.removeKeyCode(event.getCode());
			}
		});
		
		world.gameOverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(world.getHasWon()) {
					window.setScene(channel4);
				} else {
					window.setScene(channel3);
				}
				
			}
		});
		
		//channel3: Lose Screen
		BorderPane rootNode3 = new BorderPane();
		channel3 = new Scene(rootNode3);
		Text spacing3 = new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		Text t = new Text("You Lose");
		VBox vBox3 = new VBox();
		Button restartButton = new Button("Restart");
		Button titleButton = new Button("Go Back to Main Menu");
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Courier", FontWeight.BOLD, 60));
		
		// lose scene background
		Image img3 = new Image(getClass().getClassLoader().getResource("IndivProj/LoseBackground.png").toString());
		BackgroundImage im3 = new BackgroundImage(img3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(sceneWidth, sceneHeight, false, false, false, true));
		rootNode3.setBackground(new Background(im3));	
		
		restartButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				world.restart();
				window.setScene(channel2);
				
			}
			
		});;
		titleButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				window.setScene(channel1);
				
			}
			
		});
		vBox3.getChildren().addAll(spacing, spacing3, t, restartButton, titleButton);
		vBox3.setAlignment(Pos.CENTER);
		rootNode3.setCenter(vBox3);
		
		//channel4: Win Screen
		BorderPane rootNode4 = new BorderPane();
		channel4 = new Scene(rootNode4);
		Text text = new Text("You Win!");
		VBox vBox4 = new VBox();
		Button rButton = new Button("Restart");
		Button tButton = new Button("Go Back to Main Menu");
		
		// win scene background
		Image img4 = new Image(getClass().getClassLoader().getResource("IndivProj/EndBackground.png").toString());
		BackgroundImage im4 = new BackgroundImage(img4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(sceneWidth, sceneHeight, false, false, false, true));
		rootNode4.setBackground(new Background(im4));	
		
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
		
		rButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				world.restart();
				window.setScene(channel2);
				
			}
			
		});;
		tButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				window.setScene(channel1);
				
			}
			
		});
		vBox4.getChildren().addAll(spacing, text, rButton, tButton);
		vBox4.setAlignment(Pos.CENTER);
		rootNode4.setCenter(vBox4);
					
		
		stage.show();

		world.requestFocus();
	}

}
