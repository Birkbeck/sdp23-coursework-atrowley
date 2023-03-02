package sml;

/**
 * An interface that defines the template for an InstructionSetFactory that is provided to
 * Translate.java to create instruction objects.
 */
public interface InstructionSetFactory {
    public Instruction newInstruction(String Opcode, String label, String Operand1, String Operand2);
}

