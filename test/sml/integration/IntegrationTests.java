package sml.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sml.Registers.Register.*;

/**
 * This class contains tests that validate whether a stated register contains the
 * expected result after executing a test sml file
 */
class IntegrationTests {

  private Machine machine;
  private Registers registers;
  Translator translator;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
    translator = null;
  }

  /**
   * This is a helper method for the below tests
   * @param inputFilePath filepath of the test sml file
   * @param expectedResult expected result of the register passed to param "reg"
   * @param reg register with value that should match that of the expected result
   */
  private void validateInputFileWithExpectedResult(String inputFilePath, int expectedResult, RegisterName reg){
    translator = new Translator(inputFilePath, new InstructionSetCW());
    try {
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      machine.execute();
      Assertions.assertEquals(expectedResult, machine.getRegisters().get(reg));
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    validateInputFileWithExpectedResult(testFilePath, 7, EAX);
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
    validateInputFileWithExpectedResult(testFilePath, 15, EAX);
  }

  /**
   * Expected result: EAX = 120
   *
   * mov EAX 6
   * mov EBX 20
   * mul EAX EBX
   */
  @Test
  void testMovMul1() {
    String testFilePath = "test/sml/test-files/mov-mul-only1.sml";
    validateInputFileWithExpectedResult(testFilePath, 120, EAX);
  }

  /**
   * Expected result: EAX = 300
   * mov EAX 5
   * mov EBX 10
   * mov ECX 6
   * mul EAX EBX
   *
   * mul EAX ECX
   */
  @Test
  void testMovMul2() {
    String testFilePath = "test/sml/test-files/mov-mul-only2.sml";
    validateInputFileWithExpectedResult(testFilePath, 300, EAX);
  }


  /**
   * Expected result: EAX = 15
   * mov EAX 24
   * mov EBX 9
   * sub EAX EBX
   */
  @Test
  void testMovSub1() {
    String testFilePath = "test/sml/test-files/mov-sub-only1.sml";
    validateInputFileWithExpectedResult(testFilePath, 15, EAX);
  }


  /**
   * Expected result: ESP = 20
   *
   * mov EBP 8
   * mov ESP 38
   * mov ESI 55
   * mov EDI 45
   * sub ESP EBP
   * sub ESI EDI
   * sub ESP ESI
   */
  @Test
  void testMovSub2() {
    String testFilePath = "test/sml/test-files/mov-sub-only2.sml";
    validateInputFileWithExpectedResult(testFilePath, 20, ESP);
  }


  /**
   * Expected result: EAX = 4
   * mov EAX 8
   * mov EBX 2
   * div EAX EBX
   */
  @Test
  void testMovDiv1() {
    String testFilePath = "test/sml/test-files/mov-div-only1.sml";
    validateInputFileWithExpectedResult(testFilePath, 4, EAX);
  }

  /**
   * Expected result: EAX = 10
   * mov EAX 120
   * mov EBX 4
   * div EAX EBX
   * mov ECX 24
   * mov EDX 7
   * div ECX EDX
   * div EAX ECX
   */
  @Test
  void testMovDiv2() {
    String testFilePath = "test/sml/test-files/mov-div-only2.sml";
    validateInputFileWithExpectedResult(testFilePath, 10, EAX);
  }

  /**
   * Attempts to run a program where jump instruction
   * tries to jump to a non-existing label.
   *
   *     mov EAX 2
   *     mov EBX 1
   * f1: sub EAX EBX
   *     jnz EAX f2
   *     out EAX
   */
  @Test
  void labelNotFoundTest() {
    String testFilePath = "test/sml/test-files/label-not-found-test.sml";
    translator = new Translator(testFilePath, new InstructionSetCW());

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
    });

    Assertions.assertEquals("Label not found: f2", exc.getMessage());

  }

  /**
   * Attempts to run a program that contains a duplicate label
   *
   *     mov EAX 2
   * f1: mov EBX 1
   * f1: sub EAX EBX
   *     jnz EAX f1
   *     out EAX
   */
  @Test
  void labelDuplicatedTest() {
    String testFilePath = "test/sml/test-files/label-duplicated-test.sml";
    translator = new Translator(testFilePath, new InstructionSetCW());

    RuntimeException exc = assertThrows(RuntimeException.class, ()-> {
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      machine.execute();
    });

    Assertions.assertEquals("Duplicate label occurrence: f1", exc.getMessage());

  }

}




