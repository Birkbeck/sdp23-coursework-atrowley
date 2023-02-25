package sml.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Translator;
import sml.instruction.OutInstruction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static sml.Registers.Register.EBX;

public class WithOutputTests {

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
     * mov EAX 500
     * out EAX
     */
    @Test
    void testMovOut1() {
        String testFilePath = "test/sml/test-files/mov-out-only.sml";
        translator = new Translator(testFilePath);

        try {
            translator.readAndTranslate(machine.getLabels(), machine.getProgram());
            ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(byteArrayStream));
            machine.execute();
            String outputString = byteArrayStream.toString();
            System.setOut(System.out);

            Assertions.assertEquals("500", outputString.trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
