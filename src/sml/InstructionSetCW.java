package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *  This class defines the instruction set factory that will be used for the specific
 *  Coursework instruction set.
 */
public class InstructionSetCW implements InstructionSetFactory {

    /**
     * The constructor uses reflection to create an instance of the respective instruction
     * in accordance with the opcode provided. For example:
     * <br><br>
     * "div" = DivInstruction <br>
     * "out" = OutInstruction
     * @param opcode the opcode for the instruction
     * @param label the label for the instruction
     * @param operand1 the first operand of the instruction
     * @param operand2 the second operand of the instruction (if applicable)
     * @return an instruction of the type that corresponds to the opcode
     */
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
