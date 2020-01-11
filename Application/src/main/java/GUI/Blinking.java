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
            boolean a = false;
            while(i<10){
//                System.out.println(status.getStyleClass().get(0));
                if(a == true)
                    status.getStyleClass().remove(4);
                status.getStyleClass().add("label-status-warning");
                System.out.println(status.getStyleClass().get(4));
                Thread.sleep(2000);
                status.getStyleClass().remove(4);
                status.getStyleClass().add("label-status-stable");
                System.out.println(status.getStyleClass().get(4));

                a = true;
                i += 1;
            }
        }catch (Exception e) {};
    }
}
