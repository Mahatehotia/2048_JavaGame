/**
 * Created by evanzyker on 20/11/15.
 */
public class GameLauncher {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                Model model = new Model(4);
                ControlMaster cm = new ControlMaster(model);
            }

        });
    }
}
