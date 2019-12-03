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
                System.out.println("adelante");
                controller.updateController();
                System.out.println("beguante");
                Thread.sleep(100);
                System.out.println("caliente");
//                System.gc();
            }

        }catch (Exception e) {System.out.println("casseruola");};
    }
}
