package GUI;

public class Refresh implements Runnable{
    private Panel_Controller controller;

    public Refresh(Panel_Controller controller) {
        this.controller = controller;
    }

    public void run() {
        try{
            //Thread.sleep(1000);
            while (1<2) {
                controller.updateController();
                Thread.sleep(500);
            }

        }catch (Exception e) {System.out.println("casseruola");};
    }
}
