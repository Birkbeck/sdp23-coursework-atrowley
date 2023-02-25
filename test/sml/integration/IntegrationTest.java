package sml.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;
import java.io.IOException;
import static sml.Registers.Register.*;

class IntegrationTests {
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
   * This is a helper method for the below tests
   * @param inputFilePath
   * @param expectedResult
   * @param reg
   */
  private void validateInputFileWithExpectedResult(String inputFilePath, int expectedResult, RegisterName reg){
    Translator t = new Translator(inputFilePath);
    Machine m = new Machine(new Registers());
    try {
      t.readAndTranslate(m.getLabels(), m.getProgram());
      m.execute();
      Assertions.assertEquals(expectedResult, m.getRegisters().get(reg));
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

}




