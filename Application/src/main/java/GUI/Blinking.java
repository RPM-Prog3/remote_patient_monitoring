package GUI;


import javafx.scene.control.Label;

public class Blinking implements Runnable {
    private Label status, value;
    private volatile int which_status;
    private volatile boolean running;

    public Blinking(Label status, Label value){
        this.status = status;
        this.value = value;
        which_status = 0;
        running = true;
    }

    public void run(){
        try{
            int i = 0;
//            boolean remove_class = true;
//            boolean stable = false;
            status.getStyleClass().add("label-status-stable");
            value.getStyleClass().add("label-value-stable");

            while(running){
                if (which_status == 0){
//                    if (remove_class && !stable) {
//                        status.getStyleClass().remove(4);
//                        value.getStyleClass().remove(4);
//                        status.getStyleClass().add("label-status-stable");
//                        value.getStyleClass().add("label-value-stable");
//                    }
//                    stable = true;
                }

                if (which_status == 1) {
                    //if (remove_class)
                        status.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-warning");
                    Thread.sleep(1000);
                    status.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-stable");
                    Thread.sleep(1000);
//                    remove_class = true;
//                    stable = false;
                }

                if (which_status == 2){
                    //if (remove_class) {
                        status.getStyleClass().remove(4);
                        value.getStyleClass().remove(4);
                    //}
                    status.getStyleClass().add("label-status-urgent");
                    value.getStyleClass().add("label-value-urgent");
                    Thread.sleep(1000);
                    status.getStyleClass().remove(4);
                    value.getStyleClass().remove(4);
                    status.getStyleClass().add("label-status-stable");
                    value.getStyleClass().add("label-value-stable");
                    Thread.sleep(1000);
//                    remove_class = true;
//                    stable = false;
                }
            }

        }catch (Exception e) {
            System.out.println("failed blinking runnable");
            e.printStackTrace();

        };
    }

    public void stopThread(){
        running = false;
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
