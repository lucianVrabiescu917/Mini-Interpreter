package Tests.ValueTest;

import Model.Type.IntType;
import Model.Value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {

    IntValue val = new IntValue(55);
    IntValue def = new IntValue();

    @Test
    void getVal() {
        assertEquals(55, val.getVal());
        assertEquals(0, def.getVal());
    }

    @Test
    void testToString() {
        assertEquals("55", val.toString());
        assertEquals("0", def.toString());
    }

    @Test
    void getType() {
        assertEquals(val.getType(), new IntType());
        assertNotEquals(val.getType(), new Integer(55));
        assertEquals(def.getType(), new IntType());
        assertNotEquals(def.getType(), new Integer(0));
    }
}