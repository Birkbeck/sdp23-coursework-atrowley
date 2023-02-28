package sml;

import sml.instruction.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import static sml.Registers.Register;

/**
 * This class reads a set of sml instructions from a file and creates a respective
 * instruction class for each line of instruction which is then added to the program
 * ArrayList. It also stores any instruction labels in a Labels HashMap along with
 * their respective address within the program (program ArrayList index).
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author BBK staff member / Adam Rowley (GitHub username atrowley)
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {

        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    // TODO: add code for all other types of instructions
    //  [COMPLETED]

    // TODO: Then, replace the switch by using the Reflection API
    //  [STILL TO DO]

    // TODO: Next, use dependency injection to allow this machine class
    //       to work with different sets of opcodes (different CPUs)
    //       [STILL TO DO]
    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String operand1 = scan();
        String operand2 = scan();

        switch (opcode) {

            case AddInstruction.OP_CODE -> {
                return new AddInstruction(label, Register.valueOf(operand1), Register.valueOf(operand2));
            }

            case MulInstruction.OP_CODE -> {
                return new MulInstruction(label, Register.valueOf(operand1), Register.valueOf(operand2));
            }

            case SubInstruction.OP_CODE -> {
                return new SubInstruction(label, Register.valueOf(operand1), Register.valueOf(operand2));
            }

            case DivInstruction.OP_CODE -> {
                return new DivInstruction(label, Register.valueOf(operand1), Register.valueOf(operand2));
            }

            case MovInstruction.OP_CODE -> {
                return new MovInstruction(label, Register.valueOf(operand1), Integer.parseInt(operand2));
            }

            case OutInstruction.OP_CODE -> {
                return new OutInstruction(label, Register.valueOf(operand1));
            }

            case JnzInstruction.OP_CODE -> {
                return new JnzInstruction(label, Register.valueOf(operand1), operand2);
            }

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        return null;
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}