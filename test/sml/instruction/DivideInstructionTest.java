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
  void createsSubInstance() {
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    Assertions.assertEquals("div", instruction.getOpcode());
  }


  @Test
  void validToString() {
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    Assertions.assertEquals("div EAX EBX", instruction.toString());
  }


  @Test
  void checkExecuteReturnsCounter() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
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
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    Assertions.assertEquals(50, machine.getRegisters().get(EBX));
  }

  @Test
  void validDivisionTest1() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void validDivisionTest2() {
    registers.set(EAX, 30);
    registers.set(EBX, 5);
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(6, machine.getRegisters().get(EAX));
  }


  @Test
  void validDivisionTest3() {
    registers.set(EAX, 60);
    registers.set(EBX, 7);
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(8, machine.getRegisters().get(EAX));
  }

  @Test
  void testEquality1() {
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    Instruction instruction2 = new DivideInstruction(null,EAX, EBX);
        Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testEquality2() {
    Instruction instruction = new DivideInstruction("f1", ESP, EBP);
    Instruction instruction2 = new DivideInstruction("f1", ESP, EBP);
    Assertions.assertEquals(instruction, instruction2);
  }

  @Test
  void testInequality1() {
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    Instruction instruction2 = new DivideInstruction(null,EBX, EAX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality2() {
    Instruction instruction = new DivideInstruction(null,EAX, EBX);
    Instruction instruction2 = new AddInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

  @Test
  void testInequality3() {
    Instruction instruction = new DivideInstruction("f1",EAX, EBX);
    Instruction instruction2 = new DivideInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }

}