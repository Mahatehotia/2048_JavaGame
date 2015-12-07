/**
 * Created by evanzyker on 25/11/15.
 */
public class ControlMaster {

    public Model model;
    public Vue vue;
    public ControlKey controlkey;
    public ControlMenu controlMenu;

    public ControlMaster(Model model){
        this.model = model;
        vue = new Vue(this.model);
        controlkey = new ControlKey(vue, this.model);
        controlMenu = new ControlMenu(vue, this.model);

        //Assignation des controllers
        vue.setControlKey(controlkey);
        vue.setControlMenu(controlMenu);
    }
}
