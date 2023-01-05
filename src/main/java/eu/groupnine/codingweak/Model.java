package eu.groupnine.codingweak;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import eu.groupnine.codingweak.stockage.Stats;
import org.controlsfx.control.PropertySheet.Mode;

import eu.groupnine.codingweak.stockage.Carte;
import eu.groupnine.codingweak.stockage.Pile;
import eu.groupnine.codingweak.stockage.Stockage;

public class Model extends Observateur{
    SceneController sc;
    ArrayList<Carte> PileCartes;
    String keyClicked;
    public long time = 5;

    Stats currentStats = new Stats();


    Stockage stockFromDisk;

    public Model() throws FileNotFoundException, IOException{
        super();
        stockFromDisk = new Stockage();
        stockFromDisk.load();
    }

    public void setStast(){
        currentStats.cartesTrouvees= 15;
        currentStats.cartesNonTrouvees=8;
    }
    public void setCard(){
        this.PileCartes = new ArrayList<>();
        
        Carte carte1 = new Carte(1,"question1","reponse1");
        Carte carte2 = new Carte(2,"question2","reponse2");
        Carte carte3 = new Carte(3,"question3","reponse3");

        
        this.PileCartes.add(carte1);
        this.PileCartes.add(carte2);
        this.PileCartes.add(carte3);
        
    }
    
}
