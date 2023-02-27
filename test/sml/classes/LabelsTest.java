package sml.classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Labels;
import sml.Machine;
import sml.Registers;

public class LabelsTest {

    Labels labels;
    Labels labels2;

    @BeforeEach
    void setUp() {
        labels = new Labels();
        labels2 = new Labels();
    }

    @AfterEach
    void tearDown() {
        labels = null;
        labels2 = null;
    }

    @Test
    public void testLabelsToString(){
        labels.addLabel("f1",1);
        labels.addLabel("f2",2);
        labels.addLabel("f3",3);
        Assertions.assertEquals("[f1 -> 1, f2 -> 2, f3 -> 3]",labels.toString());
    }

    @Test
    public void testLabelsEquality1(){
        labels.addLabel("f1",1);
        labels.addLabel("f2",2);
        labels.addLabel("f3",3);
        labels2.addLabel("f1",1);
        labels2.addLabel("f2",2);
        labels2.addLabel("f3",3);
        Assertions.assertEquals(labels, labels2);
    }

    @Test
    public void testLabelsEquality2(){
        labels.addLabel("f1",3);
        labels.addLabel("f3",100);
        labels2.addLabel("f1",3);
        labels2.addLabel("f3",100);
        Assertions.assertEquals(labels, labels2);
    }

    @Test
    public void testLabelsInEquality1(){
        labels.addLabel("f1",1);
        labels.addLabel("f2",2);
        labels2.addLabel("f1",1);
        labels2.addLabel("f2",2);
        labels2.addLabel("f3",3);
        Assertions.assertNotEquals(labels, labels2);
    }

    @Test
    public void testLabelsInEquality2(){
        labels.addLabel("f1",2);
        labels.addLabel("f2",2);
        labels2.addLabel("f1",1);
        labels2.addLabel("f2",2);
        Assertions.assertNotEquals(labels, labels2);
    }

}
