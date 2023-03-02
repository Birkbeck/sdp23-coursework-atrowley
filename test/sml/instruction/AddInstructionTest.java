package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
import static sml.Registers.Register.*;

/**
 * This class contains JUNIT tests for testing functionality
 * of the AddInstruction class
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
   * Validates that instance of instruction with correct opcode is created
   */
  @Test
  void createsAddInstanceB() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("add", instruction.getOpcode());
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToStringB() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("add EAX EBX", instruction.toString());
  }

  /**
   * Validates that execute method returns NORMAL_PROGRAM_COUNTER_UPDATE
   */
  @Test
  void checkExecuteReturnsCounterB() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
  }

  /**
   * Validates that instruction returns expected result on execution
   */
  @Test
  void executeValidB() {
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
  void executeValidTwoB() {
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
  void testEquality1B() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testEquality2B() {
    Instruction instruction = new AddInstruction("f1", "ESP", "EBP");
    Instruction instruction2 = new AddInstruction("f1", "ESP", "EBP");
    Assertions.assertEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality1B() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EBX", "EAX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality2B() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  /**
   * Validates equals method of class
   */
  @Test
  void testInequality3B() {
    Instruction instruction = new AddInstruction("f1","EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

}