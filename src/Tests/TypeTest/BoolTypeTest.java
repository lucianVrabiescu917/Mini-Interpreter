package Tests.TypeTest;

import Model.Type.BoolType;
import Model.Type.IType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolTypeTest {

    IType typeBool = new BoolType();

    @Test
    void testEquals() {
        assertFalse(!(new BoolType()).equals(typeBool));
        assertFalse((new BoolType()).equals(new Integer(1)));
        assertFalse((new BoolType()).equals(new Boolean(true)));
    }

    @Test
    void testToString() {

        assertEquals(typeBool.toString(),"bool");
    }
}