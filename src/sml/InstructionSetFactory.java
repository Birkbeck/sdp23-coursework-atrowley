package sml;

// TODO [AR] javadocs
public interface InstructionSetFactory {
    public Instruction newInstruction(String Opcode, String label, String Operand1, String Operand2);
}

