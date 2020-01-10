package simulation;

public abstract class Value_Counter {

    public Value_Counter(){
    }

    public abstract void Pressure_Values(double val);

    public abstract void Current_Temp(double val);

    public abstract void Count_bpm(double val, int index);

    public abstract int Index_Difference();

    public abstract double Double_Value();

    public abstract int[] Max_Min();

}