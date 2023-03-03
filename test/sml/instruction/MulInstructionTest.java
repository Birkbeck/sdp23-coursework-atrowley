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
 * of the MulInstruction class
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
class MulInstructionTest {
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
   * Validates that instance of instruction with correct opcode is created
   */
  @Test
  void createsMulInstanceB() {
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("mul", instruction.getOpcode());
  }

  /**
   * Validates that execute method returns NORMAL_PROGRAM_COUNTER_UPDATE
   */
  @Test
  void checkExecuteReturnsCounterB() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality1B() {
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality2B() {
    Instruction instruction = new MulInstruction("f1", "ESP", "EBP");
    Instruction instruction2 = new MulInstruction("f1", "ESP", "EBP");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality1B() {
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new MulInstruction(null,"EBX", "EAX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality2B() {
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality3B() {
    Instruction instruction = new MulInstruction("f1","EAX", "EBX");
    Instruction instruction2 = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void validMultiplicationTest1B() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void validMultiplicationTest2B() {
    registers.set(EAX, 5);
    registers.set(EBX, -2);
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(-10, machine.getRegisters().get(EAX));
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void validMultiplicationTest3B() {
    registers.set(EAX, -5);
    registers.set(EBX, -6);
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(30, machine.getRegisters().get(EAX));
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToStringB() {
    Instruction instruction = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("mul EAX EBX", instruction.toString());
  }
}