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

class MoveInstructionTest {
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
    Instruction instruction = new MoveInstruction(null,EAX, 5);
    Assertions.assertEquals("mov", instruction.getOpcode());
  }


  @Test
  void validToString() {
    Instruction instruction = new MoveInstruction(null,EAX, 10);
    Assertions.assertEquals("mov EAX 10", instruction.toString());
  }


  @Test
  void checkExecuteReturnsCounter() {
    Instruction instruction = new MoveInstruction(null,EAX, 15);
    Assertions.assertEquals(-1, instruction.execute(machine));
  }


  @Test
  void checkMoveExecution1() {
    Instruction instruction = new MoveInstruction(null,EAX, 20);
    instruction.execute(machine);
    Assertions.assertEquals(20, machine.getRegisters().get(EAX));
  }


  @Test
  void checkMoveExecution2() {
    Instruction instruction = new MoveInstruction(null,EBX, 25);
    instruction.execute(machine);
    Assertions.assertEquals(25, machine.getRegisters().get(EBX));
  }

  @Test
  void testEquality1() {
    Instruction instruction = new MoveInstruction(null, EAX,8);
    Instruction instruction2 = new MoveInstruction(null, EAX, 8);
    Assertions.assertEquals(instruction, instruction2);
  }


  @Test
  void testEquality2() {
    Instruction instruction = new MoveInstruction("f1", EBX, 9);
    Instruction instruction2 = new MoveInstruction("f1", EBX, 9);
    Assertions.assertEquals(instruction, instruction2);
  }


  @Test
  void testInequality1() {
    Instruction instruction = new MoveInstruction(null, EAX, 8);
    Instruction instruction2 = new MoveInstruction(null, EBX, 8);
    Assertions.assertNotEquals(instruction, instruction2);
  }


  @Test
  void testInequality2() {
    Instruction instruction = new MoveInstruction(null, EAX, 8);
    Instruction instruction2 = new MoveInstruction(null, EAX, 9);
    Assertions.assertNotEquals(instruction, instruction2);
  }


  @Test
  void testInequality3() {
    Instruction instruction = new MoveInstruction("f1", EAX, 8);
    Instruction instruction2 = new MoveInstruction(null, EAX, 8);
    Assertions.assertNotEquals(instruction, instruction2);
  }


}