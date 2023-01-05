package eu.groupnine.codingweak;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import eu.groupnine.codingweak.stockage.Stats;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

public class VueStatGlobalController implements Observer, Initializable{
    Model model;

    @FXML
    ListView<String> AreaOfPiles;

    @FXML
    BarChart<String, Number> barChart;

    public VueStatGlobalController(Model model){
        this.model = model;
        model.observers.add(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AreaOfPiles.getItems().clear();
        // chargeOfPiles();
    }
    
    public void chargeOfPiles(){
        //Obtenir l'ensemble des clés du dictionnaire
        Set<String> pileNames = model.stockFromDisk.EnsembleDesPiles.keySet();
        
        if (pileNames == null){
            return;
        }

        for (String pileName : pileNames) {
            String Name;
            String Description;
            String cartesJouees;
            String cartesTrouvees;
            String cartesNonTrouvees;
            String cartesParMinutes;
            String tempsPasse;
            String affichage;
            Stats stats = model.stockFromDisk.EnsembleDesPiles.get(pileName).geStats();
            Name = model.stockFromDisk.EnsembleDesPiles.get(pileName).getNom();
            Description= model.stockFromDisk.EnsembleDesPiles.get(pileName).getDescription();
            cartesJouees = "" + stats.cartesJouees;
            cartesTrouvees = "" + stats.cartesTrouvees;
            cartesNonTrouvees = "" + stats.cartesNonTrouvees;
            cartesParMinutes = "" + stats.cartesParMinutes;
            tempsPasse = "" + stats.tempsPasse;
            affichage = "     " + Name + "    \n";
            affichage = affichage + "Description : " + Description + "\n";
            affichage = affichage + " nombre de carte jouees : " + cartesJouees + "\n";
            affichage = affichage + "nombre cartes non jouees : " + cartesNonTrouvees + "\n"; 
            affichage = affichage + "nombre de carte trouvees : " + cartesTrouvees + "\n"; 
            affichage = affichage + "nombre de carte non trouvees : " + cartesNonTrouvees + "\n";
            affichage = affichage + "temps par carte " + cartesParMinutes + "\n";
            affichage = affichage + "temps total " + tempsPasse + "\n";              
            AreaOfPiles.getItems().add(affichage);
        }
    }

    public void setGraph(){
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Partie jouée");
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Taux de réussite");

        new BarChart<String, Number>(xAxis, yAxis);

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName(model.keyClicked);


        dataSeries1.getData().add(new XYChart.Data<String, Number>("Serge", 9));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Soulaiman", 2));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Louis", 1));

        barChart.getData().add(dataSeries1);
        barChart.setTitle("Stats de la pile " + model.stockFromDisk.EnsembleDesPiles.get(model.keyClicked).getNom());
    }

    public void refresh(){
        initialize(null, null);
    }

}
