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

class MultiplyInstructionTest {
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
  void createsMulInstance() {
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    Assertions.assertEquals("mul", instruction.getOpcode());
  }


  @Test
  void validToString() {
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    Assertions.assertEquals("mul EAX EBX", instruction.toString());
  }


  @Test
  void checkExecuteReturnsCounter() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    Assertions.assertEquals(-1, instruction.execute(machine));
  }

  @Test
  void checkResultAssignsToRegister() {
    registers.set(EAX, 25);
    registers.set(EBX, 1);
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    Assertions.assertEquals(25, machine.getRegisters().get(EAX));
  }

  @Test
  void checkSourceAssignsToRegister() {
    registers.set(EAX, 1);
    registers.set(EBX, 50);
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    Assertions.assertEquals(50, machine.getRegisters().get(EBX));
  }

  @Test
  void validMultiplicationTest1() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void validMultiplicationTest2() {
    registers.set(EAX, 5);
    registers.set(EBX, -2);
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-10, machine.getRegisters().get(EAX));
  }

  @Test
  void validMultiplicationTest3() {
    registers.set(EAX, -5);
    registers.set(EBX, -6);
    Instruction instruction = new MultiplyInstruction(null,EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(30, machine.getRegisters().get(EAX));
  }

}