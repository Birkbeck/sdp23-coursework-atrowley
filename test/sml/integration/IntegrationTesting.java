package sml.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Translator;
import sml.instruction.AddInstruction;

import java.awt.*;
import java.io.IOException;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class IntegrationTesting {
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

  /**
   * Input file contains two moves and one add
   * Expected result: EAX = 7
   *
   * mov EAX 6
   * mov EBX 1
   * add EAX EBX
   */
  @Test
  void testMovAdd1() {
    String testFilePath = "test/sml/test-files/mov-add-only1.sml";
    Translator t = new Translator(testFilePath);
    Machine m = new Machine(new Registers());
    try {
      t.readAndTranslate(m.getLabels(), m.getProgram());
      m.execute();
      Assertions.assertEquals(7, m.getRegisters().get(EAX));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Expected result: EAX = 15
   *
   * mov EAX 6
   * mov EBX 1
   * add EAX EBX
   * mov ECX 9
   * mov EDX -1
   * add ECX EDX
   * add EAX ECX
   */
  @Test
  void testMovAdd2() {
    String testFilePath = "test/sml/test-files/mov-add-only2.sml";
    Translator t = new Translator(testFilePath);
    Machine m = new Machine(new Registers());
    try {
      t.readAndTranslate(m.getLabels(), m.getProgram());
      m.execute();
      Assertions.assertEquals(15, m.getRegisters().get(EAX));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}