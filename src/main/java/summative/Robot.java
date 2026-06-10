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
        shieldStrength --;
        if (shieldStrength < 0){
            throw new IllegalArgumentException("Strength shield can not go below zero!");
        }

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
    public String fireWeapon(){
        return "Sniper"  + name() + "fires a high-velocity piercing shot!";
    }
}

class TankRobot extends Robot {
    public TankRobot(String name, int shieldStrength) {
        super(name, shieldStrength);
    }

    // Implement abstract methods
    @Override
    public  String fireWeapon(){
        return "Tank"  + name() + "fires an explosive shell!";
    }
}
