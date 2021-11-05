package csd.uoc.gr.A21;

class Sensor {
    private String id;
    private boolean violation;
    private Boolean on;

    //Set methods----------------------
    public void set_id(String _id){
        this.id = _id;
    }

    public void set_violation(boolean _violation){
        this.violation = _violation;
    }

    public void set_on(Boolean _on){
        this.on = _on;
    }

    //Get methods------------------------
    public String get_id(){
        return id;
    }

    public boolean get_violation(){
        return violation;
    }

    public Boolean get_on(){
        return on;
    }

    public  Sensor(){} //Default constructor
    //Main constructor
    public Sensor(String _id,Boolean _on, boolean _violation){
        id = _id;
        on = _on;
        violation = _violation;
    }

    public String toString(){
        return "Sensor with id = " + get_id() + " , On state = " + get_on() + " , Violation state = " + get_violation();
    }

}



