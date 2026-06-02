package summative;

// ============================
// TODO: Question 2
// ============================
public abstract class Robot {
    // Declare private fields

    // Implement constructor

    // Implement getters

    // Implement takeDamage(int amount)

    // Helper method for Q3:
    public void repairShields() {
        // Example logic for tests to verify
        // this.shieldStrength = 5;
    }

    // Declare abstract fireWeapon()
}

class SniperRobot extends Robot {
    public SniperRobot(String name, int shieldStrength) {
        super(name, shieldStrength);
    }

    // Implement abstract methods
}

class TankRobot extends Robot {
    public TankRobot(String name, int shieldStrength) {
        super(name, shieldStrength);
    }

    // Implement abstract methods
}
