package summative;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Systems & Networking Summative Tests")
class SummativeTests {

    @Nested
    @DisplayName("Question 1: parseCommandArgs()")
    class ParseCommandArgsTests {

        @Test
        @DisplayName("Parses standard space-separated command")
        void testStandardCommand() {
            List<String> expected = Arrays.asList("launch", "sniper", "5", "3");
            assertEquals(expected, SystemsSummative.parseCommandArgs("launch sniper 5 3"));
        }

        @Test
        @DisplayName("Ignores multiple consecutive spaces")
        void testExtraSpaces() {
            List<String> expected = Arrays.asList("forward", "10");
            assertEquals(expected, SystemsSummative.parseCommandArgs("forward     10"));
        }

        @Test
        @DisplayName("Handles leading and trailing spaces")
        void testLeadingTrailingSpaces() {
            List<String> expected = Arrays.asList("turn", "left");
            assertEquals(expected, SystemsSummative.parseCommandArgs("   turn left   "));
        }

        @Test
        @DisplayName("Returns empty list for null input")
        void testNullInput() {
            assertTrue(SystemsSummative.parseCommandArgs(null).isEmpty());
        }

        @Test
        @DisplayName("Returns empty list for whitespace-only input")
        void testEmptyInput() {
            assertTrue(SystemsSummative.parseCommandArgs("   ").isEmpty());
        }
    }

    @Nested
    @DisplayName("Question 2: Robot Hierarchy")
    class RobotHierarchyTests {

        @Test
        @DisplayName("SniperRobot implements fireWeapon correctly")
        void testSniperFire() {
            Robot sniper = new SniperRobot("Ghost", 3);
            assertEquals("Sniper Ghost fires a high-velocity piercing shot!", sniper.fireWeapon());
        }

        @Test
        @DisplayName("TankRobot implements fireWeapon correctly")
        void testTankFire() {
            Robot tank = new TankRobot("Juggernaut", 10);
            assertEquals("Tank Juggernaut fires an explosive shell!", tank.fireWeapon());
        }

        @Test
        @DisplayName("takeDamage reduces shield but not below zero")
        void testTakeDamage() {
            Robot bot = new SniperRobot("Target", 5);
            bot.takeDamage(3);
            assertEquals(2, bot.getShieldStrength());

            bot.takeDamage(5); // More damage than remaining shields
            assertEquals(0, bot.getShieldStrength(), "Shields should not drop below zero");
        }
    }

    @Nested
    @DisplayName("Question 3: ShieldRepairTask")
    class ShieldRepairTaskTests {

        @Test
        @DisplayName("Runnable executes and restores shields after delay")
        void testRepairExecution() throws InterruptedException {
            Robot bot = new TankRobot("BrokenBot", 0);
            ShieldRepairTask task = new ShieldRepairTask(bot, 100);

            Thread thread = new Thread((Runnable) task);
            thread.start();

            // Before thread finishes, shields should still be 0
            assertEquals(0, bot.getShieldStrength());

            thread.join(); // Wait for thread to finish

            // Assumes repairShields() sets it to 5
            assertEquals(5, bot.getShieldStrength(), "Shields should be repaired after thread completes");
        }
    }

    @Nested
    @DisplayName("Question 4: filterActiveConnections()")
    class FilterConnectionsTests {

        @Test
        @DisplayName("Filters only CONNECTED states")
        void testFilterLogic() {
            List<ClientConnection> connections = Arrays.asList(
                    new ClientConnection("Client1", "CONNECTED"),
                    new ClientConnection("Client2", "DISCONNECTED"),
                    new ClientConnection("Client3", "CONNECTED"),
                    new ClientConnection("Client4", "PENDING")
            );

            List<ClientConnection> result = SystemsSummative.filterActiveConnections(connections);

            assertEquals(2, result.size());
            assertEquals("CONNECTED", result.get(0).getState());
            assertEquals("CONNECTED", result.get(1).getState());
        }

        @Test
        @DisplayName("Returns empty list if none match")
        void testNoMatches() {
            List<ClientConnection> connections = Arrays.asList(
                    new ClientConnection("Client1", "DISCONNECTED")
            );
            assertTrue(SystemsSummative.filterActiveConnections(connections).isEmpty());
        }

        @Test
        @DisplayName("Returns empty list for null input")
        void testNullList() {
            assertTrue(SystemsSummative.filterActiveConnections(null).isEmpty());
        }

        @Test
        @DisplayName("Ensures original list is not modified (Immutability check)")
        void testOriginalListUnchanged() {
            List<ClientConnection> connections = new ArrayList<>(Arrays.asList(
                    new ClientConnection("Client1", "CONNECTED"),
                    new ClientConnection("Client2", "DISCONNECTED")
            ));

            SystemsSummative.filterActiveConnections(connections);
            assertEquals(2, connections.size(), "Original list must remain unmodified");
        }
    }
}