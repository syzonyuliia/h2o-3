package water.persist;

import org.junit.Test;
import org.junit.runner.RunWith;
import water.DKV;
import water.Scope;
import water.TestUtil;
import water.fvec.Frame;
import water.fvec.Vec;
import water.runner.CloudSize;
import water.runner.H2ORunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@CloudSize(1)
@RunWith(H2ORunner.class)
public class PersistH2OTest extends TestUtil {

    @Test
    public void testImportFiles() {
        try {
            Scope.enter();
            ArrayList<String> files = new ArrayList<>();
            ArrayList<String> keys = new ArrayList<>();
            ArrayList<String> empty = new ArrayList<>();
            new PersistH2O().importFiles("h2o://iris", null, files, keys, empty, empty);
            assertTrue(empty.isEmpty());
            assertEquals(1, keys.size());
            Frame f = DKV.getGet(keys.get(0));
            assertNotNull(f);
            Scope.track(f);
        } finally {
            Scope.exit();
        }
    }

    @Test
    public void testCalcaheadTypeMatches() {
        assertTrue(new PersistH2O().calcTypeaheadMatches("h2o://", -1).size() > 0);
        assertEquals(Collections.singletonList("h2o://iris"), new PersistH2O().calcTypeaheadMatches("h2o://iris", -1));
    }

}
