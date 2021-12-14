package Tests.ADTTest;

import Model.ADT.MyList;
import Model.Exception.ADTException.MyListException;
import Model.Exception.ADTException.MyStackException;
import Model.Expression.IExp;
import Model.Expression.ValueExp;
import Model.Statement.IStmt;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    MyList<Integer> list = new MyList<Integer>();

    @BeforeEach
    void setUp() throws MyListException {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void getPos() throws MyListException {

        assertEquals(0, list.getPos(1));
        assertEquals(1, list.getPos(2));

        assertThrows(MyListException.class , () -> {
            list.getPos(4);
        });
    }

    @Test
    void add() throws MyListException {
        list.add(4);
        list.add(5);
        assertEquals(5, list.getLen());
        assertEquals(3, list.getPos(4));
        assertEquals(4, list.getPos(5));
    }


    @Test
    void getLen() {
        assertEquals(3, list.getLen());
        list.add(4);
        list.add(5);
        assertEquals(5, list.getLen());
    }

    @Test
    void get() throws MyListException {
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));

        assertThrows(MyListException.class , () -> {
            list.get(3);
        });
    }
    
    @Test
    void remove() throws MyListException {
        list.remove(2);
        assertThrows(MyListException.class , () -> {
            list.remove(2);
        });
        assertEquals(2, list.getLen());

//        MyList<IValue> valL = new MyList<IValue>();
//        IValue e1 = new IntValue(5);
//        IValue e2 = new IntValue(6);
//        IValue e3 = new BoolValue(true);
//        valL.add(e1); valL.add(e2); valL.add(e3);
//
//        valL.remove(e2);
//        assertThrows(MyListException.class , () -> {
//            valL.remove(e2);
//        });

    }

    @Test
    void removePos() throws MyListException {
        list.removePos(2);
        assertThrows(MyListException.class , () -> {
            list.removePos(2);
        });
        assertEquals(2, list.getLen());

    }
    
}