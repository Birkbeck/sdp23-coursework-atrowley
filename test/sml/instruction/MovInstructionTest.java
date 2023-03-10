// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

/**
 * This class contains JUNIT tests for testing functionality
 * of the MovInstruction class
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
class MovInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = Machine.getMachine(Registers.getRegisters());
    registers = machine.getRegisters();
    //...
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
  void createsSubInstanceB() {
    Instruction instruction = new MovInstruction(null,"EAX", "5");
    Assertions.assertEquals("mov", instruction.getOpcode());
  }

  /**
   * Validates that execute method returns NORMAL_PROGRAM_COUNTER_UPDATE
   */
  @Test
  void checkExecuteReturnsCounterB() {
    Instruction instruction = new MovInstruction(null,"EAX", "15");
    Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
  }

  /**
   * Validates that instruction executes correct move
   */
  @Test
  void checkMoveExecution1B() {
    Instruction instruction = new MovInstruction(null,"EAX", "20");
    instruction.execute(machine);
    Assertions.assertEquals(20, machine.getRegisters().get(EAX));
  }

  /**
   * Validates that instruction executes correct move
   */
  @Test
  void checkMoveExecution2B() {
    Instruction instruction = new MovInstruction(null,"EBX", "25");
    instruction.execute(machine);
    Assertions.assertEquals(25, machine.getRegisters().get(EBX));
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality1B() {
    Instruction instruction = new MovInstruction(null, "EAX","8");
    Instruction instruction2 = new MovInstruction(null, "EAX", "8");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality2B() {
    Instruction instruction = new MovInstruction("f1", "EBX", "9");
    Instruction instruction2 = new MovInstruction("f1", "EBX", "9");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality1B() {
    Instruction instruction = new MovInstruction(null, "EAX", "8");
    Instruction instruction2 = new MovInstruction(null, "EBX", "8");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality2B() {
    Instruction instruction = new MovInstruction(null, "EAX", "8");
    Instruction instruction2 = new MovInstruction(null, "EAX", "9");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality3B() {
    Instruction instruction = new MovInstruction("f1", "EAX", "8");
    Instruction instruction2 = new MovInstruction(null, "EAX", "8");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToStringB() {
    Instruction instruction = new MovInstruction(null,"EAX", "10");
    Assertions.assertEquals("mov EAX 10", instruction.toString());
  }
}