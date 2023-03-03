package sml.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.InstructionSetCoursework;
import sml.InstructionSetFactory;
import sml.instruction.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains tests that validate functionality of InstructionSetFactory implementations.
 *
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public class InstructionSetFactoryTest {

  // InstructionSetFactory to test
  InstructionSetFactory isf = new InstructionSetCoursework();

  /**
   * Validates that an AddInstruction is created
   */
  @Test
  void createsAddInstruction(){
    Instruction instruction = isf.newInstruction("add",null,"EAX", "EBX");
    assertTrue(instruction instanceof AddInstruction);
  }

  /**
   * Validates that a DivInstruction is created
   */
  @Test
  void createsDivInstruction(){
    Instruction instruction = isf.newInstruction("div",null,"EAX", "EBX");
    assertTrue(instruction instanceof DivInstruction);
  }

  /**
   * Validates that a JnzInstruction is created
   */
  @Test
  void createsJnzInstruction(){
    Instruction instruction = isf.newInstruction("jnz",null,"EAX", "f1");
    assertTrue(instruction instanceof JnzInstruction);
  }

  /**
   * Validates that a MulInstruction is created
   */
  @Test
  void createsMulInstruction(){
    Instruction instruction = isf.newInstruction("mul",null,"EAX", "EBX");
    assertTrue(instruction instanceof MulInstruction);
  }

  /**
   * Validates that an OutInstruction is created
   */
  @Test
  void createsOutInstruction(){
    Instruction instruction = isf.newInstruction("out",null,"EAX", null);
    assertTrue(instruction instanceof OutInstruction);
  }

  /**
   * Validates that an SubInstruction is created
   */
  @Test
  void createsSubInstruction(){
    Instruction instruction = isf.newInstruction("sub",null,"EAX", "EBX");
    assertTrue(instruction instanceof SubInstruction);
  }


  /**
   * Validates that a RuntimeException is thrown with applicable message when a non-existent
   * register name is used in Instruction construction
   */
  @Test
  void checkIncorrectRegisterReturnsException(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("mul",null,"BBX", "EBX");
    });
    Assertions.assertEquals("Register does not exist: BBX", exc.getMessage());
  }

  /**
   * Validates that a RuntimeException is thrown with applicable message when a null
   * register name is used in Instruction construction
   */
  @Test
  void checkIncorrectRegisterReturnsException2(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("mul",null,"EAX", null);
    });
    Assertions.assertEquals("A register value for opcode 'mul' is missing (null)", exc.getMessage());
  }

  /**
   * Validates that a RuntimeException is thrown with applicable message when a null
   * register argument is used in Instruction construction
   */
  @Test
  void checkIncorrectRegisterReturnsException3(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("jnz",null,null, "f1");
    });
    Assertions.assertEquals("A register value for opcode 'jnz' is missing (null)", exc.getMessage());
  }

  /**
   * Validates that a RuntimeException is thrown with applicable message when a null
   * register argument is used in Instruction construction
   */
  @Test
  void checkIncorrectRegisterReturnsException4(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("out",null,null, "f1");
    });
    Assertions.assertEquals("A register value for opcode 'out' is missing (null)", exc.getMessage());
  }

  /**
   * Validates that a RuntimeException is thrown with applicable message when a null
   * register argument is used in Instruction construction
   */
  @Test
  void checkIncorrectRegisterReturnsException5(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("mov",null,null, "10");
    });
    Assertions.assertEquals("A register value for opcode 'mov' is missing (null)", exc.getMessage());
  }

  /**
   * Validates that a RuntimeException is thrown with applicable message when a non-existent
   * register name is used in Instruction construction
   */
  @Test
  void checkIncorrectValueReturnsException(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("mov",null,"EBX", "word");
    });
    Assertions.assertEquals("Invalid value for mov instruction: 'word'", exc.getMessage());
  }

  /**
   * Validates that a RuntimeException is thrown with applicable message when a non-existent
   * register name is used in Instruction construction
   */
  @Test
  void checkIncorrectValueReturnsException2(){

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      Instruction instruction = isf.newInstruction("mov",null,"EBX", null);
    });
    Assertions.assertEquals("Invalid value for mov instruction: 'null'", exc.getMessage());
  }

}
