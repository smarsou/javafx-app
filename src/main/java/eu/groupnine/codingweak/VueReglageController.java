package eu.groupnine.codingweak;

import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.groupnine.codingweak.stockage.Carte;
import eu.groupnine.codingweak.stockage.Pile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueReglageController implements Observer, Initializable {
    Model model;
    @FXML
    private TextField  NomPile;

    @FXML
    private TextField Description;

    @FXML
    private TableView<Carte> Table;

    @FXML
    private TableColumn<Carte, Integer> idCartes;

    @FXML
    private TableColumn<Carte, String> Questions;

    @FXML
    private TableColumn<Carte, String> Reponses;

    @FXML
    private TextArea question;

    @FXML
    private TextArea reponse;

    @FXML
    private TextField idCarte;

    @FXML
    private Label nbCartes;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button CreateButton;

    @FXML
    private Button ModifyButton;

    @FXML
    private Button ClearButton;






    public VueReglageController(Model model){
        this.model = model;
        this.model.observers.add(this);
    }

    



    @FXML
    public void rowClicked() {
        Carte clickCarte = this.Table.getSelectionModel().getSelectedItem();
        if (clickCarte != null) {
            this.idCarte.setText(clickCarte.getId()+"");
            this.question.setText(clickCarte.getQuestion());
            this.reponse.setText(clickCarte.getReponse());

        }

    }

    @FXML
    public void Register() {
        Pile p = this.model.getCurrentPile();
        p.getNom();
        p.getDescription();
        String n = this.NomPile.getText();
        String d = this.Description.getText();
        if ((n != null)&&(d != null)) {
            this.model.getCurrentPile().setNom(n);
            this.model.getCurrentPile().setDescription(d);
            this.model.callObservers();

        }
        this.NomPile.setText(null);
        this.Description.setText(null);
        this.idCarte.setText(null);
        this.question.setText(null);
            
        this.reponse.setText(null);

    }



    @FXML
    public void Modify()  {
        String ind = this.idCarte.getText();
        if (ind != null) {
            int id = Integer.parseInt(ind);
            String q = this.question.getText();
            String rep = this.reponse.getText();
            this.model.ModifyQuestionCarte(id,q);
            this.model.ModifyReponseCarte(id,rep);
            this.model.callObservers();
            this.idCarte.setText(null);
            this.question.setText(null);
            this.reponse.setText(null);

        }
        

    }

    @FXML
    public void Create()  {
        
        String q = this.question.getText();
        String rep = this.reponse.getText();
        if ((q != null)&&(rep != null)) {
            int indLast = this.model.PileCartes.size() -1;

            int lastId = this.model.PileCartes.get(indLast).getId();
    
            this.model.PileCartes.add(new Carte(lastId+1, q, rep));
    
            this.model.callObservers();
            this.idCarte.setText(null);
    
            this.question.setText(null);
    
            this.reponse.setText(null);

        }
    }



    @FXML
    public void Supp() {
        String id = this.idCarte.getText();
        String answer = this.reponse.getText();
        String quest = this.question.getText();
        ArrayList<Carte> copy = new ArrayList<>();
        for (Carte c : this.model.getCurrentPile().cartes) {
            copy.add(c);
            if ((String.valueOf(c.getId()).equals(id))&&(c.getQuestion().equals(quest))&&(c.getReponse().equals(answer))) {
                System.out.println(quest);
                break;
            }
        }
        this.model.getCurrentPile().cartes.clear();
        this.model.getCurrentPile().cartes.addAll(copy);
        
        this.model.callObservers();
        this.idCarte.setText(null);
    
            
        this.question.setText(null);
    
            
        this.reponse.setText(null);


    }
    
    public void refresh(){
        this.NomPile.setText(null);
        this.Description.setText(null);
        Pile p = this.model.getCurrentPile();
        this.NomPile.setText(p.getNom());
        this.Description.setText(p.getDescription());
        
        this.idCartes.setCellValueFactory(new PropertyValueFactory<Carte, Integer>("idcard"));
        this.Questions.setCellValueFactory(new PropertyValueFactory<Carte, String>("infoquestion"));
        this.Reponses.setCellValueFactory(new PropertyValueFactory<Carte, String>("inforeponse"));
        this.Table.getItems().clear();
        
        ArrayList<Carte> CurrentCartes = this.model.getCurrentPile().cartes;
        
        
        for (int i = 0;i<CurrentCartes.size();i++) {
            this.Table.getItems().add(new Carte(i,CurrentCartes.get(i).getQuestion(),CurrentCartes.get(i).getReponse()));
        }
        this.nbCartes.setText(CurrentCartes.size() + " " +"Cartes");

        
    }

    public void setIcone(){
        Image image1 = new Image(getClass().getResource("/icone/register.png").toExternalForm()); 
        ImageView icon1 = new ImageView(image1);
        icon1.setFitWidth(30);
        icon1.setFitHeight(30); 
        RegisterButton.setGraphic(icon1);
        RegisterButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Image image2 = new Image(getClass().getResource("/icone/creer.png").toExternalForm()); 
        ImageView icon2 = new ImageView(image2);
        icon2.setFitWidth(30);
        icon2.setFitHeight(30); 
        CreateButton.setGraphic(icon2);
        CreateButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Image image3 = new Image(getClass().getResource("/icone/modifier.png").toExternalForm()); 
        ImageView icon3 = new ImageView(image3);
        icon3.setFitWidth(30);
        icon3.setFitHeight(30); 
        ModifyButton.setGraphic(icon3);
        ModifyButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Image image4 = new Image(getClass().getResource("/icone/nettoyer.png").toExternalForm()); 
        ImageView icon4 = new ImageView(image4);
        icon4.setFitWidth(30);
        icon4.setFitHeight(30); 
        ClearButton.setGraphic(icon4);
        ClearButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setIcone();
        
    }

    
}
