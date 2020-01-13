public class Test_Main {
    public static void main(String[] args){
        Test_Simulation test1 =  new Test_Simulation();
        test1.Test_ECG();
        test1.Test_BP();
        test1.Test_RR();
        test1.Test_TEMP();

        Test_Status test2 = new Test_Status();
        //test2.Stable_Status_Test();
        test2.Warning_Status_Test();
        test2.Urgent_Status_Test();
    }

}
