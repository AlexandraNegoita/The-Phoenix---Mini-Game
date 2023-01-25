
package ThePhoenixMiniGame;

import ThePhoenixMiniGame.Carte.Coordonees;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class FXMain extends Application {
    GridPane menuGrid = new GridPane();
    Scene scene = new Scene(menuGrid, 1280,720);
    
    private Pane sp=new Pane();
    Stage gameStage=new Stage();
    Personnage personnage= new Personnage();  
    
    AnchorPane interactionPane=new AnchorPane();
    Scene interactionScene=new Scene(interactionPane,1280,720);
    
    AnchorPane fightPane=new AnchorPane();
    Scene fightScene=new Scene(fightPane,1280,720);
    
    GridPane inventoryPane=new GridPane();
    Scene inventoryScene=new Scene(inventoryPane,1280,720);
    
    GridPane profile=new GridPane();
    Scene profileScene=new Scene(profile);
    
    GridPane equippedPane=new GridPane();
    Scene equippedScene=new Scene(equippedPane,1280,720);
    
    ScrollPane scroll = new ScrollPane();
    AnchorPane rootElement=new AnchorPane();
    Scene gameScene=new Scene(rootElement,1280,720);
    
    private HashMap<Carte.Coordonees,ImageView> lootImageMap=new HashMap<>();
    @Override
    public void start(Stage menuStage) {
        int mapWidth=1920;
        int mapLenght=1920;
        int tileNumberW=20;
        int tileNumberL=20;
        int tileSizeW=mapWidth/tileNumberW;
        int tileSizeL=mapLenght/tileNumberL;
        
        Text welcome=new Text("Welcome!");
        Label lblUsername=new Label("Username:");
        lblUsername.setMinSize(50, 70);
        lblUsername.setFont(Font.font("Tahoma",FontWeight.EXTRA_LIGHT, 20));
        TextField username=new TextField();
        
        username.setFont(Font.font("Tahoma",FontWeight.EXTRA_LIGHT, 20));
        welcome.setFont(Font.font("Tahoma",FontWeight.NORMAL, 50));
        Button btnStartGame = new Button();
        btnStartGame.setMinSize(350, 70);
        btnStartGame.setText("Play!");
        btnStartGame.setFont(Font.font("Tahoma",FontWeight.NORMAL, 30));
        btnStartGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                personnage=new Personnage();
                personnage.setName(username.getText());
                personnage.setAttributesHpArmAdApArmor(1000, 0, 50, 100);
                personnage.setHealth(1000);
                
                ArrayList<Terrain> terrain= new ArrayList();
                Terrain t1=new Terrain(1);
                terrain.add(t1);
                Terrain t2=new Terrain(2);
                terrain.add(t2);
                Terrain t3=new Terrain(3);
                terrain.add(t3);
                Terrain t0=new Terrain(0);
                
                ArrayList<PNJ> pnj=new ArrayList();
                PNJ p1=new PNJ("The fairy",1);
                p1.setInteraction1("Welcome to our city! \nIf you need me, you know where to find me.");
                p1.setInteraction2("I knew you would look for me, Hero! \nBut I still have faith you will succeed even without my help.");
                p1.setInteraction3("The one you're looking for has the face of a bird and the appearance of a man. He is really close to you.");
                pnj.add(p1);
                
                PNJ p2=new PNJ("The old lady",2);
                p2.setInteraction1("You're going somewhere but even you don't know where. \nCome back when you have found your true destination.");
                p2.setInteraction2("The answers you seek are wrapped in gold and flames.");
                p2.setInteraction3("I've been here for 12 decades. \nWere you to need advice, I'm here to light up the way to your victory.");
                pnj.add(p2);
                
                PNJ p3=new PNJ("The Professor",3);
                p3.setInteraction1("I am the man who watches over them. \nI know their every move.");
                p3.setInteraction2("For several months now, the situation here has changed. \nThey are acting different. We all need your help.");
                p3.setInteraction3("They sink and change their shape, drink poison and make curses, feed on blood. \nBut still after all of this, I see the Renaissance of Hope.");
                pnj.add(p3);
                
                ArrayList<Adversaire> adversaire=new ArrayList();
                Loot lootDrop1=new Loot("Pearl of the Sea","Precious gift from defeating the Mermaid",0,false,1,100,50,16,60);
                Loot lootDrop2=new Loot("Cursed strange potion","Suspicious gift from defeating the Witch",0,false,2,10,4,24,6);
                Loot lootDrop3=new Loot("Blood hungry bat","Powerful gift from defeating the Vampire",0,false,3,600,10,5,9);
                Loot lootDrop4=new Loot("Renaissance","Ultimate gift from defeating the Phoenix",0,false,4,1000,1000,1000,1000);
                Adversaire a1= new Adversaire("The Mermaid",3000,1500,150,1,lootDrop1); 
                Adversaire a2= new Adversaire("The Witch",1000,700,80,2,lootDrop2); 
                Adversaire a3= new Adversaire("The Vampire",5000,2000,200,3,lootDrop3); 
                Adversaire a4= new Adversaire("The Phoenix",9000,5000,600,4,lootDrop4); 
                adversaire.add(a1);
                adversaire.add(a2);
                adversaire.add(a3);
                adversaire.add(a4);
                
                Loot potion=new Loot("Health potion","Regenerates some life",0,true,0,100,0,0,0);
                potion.setAttributesHpArmAdAp(100, 0, 0, 0);
                
                int width = tileNumberW,lenght = tileNumberL;
                sp.getChildren().clear();
                HashMap<Carte.Coordonees,Object> map=new Carte().generateMap(terrain,pnj,adversaire,personnage,potion,width,lenght);
                for(Carte.Coordonees coord : map.keySet()){
                    if(map.get(coord) instanceof Terrain){
                        Image img=new Image(((Terrain)map.get(coord)).getImage());
                        ImageView imgView=new ImageView(img);
                        imgView.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView);
                    }
                    else if(map.get(coord) instanceof PNJ){
                        Image img1=new Image(t0.getImage());
                        ImageView imgView1=new ImageView(img1);
                        imgView1.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView1.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView1);
                        
                        Image img=new Image(((PNJ)map.get(coord)).getImg_map_path());
                        ImageView imgView=new ImageView(img);
                        imgView.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView);
                    }
                    else if(map.get(coord) instanceof Adversaire){
                        Image img1=new Image(t0.getImage());
                        ImageView imgView1=new ImageView(img1);
                        imgView1.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView1.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView1);
                        Image img=new Image(((Adversaire)map.get(coord)).getImg_map_path());
                        ImageView imgView=new ImageView(img);
                        imgView.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView);
                    }
                    else if(map.get(coord) instanceof Loot){
                        Image img1=new Image(t0.getImage());
                        ImageView imgView1=new ImageView(img1);
                        imgView1.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView1.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView1);
                        Image img=new Image(((Loot)map.get(coord)).getImage());
                        ImageView imgView=new ImageView(img);
                        imgView.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView.setLayoutY(coord.y*(mapWidth/width));
                        lootImageMap.put(coord, imgView);
                        sp.getChildren().addAll(imgView);
                    }
                    else if(map.get(coord)==null){
                        Image img=new Image(t0.getImage());
                        ImageView imgView=new ImageView(img);
                        imgView.setLayoutX(coord.x*(mapLenght/lenght));
                        imgView.setLayoutY(coord.y*(mapWidth/width));
                        sp.getChildren().addAll(imgView);
                    }
                
                }
                
                Image img=new Image(personnage.getImg_map_path());
                ImageView imgView=new ImageView(img);
                imgView.setLayoutX(mapLenght/2);
                imgView.setLayoutY(mapWidth/2);
                sp.getChildren().addAll(imgView);
                AnchorPane.setBottomAnchor(scroll, 0.0);
                AnchorPane.setTopAnchor(scroll, 0.0);
                
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setPannable(true);
                scroll.setContent(sp);
                rootElement.getChildren().clear();
                rootElement.getChildren().addAll(scroll);
                createProfileScene(personnage);
                scroll.setHvalue(scroll.getHmin() + (scroll.getHmax() - scroll.getHmin()) / 2);
                scroll.setVvalue(scroll.getVmin() + (scroll.getVmax() - scroll.getVmin()) / 2);
                
                gameScene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.W ){
                            if(imgView.getLayoutY()-tileSizeW>=0){
                                imgView.setLayoutY(imgView.getLayoutY()-tileSizeW);
                            }
                        } 
                        else if(event.getCode() == KeyCode.A ){
                            if(imgView.getLayoutX()-tileSizeL>=0){
                                imgView.setLayoutX(imgView.getLayoutX()-tileSizeL);
                            }
                        }
                        else if(event.getCode() == KeyCode.S){
                            if(imgView.getLayoutY()+tileSizeW<mapLenght){
                                imgView.setLayoutY(imgView.getLayoutY()+tileSizeW);
                            }
                            
                        }
                        else if(event.getCode() == KeyCode.D) {
                            if(imgView.getLayoutX()+tileSizeL<mapWidth){
                                imgView.setLayoutX(imgView.getLayoutX()+tileSizeL);
                            }
                        }
                        else if(event.getCode() == KeyCode.I) {
                            createInventoryScene(personnage, personnage.inventory);
                        }
                        else if(event.getCode() == KeyCode.O) {
                            createEquippedScene(personnage, personnage.equipped);
                        }
                        else if(event.getCode() == KeyCode.P) {
                            profile.setVisible(!profile.isVisible());
                        }
                        try {
                            interaction(map,((int)imgView.getLayoutX()*lenght)/mapLenght,((int)imgView.getLayoutY()*width)/mapWidth,personnage);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            });
                resetScene(inventoryScene,gameScene,KeyCode.I);
                resetScene(equippedScene,gameScene,KeyCode.O);
                resetScene(interactionScene,gameScene,KeyCode.SPACE);
                resetScene(profileScene,gameScene,KeyCode.P);
                
                gameStage.setResizable(false);
                gameStage.setScene(gameScene);
                gameStage.show();
                scroll.prefWidthProperty().bind(gameScene.widthProperty());
                scroll.prefHeightProperty().bind(gameScene.widthProperty());
                
            }
        });
        
        Button btnHelp = new Button();
        btnHelp.setText("Help");
        btnHelp.setFont(Font.font("Tahoma",FontWeight.THIN, 20));
        btnHelp.setMinSize(350, 70);
        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane help=new AnchorPane();
                Label textHelp=new Label();
                textHelp.setText("Welcome to our \nhorror city of Pennsylvania, where I, the last protector of the city, am facing the 4th invasion of human-looking mystical beings. My goal is to defeat them and let the people of the city live normally. Do you want to represent me in this fight so we can win?\n" +
"\n\nMap exploration: \n\tPress W/A/S/D to move;\n\tPress P to toggle profile;\n\tPress I to toggle inventory;\n\tPress O to toggle equipped items;\n" +
"\n\nFight: \n\tPress SPACE to attack;\n\tPress 1 to use your ability;\n\tPress 2 to use your shield;\n\nPress SPACE to leave Help.");
                textHelp.setWrapText(true);
                textHelp.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                help.setPadding(new Insets(25, 25, 25, 25));
                help.getChildren().addAll(textHelp);
                textHelp.setAlignment(Pos.CENTER);
                textHelp.setTextAlignment(TextAlignment.CENTER);
                AnchorPane.setLeftAnchor(textHelp, 0.0);
                AnchorPane.setRightAnchor(textHelp, 0.0);
                AnchorPane.setBottomAnchor(textHelp, 50.0);
                AnchorPane.setTopAnchor(textHelp, 50.0);
                Scene helpScene=new Scene(help,1280,720);
                help.setStyle("-fx-background-image: url('/ThePhoenixMiniGame/materials/sprites/helpBG.jpg'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
                menuStage.setScene(helpScene);
                menuStage.show();
                resetSceneMenu(menuStage,helpScene,scene,KeyCode.SPACE);
            }
        });
        Button btnExit = new Button();
        btnExit.setFont(Font.font("Tahoma",FontWeight.THIN, 20));
        btnExit.setText("Exit");
        btnExit.setMinSize(350, 70);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        
        
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.setPadding(new Insets(25, 25, 25, 25));
        menuGrid.add(welcome, 0,0,2,1);
        menuGrid.add(lblUsername,0,1);
        menuGrid.add(username,1,1);
        menuGrid.add(btnStartGame, 0, 2,2,1);
        menuGrid.add(btnHelp, 0, 3,2,1);
        menuGrid.add(btnExit, 0, 4,2,1);
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        menuGrid.getColumnConstraints().add(col);
        RowConstraints row = new RowConstraints ();
        row.setValignment(VPos.CENTER);
        menuGrid.getRowConstraints().add(row);
        menuGrid.setStyle("-fx-background-image: url('/ThePhoenixMiniGame/materials/sprites/menuBG.jpg'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
        menuStage.setTitle("Joc");
        menuStage.setResizable(false);
        menuStage.setScene(scene);
        menuStage.show();
    }
   
    public void interaction(HashMap<Carte.Coordonees, Object> map, int x, int y,Personnage p) throws InterruptedException{
        Carte c=new Carte();
        Coordonees coordTest=c.new Coordonees(x,y);
        if(map.containsKey(coordTest)){
            if(map.get(coordTest) instanceof PNJ){
                int randInteraction=c.indexAleatoire(1, 4);
                
                switch(randInteraction){
                    case 1:
                        createInteractionScene(new Label(((PNJ)map.get(coordTest)).interaction1()),new ImageView(new Image(((PNJ)map.get(coordTest)).getSplash_path())));
                        break;
                    case 2:
                        createInteractionScene(new Label(((PNJ)map.get(coordTest)).interaction2()),new ImageView(new Image(((PNJ)map.get(coordTest)).getSplash_path())));
                        break;
                    case 3:
                        createInteractionScene(new Label(((PNJ)map.get(coordTest)).interaction3()),new ImageView(new Image(((PNJ)map.get(coordTest)).getSplash_path())));
                        break;
                }
            }
            else if(map.get(coordTest) instanceof Adversaire){
                createFightScene((Adversaire)map.get(coordTest),personnage);
                deleteEnemy(map,coordTest);
            }
            else if(map.get(coordTest) instanceof Loot){
                sp.getChildren().remove(lootImageMap.get(coordTest));
                p.addToInventory((Loot)map.get(coordTest));
                map.remove(coordTest);
                lootImageMap.remove(coordTest);
            }
            
        }
    }
    public void createInventoryScene(Personnage p, HashMap<Integer,Loot> map){
        inventoryPane.getChildren().clear();
        int x=0;
        int y=2;
        inventoryPane.setAlignment(Pos.CENTER);
        inventoryPane.setHgap(10);
        inventoryPane.setVgap(10);
        inventoryPane.setPadding(new Insets(25, 50, 25, 25));
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        inventoryPane.getColumnConstraints().add(col);
        RowConstraints row = new RowConstraints ();
        row.setValignment(VPos.CENTER);
        inventoryPane.getRowConstraints().add(row);
        Text notification=new Text("Inventory");
        notification.setFont(Font.font("Tahoma",FontWeight.NORMAL, 50));
        inventoryPane.add(notification, 0, 0,5,1);
        Button potionButton=new Button();
        potionButton.setText("Use health potion");
        inventoryPane.add(potionButton, 0, 1,5,1);
        ImageView imgView= new ImageView();
        for(int i : map.keySet()){
            imgView=new ImageView(new Image(map.get(i).getImage()));
            inventoryPane.add(imgView, x, y);
            if(x==4) {
                x=0;
                y++;
            }
            else x++;
        }
        potionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p.useItem();
                int index=inventoryPane.getChildren().size()-1;
                if(index==1){
                    inventoryPane.add(new Text("You don't have any potions!"), 0, 2,5,1);
                    return;
                }
                inventoryPane.getChildren().remove(index);
                index--;
                createProfileScene(p);
            }});
        inventoryPane.setStyle("-fx-background-color: rgb(131,131,131);");
        gameStage.setScene(inventoryScene); 
        createProfileScene(p);
    }
    public void createEquippedScene(Personnage p, HashMap<Integer,Loot> map){
        equippedPane.getChildren().clear();
        int x=0;
        int y=2;
        equippedPane.setAlignment(Pos.CENTER);
        equippedPane.setHgap(10);
        equippedPane.setVgap(10);
        equippedPane.setPadding(new Insets(25, 50, 25, 25));
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        equippedPane.getColumnConstraints().add(col);
        RowConstraints row = new RowConstraints ();
        row.setValignment(VPos.CENTER);
        equippedPane.getRowConstraints().add(row);
        Text notification=new Text("Equipped");
        notification.setFont(Font.font("Tahoma",FontWeight.NORMAL, 50));
        equippedPane.add(notification, 0, 0,5,1);
        Text description=new Text();
        equippedPane.add(description,0,1,5,1);
        ImageView imgView;
        
        for(int i : map.keySet()){
            imgView=new ImageView(new Image(map.get(i).getImage()));
            Button imgButton=new Button();
            imgButton.setGraphic(imgView);
            equippedPane.add(imgButton, x, y);
            if(x==4) {
                x=0;
                y++;
            }
            else x++;
            imgButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               description.setText("\t\t"+map.get(i).getName()+"\n"+map.get(i).getDescription()+"\n\t\tHealth: "+map.get(i).getHealth()+"\n\t\tAD: "+map.get(i).getAd()+"\n\t\tAP: "+map.get(i).getAp()+"\n\t\tArmor: "+map.get(i).getArmor());
                    
            }});
        }
        equippedPane.setStyle("-fx-background-color: rgb(131,131,131);");
        gameStage.setScene(equippedScene);
    }
    public void createInteractionScene(Label text,ImageView splash){
        interactionPane.getChildren().clear();
        Rectangle rectangle=new Rectangle(0,720*2/3,1280,720/3);
        rectangle.setFill(Color.rgb(73, 8, 37,0.5));
        rectangle.setStrokeWidth(10);
        rectangle.setStroke(Color.rgb(73, 8, 37,1));
        text.setFont(Font.font("Tahoma",FontWeight.NORMAL, 30));
        text.setAlignment(Pos.CENTER);
        text.setLayoutY(550);
        text.setWrapText(true);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextFill(Color.WHITE);
        Label skipText=new Label("Press SPACE to exit.");
        skipText.setFont(Font.font("Tahoma",FontWeight.EXTRA_LIGHT, 20));
        skipText.setTextFill(Color.WHITE);
        skipText.setAlignment(Pos.CENTER);
        interactionPane.setPadding(new Insets(25, 25, 25, 25));
        interactionPane.getChildren().addAll(splash,rectangle,text,skipText);
        AnchorPane.setLeftAnchor(text, 20.0);
        AnchorPane.setRightAnchor(text, 20.0);
        AnchorPane.setLeftAnchor(splash, 20.0);
        AnchorPane.setRightAnchor(splash, 0.0);
        AnchorPane.setLeftAnchor(skipText, 20.0);
        AnchorPane.setRightAnchor(skipText, 20.0);
        AnchorPane.setBottomAnchor(skipText, 20.0);
        interactionPane.setStyle("-fx-background-color: rgb(131,131,131);");
        gameStage.setScene(interactionScene);
    }
    public void createFightScene(Adversaire adv, Personnage p) throws InterruptedException{
        ImageView advImg=new ImageView(new Image(adv.getSplash_path()));
        ImageView pImg=new ImageView(new Image(p.getSplash()));
        Rectangle rectangle=new Rectangle(0,720*2/3,1280,720/3);
        rectangle.setFill(Color.rgb(73, 8, 37,0.5));
        rectangle.setStrokeWidth(10);
        rectangle.setStroke(Color.rgb(73, 8, 37,1));
        Label announcer=new Label("The fight begins!");
        announcer.setFont(Font.font("Tahoma",FontWeight.NORMAL, 30));
        announcer.setTextFill(Color.WHITE);
        announcer.setAlignment(Pos.CENTER);
        announcer.setTextAlignment(TextAlignment.CENTER);
        announcer.setLayoutY(550);
        AnchorPane.setRightAnchor(announcer, 0.0);
        AnchorPane.setLeftAnchor(announcer, 0.0);
        String hintP="Press SPACE to attack / 1 to use your ability / 2 to shield";
        String hintE="Press SPACE to continue";
        
        Rectangle healthContainer1 = new Rectangle(200.0, 30.0, Color.TRANSPARENT);
        Rectangle healthContainer2 = new Rectangle(200.0, 30.0, Color.TRANSPARENT);
        healthContainer1.setLayoutX(220);
        healthContainer1.setLayoutY(15);
        healthContainer2.setLayoutX(860);
        healthContainer2.setLayoutY(15);
        healthContainer1.setStroke(Color.WHITE);
        healthContainer2.setStroke(Color.WHITE);
        healthContainer1.setStrokeWidth(2);
        healthContainer2.setStrokeWidth(2);
        
        Rectangle healthbar1 = new Rectangle(200.0, 30.0, Color.GREEN);
        healthbar1.setWidth((200.0*p.getHealthPercent())/100);
        Rectangle healthbar2 = new Rectangle(200.0, 30.0, Color.RED);
        Rectangle armor=new Rectangle(0,10.0,Color.WHITE);
        healthbar1.setLayoutX(220);
        healthbar1.setLayoutY(15);
        healthbar2.setLayoutX(860);
        healthbar2.setLayoutY(15);
        armor.setLayoutX(220);
        armor.setLayoutY(45);
        healthbar1.setStroke(Color.WHITE);
        healthbar2.setStroke(Color.WHITE);
        healthbar1.setStrokeWidth(0.5);
        healthbar2.setStrokeWidth(0.5);
        
        Label name1=new Label(p.getName());
        Label name2=new Label(adv.getName());
        name1.setLayoutX(280);
        name1.setLayoutY(50);
        name2.setLayoutX(890);
        name2.setLayoutY(50);
        name1.setFont(Font.font("Tahoma",FontWeight.EXTRA_BOLD, 30));
        name2.setFont(Font.font("Tahoma",FontWeight.EXTRA_BOLD, 30));
        name1.setTextFill(Color.WHITE);
        name2.setTextFill(Color.WHITE);
        
        fightPane.setMinSize(1280, 720);
        GridPane fightPaneGr=new GridPane();
        fightPaneGr.add(advImg, 1, 0);
        fightPaneGr.add(pImg, 0, 0);
       int ok=0;
        fightPane.getChildren().addAll(fightPaneGr,rectangle,announcer,healthbar1,healthbar2,healthContainer1,healthContainer2,name1,name2,armor);
        
        gameStage.setScene(fightScene);
        double d1=healthbar1.getWidth();double d2=healthbar2.getWidth();int initArmor=p.getArmor();
        while(p.getHealth()>0&&adv.getHealth()>0){
            if (ok==0){
                announcer.setText("Your turn!\n"+hintP);
                attack(pImg,fightScene,p,adv);
                healthbar2.setWidth((d2*adv.getHealthPercent())/100);
                armor.setWidth(p.getArmor());
                ok=1;
            }
            else{
                announcer.setText("Enemies turn!\n"+hintE);
                enemyAttack(advImg,fightScene,p,adv);
                healthbar1.setWidth((d1*p.getHealthPercent())/100);
                armor.setWidth(p.getArmor());
                ok=0;
            }
        }
        
        if(p.getHealth()<=0){
            announcer.setText("Defeat! Press SPACE to return to the menu.");
            exitGame(fightScene,KeyCode.SPACE);
        }
        else if(adv.getHealth()<=0){
            announcer.setText("Victory! \nYou earned  "+adv.getExperience()+"XP and "+adv.getDrop().getName()+". \nPress SPACE to return to the game.");
            resetScene(fightScene,gameScene,KeyCode.SPACE);
            p.setArmor(initArmor);
            p.equip(adv.getDrop());
            levelUp(p,adv);
        } 
        createProfileScene(p);
    }
    
    public void resetSceneMenu(Stage menuStage,Scene scene, Scene gameScene, KeyCode key){
        scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == key) {
                            menuStage.setScene(gameScene);
                        } 
                    }});
    }
    
    public void resetScene(Scene scene, Scene gameScene, KeyCode key){
        scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == key) {
                            gameStage.setScene(gameScene);
                        } 
                    }});
    }
    public void exitGame(Scene scene, KeyCode key){
        scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == key) {
                            gameStage.close();
                        } 
                    }});
    }
    public void enemyAttack(ImageView imgView,Scene scene,Personnage p, Adversaire adv){
        Image original=imgView.getImage();
        
        scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.SPACE) {
                           imgView.setImage(new Image(adv.getSplash_path_attack()));
                           try{
                               if(p.getArmor()==0){
                                   p.setHealthPercent((p.getHealth()-adv.getAp())*100/p.getInitialHealth());
                                   p.setHealth(p.getHealth()-adv.getAp());
                               }
                               else if(p.getArmor()>=adv.getAp()){
                                   p.setArmor(p.getArmor()-adv.getAp());
                               }
                               else if(p.getArmor()<adv.getAp()){
                                   p.setHealthPercent(((p.getHealth()-(adv.getAp()-p.getArmor())) *100)/p.getInitialHealth());
                                   p.setHealth(p.getHealth()-(adv.getAp()-p.getArmor()));
                                   p.setArmor(0);
                               }
                           }
                           catch(Exception e){
                               resume();
                           }
                           
                       }
                    }});
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent event)
                {
                    if(event.getCode() == KeyCode.SPACE) {
                           imgView.setImage(original);
                          resume(); 
                    }
                }
            });
        pause();
    }
       
    public void attack(ImageView imgView,Scene scene,Personnage p, Adversaire adv){
        Image original=imgView.getImage();
        scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.SPACE) {
                           imgView.setImage(new Image(p.getSplash_path_attack()));
                           try{
                               adv.setHealthPercent((adv.getHealth()-p.getAd())*100/adv.getInitialHealth());
                               adv.setHealth(adv.getHealth()-p.getAd());
                           }
                           catch(Exception e){
                               resume();
                           }
                       }
                        else if(event.getCode() == KeyCode.DIGIT1) {
                           imgView.setImage(new Image(p.getSplash_path_ability()));
                           try{
                               adv.setHealthPercent((adv.getHealth()-p.getAp())*100/adv.getInitialHealth());
                               adv.setHealth(adv.getHealth()-p.getAp());
                           }
                           catch(Exception e){
                               resume();
                           }
                        }
                        else if(event.getCode() == KeyCode.DIGIT2) {
                           imgView.setImage(new Image(p.getSplash_path_shield()));
                           p.setArmor(p.getArmor()+p.getAp());
                        }
                    }});
      
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent event)
                {
                    if(event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.DIGIT2) {
                           imgView.setImage(original);
                          resume(); 
                    }
                }
            }); 
        pause();    
    }
    public void levelUp(Personnage p, Adversaire adv){
        p.setExperience(p.getExperience()+adv.getExperience());
            if(p.getExperience()%1000==0){
                p.setLevel(p.getExperience()/1000);
                p.setInitialHealth(p.getInitialHealth()+p.getExperience()/10);
                p.setAd(p.getAd()+p.getExperience()/100);
                p.setAp(p.getAp()+p.getExperience()/50);
            }
    }

public void createProfileScene(Personnage p){
    profile.getChildren().clear();
    ImageView imgView=new ImageView(new Image(p.getImg_map_path()));
    profile.add(imgView, 0, 0, 1,6);
    Label name=new Label();
    name.setText(p.getName());
    name.setFont(Font.font("Tahoma",FontWeight.EXTRA_BOLD, 30));
    name.setTextFill(Color.WHITE);
    Label level=new Label("Level: "+p.getLevel());
    level.setFont(Font.font("Tahoma",FontWeight.NORMAL, 15));
    level.setTextFill(Color.WHITE);
    Label showArmor=new Label();
    showArmor.setText("Armor: "+p.getArmor());
    showArmor.setTextFill(Color.WHITE);
    Label showAd=new Label();
    showAd.setText("AD: "+p.getAd());
    showAd.setTextFill(Color.WHITE);
    Label showAp=new Label();
    showAp.setText("AP: "+p.getAp());
    showAp.setTextFill(Color.WHITE);
    Label showHp=new Label();
    showHp.setText("HP: "+p.getHealthPercent()+"%");
    showHp.setTextFill(Color.WHITE);
    profile.add(name, 1, 0);
    profile.add(level, 1, 1);
    profile.add(showArmor, 1, 2);
    profile.add(showAd, 1, 3);
    profile.add(showAp, 1, 4);
    profile.add(showHp, 1, 5);
    Rectangle healthContainer1 = new Rectangle(200.0, 30.0, Color.TRANSPARENT);
    healthContainer1.setStroke(Color.GRAY);
    healthContainer1.setStrokeWidth(2);
    Rectangle healthbar1 = new Rectangle(200.0, 30.0, Color.GREEN);
    healthbar1.setWidth((200.0*p.getHealthPercent())/100);
    healthbar1.setStroke(Color.GRAY);
    healthbar1.setStrokeWidth(1);
    Rectangle armor=new Rectangle(0,10.0,Color.WHITE);
    armor.setWidth(p.getArmor());
    armor.setStroke(Color.GRAY);
    armor.setStrokeWidth(2);
    StackPane health=new StackPane();
    health.setAlignment(Pos.CENTER_LEFT);
    health.getChildren().addAll(healthContainer1,healthbar1);
    profile.add(health,0,6,2,1);
    profile.add(armor,0,7,2,1);
    Label hint=new Label();
    hint.setText("Press 'P' to toggle");
    hint.setTextFill(Color.WHITE);
    profile.add(hint, 0,8,2,1);
    profile.setAlignment(Pos.CENTER);
    profile.setStyle("-fx-background-color: rgba(0,0,0,0.5);-fx-padding:0 25 0 25");
    if(!rootElement.getChildren().contains(profile)){
        rootElement.getChildren().addAll(profile);
    }
}
private final Object PAUSE_KEY = new Object();
private void pause() {
    Platform.enterNestedEventLoop(PAUSE_KEY);
}

private void resume() {
    Platform.exitNestedEventLoop(PAUSE_KEY,null);
}
public void deleteEnemy(HashMap<Carte.Coordonees,Object> map, Carte.Coordonees coord){
    map.put(coord, new Terrain(0));
}
    public static void main(String[] args) {
        launch(args);
    }
    
}
