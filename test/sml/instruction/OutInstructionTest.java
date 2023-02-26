package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class OutInstructionTest {
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
  void createsOutInstance() {
    Instruction instruction = new OutInstruction(null,EAX);
    Assertions.assertEquals("out", instruction.getOpcode());
  }


  @Test
  void validToString() {
    Instruction instruction = new OutInstruction(null,EAX);
    Assertions.assertEquals("out EAX", instruction.toString());
  }

  // TODO [AR]: remove if superfluous
  @Test
  void checkExecuteReturnsCounter() {
    Instruction instruction = new OutInstruction(null,EAX);
    Assertions.assertEquals(-1, instruction.execute(machine));
  }


  @Test
  void checkOutExecution1() {
    registers.set(EAX,100);
    Instruction instruction = new OutInstruction(null,EAX);

    ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArrayStream));
    instruction.execute(machine);
    String outputString = byteArrayStream.toString();
    System.setOut(System.out);

    Assertions.assertEquals("100", outputString.trim());
  }


  @Test
  void checkOutExecution2() {
    registers.set(EBX,-1000);
    Instruction instruction = new OutInstruction(null,EBX);

    ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(byteArrayStream));
    instruction.execute(machine);
    String outputString = byteArrayStream.toString();
    System.setOut(System.out);

    Assertions.assertEquals("-1000", outputString.trim());
  }


  @Test
  void testEquality1() {
    Instruction instruction = new OutInstruction(null,EAX);
    Instruction instruction2 = new OutInstruction(null,EAX);
    Assertions.assertEquals(instruction, instruction2);
  }


  @Test
  void testEquality2() {
    Instruction instruction = new OutInstruction("f1",EAX);
    Instruction instruction2 = new OutInstruction("f1",EAX);
    Assertions.assertEquals(instruction, instruction2);
  }


  @Test
  void testInequality1() {
    Instruction instruction = new OutInstruction(null,EAX);
    Instruction instruction2 = new OutInstruction(null,EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }


  @Test
  void testInequality2() {
    Instruction instruction = new OutInstruction(null,EAX);
    Instruction instruction2 = new AddInstruction(null,EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction2);
  }


  @Test
  void testInequality3() {
    Instruction instruction = new OutInstruction("f1",EAX);
    Instruction instruction2 = new OutInstruction(null,EAX);
    Assertions.assertNotEquals(instruction, instruction2);
  }


}