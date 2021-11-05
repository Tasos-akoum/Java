package csd.uoc.gr.A21;

class TemperatureSensor extends Sensor{
    public TemperatureSensor(String _id, Boolean _on, boolean _violation){
        super(_id, _on, _violation);
    }
    public String toString(){
        return super.toString() + ", (temp. sensor)";
    }

    public static void main(String[] args) {
        System.out.println(new TemperatureSensor("0",true,false));
    }
}
