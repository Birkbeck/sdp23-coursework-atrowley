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
 * of the AddInstruction class
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
class AddInstructionTest {
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

  /**
   * Validates that execute method returns NORMAL_PROGRAM_COUNTER_UPDATE
   */
  @Test
  void checkExecuteReturnsCounter() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
  }

  /**
   * Validates that instance of instruction with correct opcode is created
   */
  @Test
  void createsAddInstance() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("add", instruction.getOpcode());
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void executeValid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, "EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, "EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality1() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality2() {
    Instruction instruction = new AddInstruction("f1", "ESP", "EBP");
    Instruction instruction2 = new AddInstruction("f1", "ESP", "EBP");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality1() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EBX", "EAX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality2() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality3() {
    Instruction instruction = new AddInstruction("f1","EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToString() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("add EAX EBX", instruction.toString());
  }

}