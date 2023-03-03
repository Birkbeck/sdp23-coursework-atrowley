// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * This class contains tests that validate whether the final console output
 * matches the expected output after executing a test sml file
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public class WithConsoleOutputTests {

  private Machine machine;
  private Registers registers;
  private Translator translator;

  @BeforeEach
  void setUp() {
    machine = Machine.getMachine(Registers.getRegisters());
    registers = machine.getRegisters();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
    translator = null;
  }

  /**
   * Expected console output = 1320
   *
   * mov EAX 66
   * mov EBX 10
   * add EAX EAX
   * mul EAX EBX
   * out EAX
   */
  @Test
  void testInputFile1() {
    String testFilePath = "test/sml/test-files/test1.sml";
    validateConsoleOutput(testFilePath, "1320");
  }

  /**
   * Expected console output = 720
   *
   *     mov EAX 6
   *     mov EBX 1
   *     mov ECX 1
   * f3: mul EBX EAX
   *     sub EAX ECX
   *     jnz EAX f3
   *     out EBX
   */
  @Test
  void testInputFile2() {
    String testFilePath = "test/sml/test-files/test2.sml";
    validateConsoleOutput(testFilePath, "720");
  }

  /**
   * Tests add, sub, mul, and div.
   * Expected result = 74
   *
   * L1: mov EAX 22
   *     mov EBX 150
   *     mov ECX 24
   *     mov EDX 2
   *     mov ESP 4
   *     add EAX EBX
   *     sub EAX ECX
   *     mul EAX EDX
   *     div EAX ESP
   *     out EAX
   */
  @Test
  void testInputFile3() {
    String testFilePath = "test/sml/test-files/test3-add-sub-mul-div.sml";
    validateConsoleOutput(testFilePath, "74");
  }

  /**
   * Expected console output = 0
   *
   * f1: mov EAX 2
   *     mov EBX 1
   *     sub EAX EBX
   *     jnz EAX f1
   *     out EAX
   */
  @Test
  void testJnz1() {
    String testFilePath = "test/sml/test-files/jnz-test1.sml";
    validateConsoleOutput(testFilePath, "0");
  }

  /**
   * Expected console output = 5040
   *
   *     mov EAX 6
   *     mov EBX 1
   *     mov ECX 1
   * f3: mul EBX EAX
   *     sub EAX ECX
   *     jnz EAX f3
   *     out EBX
   */
  @Test
  void testJnz2() {
    String testFilePath = "test/sml/test-files/jnz-test2.sml";
    validateConsoleOutput(testFilePath, "5040");
  }

  /**
   * Expected console output = 500
   *
   * mov EAX 500
   * out EAX
   */
  @Test
  void testMovOut1() {
    String testFilePath = "test/sml/test-files/mov-out-only.sml";
    validateConsoleOutput(testFilePath, "500");
  }

  /**
   * This is a helper method for the below tests
   * @param testFilePath filepath of the test sml file
   * @param expectedOutput expected result of the register passed to param "reg"
   */
  private void validateConsoleOutput(String testFilePath, String expectedOutput){
    translator = new Translator(testFilePath);

    try {
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(byteArrayStream));
      machine.execute();
      String outputString = byteArrayStream.toString();
      System.setOut(System.out);

      Assertions.assertEquals(expectedOutput, outputString.trim());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
