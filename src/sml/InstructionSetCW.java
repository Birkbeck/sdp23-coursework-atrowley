package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// TODO [AR] javadocs
public class InstructionSetCW implements InstructionSetFactory {

    @Override
    public Instruction newInstruction(String opcode, String label,String operand1, String operand2) {

        String insClassName = "sml.instruction." + opcode.substring(0, 1).toUpperCase()
                + opcode.substring(1) + "Instruction";

        try {
            Class<?> insClass = Class.forName(insClassName);
            Constructor<?> insConstructor = insClass.getConstructor(String.class, String.class, String.class);
            return (Instruction) insConstructor.newInstance(label, operand1, operand2);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
