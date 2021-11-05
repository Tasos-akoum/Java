package csd.uoc.gr.A21;

class LaserSensor extends Sensor{
    private float range;

    public void set_range(float _range){
        try {
            range = _range;
        } catch(Exception e){
            System.err.println(e.getMessage());
            range = 0;
        }
    }

    public float get_range(){
        return range;
    }

    public LaserSensor(String _id, Boolean _on, boolean _violation, float _range){
        super(_id, _on, _violation);
        range = _range;
    }

    public String toString(){
        return super.toString() + ", range = " + range + ", (laser sensor)";
    }

}

