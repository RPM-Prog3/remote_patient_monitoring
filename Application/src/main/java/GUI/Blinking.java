package GUI;


import javafx.scene.control.Label;

public class Blinking implements Runnable {
    Label status;

    public Blinking(Label status){
        this.status = status;
    }

    public void run(){
        try{
            int i = 0;
            boolean blinking_boolean = false;
            while(1<2){
                if(blinking_boolean)
                    status.getStyleClass().remove(4);
                status.getStyleClass().add("label-status-warning");
                Thread.sleep(1000);
                status.getStyleClass().remove(4);
                status.getStyleClass().add("label-status-stable");
                Thread.sleep(1000);
                blinking_boolean = true;
            }

        }catch (Exception e) {};
    }
}
