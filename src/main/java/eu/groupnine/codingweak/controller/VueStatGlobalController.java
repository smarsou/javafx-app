package eu.groupnine.codingweak.controller;
import eu.groupnine.codingweak.Model;
import eu.groupnine.codingweak.Observer;
public class VueStatGlobalController implements Observer{
    Model model;

    public VueStatGlobalController(Model model){
        this.model = model;
    }
    public void refresh(){
        
    }
}
