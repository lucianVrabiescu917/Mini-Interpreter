package Tests.TypeTest;

import Model.Type.IType;
import Model.Type.IntType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntTypeTest {

    @Test
    @org.junit.Test
    @DisplayName("Testing equals()")
    public void testEquals() {
        IType typeInt = new IntType();
        assertFalse(!(new IntType()).equals(typeInt));
        assertFalse((new IntType()).equals(new Integer(1)));
        assertFalse((new IntType()).equals(new Boolean(true)));
    }

    @Test
    void testToString() {
        IType typeInt = new IntType();
        assertEquals(typeInt.toString(),"int");
    }
}