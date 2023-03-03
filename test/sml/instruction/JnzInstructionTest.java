// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml.instruction;

import org.junit.jupiter.api.*;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
import static sml.Registers.Register.EAX;

/**
 * This class contains JUNIT tests for testing functionality
 * of the JnzInstruction class
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
class JnzInstructionTest {
  private Machine machine;
  private Registers registers;
  private Labels labels;

  @BeforeEach
  void setUp() {
    machine = Machine.getMachine(Registers.getRegisters());
    registers = machine.getRegisters();
    labels = machine.getLabels();
    labels.reset();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  /**
   * Validates that instance of instruction with correct opcode is created
   */
  @Test
  void createsJnzInstanceB() {
    Instruction instruction = new JnzInstruction(null,"EAX", "f1");
    Assertions.assertEquals("jnz", instruction.getOpcode());
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToStringB() {
    Instruction instruction = new JnzInstruction(null,"EAX", "f1");
    Assertions.assertEquals("jnz EAX f1", instruction.toString());
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToString2B() {
    Instruction instruction = new JnzInstruction("f2", "EAX", "f1");
    Assertions.assertEquals("f2: jnz EAX f1", instruction.toString());
  }

  /**
   * Validates that execute method returns NORMAL_PROGRAM_COUNTER_UPDATE
   */
  @Test
  void checkExecuteReturnsCounterB() {
    registers.set(EAX, 0);
    Instruction instruction = new JnzInstruction(null, "EAX", "f1");
    Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
  }

  /**
   * Tests that correct program counter value is returned:
   * Label "f1": 5
   * Register EAX: 1
   * Expected return is therefore 5 as EAX is not zero
   */
  @Test
  void checkExecuteReturnsNewCounterB() {
    registers.set(EAX, 1);
    labels.addLabel("f1",5);
    Instruction instruction = new JnzInstruction(null, "EAX", "f1");
    Assertions.assertEquals(5, instruction.execute(machine));
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality1B() {
    Instruction instruction = new JnzInstruction(null, "EAX","f1");
    Instruction instruction2 = new JnzInstruction(null, "EAX", "f1");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality2B() {
    Instruction instruction = new JnzInstruction("f4", "EAX","f1");
    Instruction instruction2 = new JnzInstruction("f4", "EAX", "f1");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality1B() {
    Instruction instruction = new JnzInstruction(null, "EAX","f1");
    Instruction instruction2 = new JnzInstruction(null, "EBX", "f1");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality2B() {
    Instruction instruction = new JnzInstruction(null, "EAX","f2");
    Instruction instruction2 = new JnzInstruction(null, "EAX", "f1");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality3B() {
    Instruction instruction = new JnzInstruction("f4", "EBX","f1");
    Instruction instruction2 = new JnzInstruction("f5", "EBX", "f1");
    Assertions.assertNotEquals(instruction, instruction2);
  }
}