package Tests.ADTTest;

import Model.ADT.MyDict;
import Model.Exception.ADTException.MyDictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO:test creation go to class->ctrl shift T->select methods that need testing

class MyDictTest {

    MyDict<String, Integer> dictInt = new MyDict<String, Integer>();


    @BeforeEach
    void setUp() throws MyDictException {
        dictInt.add("a", 5);
        dictInt.add("b", 6);
    }

    @Test
    void add() throws MyDictException {
        dictInt.add("c", 7);
        assertEquals(7,dictInt.getValue("c"));

        dictInt.add("a", 55);
        assertEquals(55,dictInt.getValue("a"));
    }

    @Test
    void getValue() throws MyDictException {
        assertEquals(5,dictInt.getValue("a"));
        assertEquals(6,dictInt.getValue("b"));

        assertThrows(MyDictException.class , () -> {
            dictInt.getValue("d");
        });
    }

    @Test
    void getSize() {
        assertEquals(2, dictInt.getSize());
    }

    @Test
    void isDefined() {
        assertTrue(dictInt.isDefined("a"));
        assertFalse(dictInt.isDefined("aaaa"));
    }

    @Test
    void removeKey() throws MyDictException {
        dictInt.removeKey("a");
        assertThrows(MyDictException.class , () -> {
            dictInt.removeKey("a");
        });
        assertFalse(dictInt.isDefined("a"));

    }

}