package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class JnzInstructionTest {
  private Machine machine;
  private Registers registers;
  private Labels labels;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    labels = machine.getLabels();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
    labels = null;
  }


  @Test
  void createsJnzInstance() {
    Instruction instruction = new JnzInstruction(null,EAX, "f1");
    Assertions.assertEquals("jnz", instruction.getOpcode());
  }


  @Test
  void validToString() {
    Instruction instruction = new JnzInstruction(null,EAX, "f1");
    Assertions.assertEquals("jnz EAX f1", instruction.toString());
  }

  @Test
  void validToString2() {
    Instruction instruction = new JnzInstruction("f2", EAX, "f1");
    Assertions.assertEquals("f2: jnz EAX f1", instruction.toString());
  }


  @Test
  void checkExecuteReturnsCounter() {
    registers.set(EAX, 0);
    Instruction instruction = new JnzInstruction(null, EAX, "f1");
    Assertions.assertEquals(-1, instruction.execute(machine));
  }

  /**
   * Test sets following:
   * Label "f1": 5
   * Register EAX: 1
   * Expected return is therefore 5 as EAX is not zero
   */
  @Test
  void checkExecuteReturnsNewCounter() {
    registers.set(EAX, 1);
    labels.addLabel("f1",5);
    Instruction instruction = new JnzInstruction(null, EAX, "f1");
    Assertions.assertEquals(5, instruction.execute(machine));
  }


  @Test
  void testEquality1() {
    Instruction instruction = new JnzInstruction(null, EAX,"f1");
    Instruction instruction2 = new JnzInstruction(null, EAX, "f1");
    Assertions.assertEquals(instruction, instruction2);
  }


  @Test
  void testEquality2() {
    Instruction instruction = new JnzInstruction("f4", EAX,"f1");
    Instruction instruction2 = new JnzInstruction("f4", EAX, "f1");
    Assertions.assertEquals(instruction, instruction2);
  }


  @Test
  void testInequality1() {
    Instruction instruction = new JnzInstruction(null, EAX,"f1");
    Instruction instruction2 = new JnzInstruction(null, EBX, "f1");
    Assertions.assertNotEquals(instruction, instruction2);
  }


  @Test
  void testInequality2() {
    Instruction instruction = new JnzInstruction(null, EAX,"f2");
    Instruction instruction2 = new JnzInstruction(null, EAX, "f1");
    Assertions.assertNotEquals(instruction, instruction2);
  }


  @Test
  void testInequality3() {
    Instruction instruction = new JnzInstruction("f4", EBX,"f1");
    Instruction instruction2 = new JnzInstruction("f5", EBX, "f1");
    Assertions.assertNotEquals(instruction, instruction2);
  }


}