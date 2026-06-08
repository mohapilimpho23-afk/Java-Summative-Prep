package summative;

// ============================
// TODO: Question 2
// ============================
public abstract class Robot {
    // Declare private fields
    private String name;
    private int shieldStrength;


    // Implement constructor
    public Robot(String name , int shieldStrength){
        this.name = name;
        this.shieldStrength = shieldStrength;
    }

    // Implement getters
    public String name(){
        return name;
    }

    public int getShieldStrength(){
        return shieldStrength;
    }

    // Implement takeDamage(int amount)
    public void takeDamage(int amount){

    }

    // Helper method for Q3:
    public void repairShields() {
        // Example logic for tests to verify
        // this.shieldStrength = 5;
    }

    public abstract String fireWeapon();
}

class SniperRobot extends Robot {
    public SniperRobot(String name, int shieldStrength) {
        super(name, shieldStrength);
    }

    // Implement abstract methods
    @Override
      void fireWeapon() {
        return ""

    }

     abstract void shieldStrength();
}

class TankRobot extends Robot {
    public TankRobot(String name, int shieldStrength) {
        super(name, shieldStrength);
    }

    // Implement abstract methods
}
