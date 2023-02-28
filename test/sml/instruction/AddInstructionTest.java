package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;
import static sml.Registers.Register.EBP;

class AddInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void testEquality1() {
    Instruction instruction = new AddInstruction(null,EAX, EBX);
    Instruction instruction2 = new AddInstruction(null,EAX, EBX);
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testEquality2() {
    Instruction instruction = new AddInstruction("f1", ESP, EBP);
    Instruction instruction2 = new AddInstruction("f1", ESP, EBP);
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testInequality1() {
    Instruction instruction = new AddInstruction(null,EAX, EBX);
    Instruction instruction2 = new AddInstruction(null,EBX, EAX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality2() {
    Instruction instruction = new AddInstruction(null,EAX, EBX);
    Instruction instruction2 = new MulInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality3() {
    Instruction instruction = new AddInstruction("f1",EAX, EBX);
    Instruction instruction2 = new AddInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }


  // Performs same suite of tests using the constructor that takes three string params
  @Test
  void executeValidB() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, "EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwoB() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, "EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void testEquality1B() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testEquality2B() {
    Instruction instruction = new AddInstruction("f1", "ESP", "EBP");
    Instruction instruction2 = new AddInstruction("f1", "ESP", "EBP");
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testInequality1B() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EBX", "EAX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality2B() {
    Instruction instruction = new AddInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new MulInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality3B() {
    Instruction instruction = new AddInstruction("f1","EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

}