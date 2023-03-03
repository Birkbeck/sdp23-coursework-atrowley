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

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

/**
 * This class contains JUNIT tests for testing functionality
 * of the SubInstruction class
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
class SubInstructionTest {
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
   * Validates that execute method returns NORMAL_PROGRAM_COUNTER_UPDATE
   */
  @Test
  void checkExecuteReturnsCounterB() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(-1, instruction.execute(machine));
  }

  /**
   * Validates that instance of instruction with correct opcode is created
   */
  @Test
  void createsSubInstanceB() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("sub", instruction.getOpcode());
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality1B() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality2B() {
    Instruction instruction = new SubInstruction("f1", "ESP", "EBP");
    Instruction instruction2 = new SubInstruction("f1", "ESP", "EBP");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality1B() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new SubInstruction(null,"EBX", "EAX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality2B() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality3B() {
    Instruction instruction = new SubInstruction("f1","EAX", "EBX");
    Instruction instruction2 = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void validSubtractionTest1B() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void validSubtractionTest2B() {
    registers.set(EAX, -1);
    registers.set(EBX, -1);
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void validSubtractionTest3B() {
    registers.set(EAX, -1);
    registers.set(EBX, 0);
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToStringB() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("sub EAX EBX", instruction.toString());
  }


}