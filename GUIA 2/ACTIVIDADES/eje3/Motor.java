package eje3;

public class Motor {
    private int numMotor;
    private int revPorMin;
    
    public Motor(int numMotor, int revPorMin) {
        this.numMotor = numMotor;
        this.revPorMin = revPorMin;
    }
    
    // Getters y setters
    public int getNumMotor() {
        return numMotor;
    }
    
    public void setNumMotor(int numMotor) {
        this.numMotor = numMotor;
    }
    
    public int getRevoluciones() {
        return revPorMin;
    }
    
    public void setRevoluciones(int revPorMin) {
        this.revPorMin = revPorMin;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Motor [Número: " + numMotor + ", RPM: " + revPorMin + "]";
    }
}