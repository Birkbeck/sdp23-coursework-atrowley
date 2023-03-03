// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml.classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

/**
 * This class contains testing related to the Machine class
 * (moved out of other test classes)
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public class MachineTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = Machine.getMachine(Registers.getRegisters());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void checkResultAssignsToRegisterB() {
        registers.set(EAX, 30);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void checkSourceAssignsToRegisterB() {
        registers.set(EAX, 1);
        registers.set(EBX, 50);
        Assertions.assertEquals(50, machine.getRegisters().get(EBX));
    }

}
