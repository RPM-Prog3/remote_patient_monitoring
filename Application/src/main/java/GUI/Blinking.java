package GUI;


import javafx.scene.control.Label;

public class Blinking implements Runnable {
    Label status;
    private int which_status;

    public Blinking(Label status){
        this.status = status;
        which_status = 0;
    }

    public void run(){
        try{
            int i = 0;
            boolean remove_class = false;

            while(1<2){
                if (which_status == 0){
                    if (remove_class)
                        status.getStyleClass().remove(4);
                    remove_class = false;
                }

                if (which_status == 1) {
                    if (remove_class)
                        status.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-warning");
                    Thread.sleep(1000);
                    status.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-stable");
                    Thread.sleep(1000);
                    remove_class = true;
                }

                if (which_status == 2){
                    if (remove_class)
                        status.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-warning");
                    Thread.sleep(1000);
                    status.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-stable");
                    Thread.sleep(1000);
                    remove_class = true;
                }
            }

        }catch (Exception e) {};
    }

    public void stable_status(){
        which_status = 0;
    }

    public void warning_status(){
        which_status = 1;
    }

    public void urgent_status(){
        which_status = 2;
    }

}
