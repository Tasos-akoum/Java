package csd.uoc.gr.A21;

class SensorLine extends Sensor{
    private String name;
    private Sensor[] array = new Sensor[1000];

    private int activeCapacity = 0;
    private int numberOfViolated = 0;

    public SensorLine(){
        name = "Anonymous sensorLine";
    }

    public SensorLine(String _name){
        name = _name;
    }

    public void add(Sensor s){
        if(activeCapacity < 1000){
            try {
                array[activeCapacity] = s;
                activeCapacity++;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void setOn(Boolean b){
        for(Sensor s : array) {
            if(s != null)
                s.set_on(b);
        }
    }

    public boolean isViolated(){
        boolean b = false;

        for(int i = 0; i < activeCapacity; i++){
            if(array[i] != null) {
                if (array[i].get_violation()) {
                    b = true;
                    numberOfViolated++;
                }
            }
        }

        return b;
    }

    public Sensor[] getSensors(){
        return array;
    }

    private String printViolated(){
        String ids = "";

        for(int i = 0; i < activeCapacity; i++){
            if(array[i].get_violation()){
                ids += array[i].get_id() + " ";
            }
        }

        return ids;
    }

    public String toString(){
        return "SensorLine " + name + "\nNumber of sensors=" + activeCapacity + "\nViolated=" + isViolated() + "\n# of violated sensors=" + numberOfViolated +
                "\nIds of Violated: " + printViolated();
    }

}

