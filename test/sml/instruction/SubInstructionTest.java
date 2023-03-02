package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

/**
 * This class contains JUNIT tests for testing functionality
 * of the SubInstruction class
 */
class SubInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = Machine.newMachine(Registers.newRegisters());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

//  /**
//   * Validates that instance of instruction with correct opcode is created
//   */
//  @Test
//  void createsSubInstance() {
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Assertions.assertEquals("sub", instruction.getOpcode());
//  }
//
//  @Test
//  void validToString() {
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Assertions.assertEquals("sub EAX EBX", instruction.toString());
//  }
//
//  @Test
//  void checkExecuteReturnsCounter() {
//    registers.set(EAX, 1);
//    registers.set(EBX, 1);
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Assertions.assertEquals(-1, instruction.execute(machine));
//  }
//
//  @Test
//  void checkResultAssignsToRegister() {
//    registers.set(EAX, 25);
//    registers.set(EBX, 1);
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Assertions.assertEquals(25, machine.getRegisters().get(EAX));
//  }
//
//  @Test
//  void checkSourceAssignsToRegister() {
//    registers.set(EAX, 1);
//    registers.set(EBX, 50);
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Assertions.assertEquals(50, machine.getRegisters().get(EBX));
//  }
//
//  /**
//   * Validates that instruction returns expected result on execution
//   */
//  @Test
//  void validSubtractionTest1() {
//    registers.set(EAX, 1);
//    registers.set(EBX, 1);
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    instruction.execute(machine);
//    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
//  }
//
//  /**
//   * Validates that instruction returns expected result on execution
//   */
//  @Test
//  void validSubtractionTest2() {
//    registers.set(EAX, -1);
//    registers.set(EBX, -1);
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    instruction.execute(machine);
//    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
//  }
//
//  /**
//   * Validates that instruction returns expected result on execution
//   */
//  @Test
//  void validSubtractionTest3() {
//    registers.set(EAX, -1);
//    registers.set(EBX, 0);
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    instruction.execute(machine);
//    Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
//  }
//
//  @Test
//  void testEquality1() {
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Instruction instruction2 = new SubInstruction(null,EAX, EBX);
//    Assertions.assertEquals(instruction, instruction2);
//  }
//
//  @Test
//  void testEquality2() {
//    Instruction instruction = new SubInstruction("f1", ESP, EBP);
//    Instruction instruction2 = new SubInstruction("f1", ESP, EBP);
//    Assertions.assertEquals(instruction, instruction2);
//  }
//
//  @Test
//  void testInequality1() {
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Instruction instruction2 = new SubInstruction(null,EBX, EAX);
//    Assertions.assertNotEquals(instruction, instruction2);
//  }
//
//  @Test
//  void testInequality2() {
//    Instruction instruction = new SubInstruction(null,EAX, EBX);
//    Instruction instruction2 = new AddInstruction(null,EAX, EBX);
//    Assertions.assertNotEquals(instruction, instruction2);
//  }
//
//  @Test
//  void testInequality3() {
//    Instruction instruction = new SubInstruction("f1",EAX, EBX);
//    Instruction instruction2 = new SubInstruction(null,EAX, EBX);
//    Assertions.assertNotEquals(instruction, instruction2);
//  }


  // Performs same suite of tests using the constructor that takes three string params

  /**
   * Validates that instance of instruction with correct opcode is created
   */
  @Test
  void createsSubInstanceB() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("sub", instruction.getOpcode());
  }

  /**
   * Validates toString method of the class
   */
  @Test
  void validToStringB() {
    Instruction instruction = new SubInstruction(null,"EAX", "EBX");
    Assertions.assertEquals("sub EAX EBX", instruction.toString());
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


}