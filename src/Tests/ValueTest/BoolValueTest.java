package Tests.ValueTest;

import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolValueTest {

    BoolValue val = new BoolValue(true);
    BoolValue def = new BoolValue();

    @Test
    void getVal() {
        assertEquals(true, val.getVal());
        assertEquals(false, def.getVal());
    }

    @Test
    void testToString() {
        assertEquals("true", val.toString());
        assertEquals("false", def.toString());
    }

    @Test
    void getType() {
        assertEquals(val.getType(), new BoolType());
        assertNotEquals(val.getType(), new Boolean(true));
        assertEquals(def.getType(), new BoolType());
        assertNotEquals(def.getType(), new Boolean(false));
    }
}