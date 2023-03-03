// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml;

/**
 * An interface that defines the template for an InstructionSetFactory that is provided to
 * Translate.java to create instruction objects.
 *
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public interface InstructionSetFactory {
    public Instruction newInstruction(String Opcode, String label, String Operand1, String Operand2);
}

