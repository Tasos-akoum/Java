package csd.uoc.gr.A21;

class SensorTester {
    public static void main(String[] args) {
        Sensor s1 = new Sensor("0", true, true);
        LaserSensor s2 = new LaserSensor("1", false, false, 4);

        SensorLine line = new SensorLine();
        line.add(s1);
        line.add(s2);
        System.out.println(line);

        SensorLine line1 = new SensorLine();

        int id = 1;
        
        for(int i = 1; i <= 25; i++){
            Sensor s = new Sensor("SID" + (id), true, false);
            TemperatureSensor ts = new TemperatureSensor("SID" + (id + 1), true, false);
            LaserSensor ls = new LaserSensor("SID" + (id+2), true, false, id+1);
            Sensor ss = new Sensor("SID" + (id+3), true, true);

            line1.add(s);
            line1.add(ts);
            line1.add(ls);
            line1.add(ss);

            id += 4;
        }

        System.out.println(line1);
    }
}