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

class DivideInstructionTest {
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


  @Test
  void createsSubInstance() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Assertions.assertEquals("div", instruction.getOpcode());
  }


  @Test
  void validToString() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Assertions.assertEquals("div EAX EBX", instruction.toString());
  }


  @Test
  void checkExecuteReturnsCounter() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Assertions.assertEquals(-1, instruction.execute(machine));
  }

  @Test
  void checkResultAssignsToRegister() {
    registers.set(EAX, 30);
    Assertions.assertEquals(30, machine.getRegisters().get(EAX));
  }

  @Test
  void checkSourceAssignsToRegister() {
    registers.set(EAX, 1);
    registers.set(EBX, 50);
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Assertions.assertEquals(50, machine.getRegisters().get(EBX));
  }

  @Test
  void validDivisionTest1() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void validDivisionTest2() {
    registers.set(EAX, 30);
    registers.set(EBX, 5);
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(6, machine.getRegisters().get(EAX));
  }


  @Test
  void validDivisionTest3() {
    registers.set(EAX, 60);
    registers.set(EBX, 7);
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(8, machine.getRegisters().get(EAX));
  }

  @Test
  void testEquality1() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Instruction instruction2 = new DivInstruction(null,EAX, EBX);
        Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testEquality2() {
    Instruction instruction = new DivInstruction("f1", ESP, EBP);
    Instruction instruction2 = new DivInstruction("f1", ESP, EBP);
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testInequality1() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Instruction instruction2 = new DivInstruction(null,EBX, EAX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality2() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Instruction instruction2 = new AddInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality3() {
    Instruction instruction = new DivInstruction("f1",EAX, EBX);
    Instruction instruction2 = new DivInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }


  // Performs same suite of tests using the constructor that takes three string params
  @Test
  void createsSubInstanceB() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Assertions.assertEquals("div", instruction.getOpcode());
  }

  @Test
  void validToStringB() {
    Instruction instruction = new DivInstruction(null,EAX, EBX);
    Assertions.assertEquals("div EAX EBX", instruction.toString());
  }

  @Test
  void checkExecuteReturnsCounterB() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(-1, instruction.execute(machine));
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
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(50, machine.getRegisters().get(EBX));
  }

  @Test
  void validDivisionTest1B() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void validDivisionTest2B() {
    registers.set(EAX, 30);
    registers.set(EBX, 5);
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(6, machine.getRegisters().get(EAX));
  }


  @Test
  void validDivisionTest3B() {
    registers.set(EAX, 60);
    registers.set(EBX, 7);
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    instruction.execute(machine);
    Assertions.assertEquals(8, machine.getRegisters().get(EAX));
  }

  @Test
  void testEquality1B() {
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new DivInstruction(null,"EAX", "EBX");
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testEquality2B() {
    Instruction instruction = new DivInstruction("f1", "ESP", "EBP");
    Instruction instruction2 = new DivInstruction("f1", "ESP", "EBP");
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testInequality1B() {
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new DivInstruction(null,"EBX", "EAX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality2B() {
    Instruction instruction = new DivInstruction(null,"EAX", "EBX");
    Instruction instruction2 = new AddInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality3B() {
    Instruction instruction = new DivInstruction("f1","EAX", "EBX");
    Instruction instruction2 = new DivInstruction(null,"EAX", "EBX");
    Assertions.assertNotEquals(instruction, instruction2);
  }

}