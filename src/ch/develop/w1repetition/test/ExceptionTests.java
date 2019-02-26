package ch.develop.w1repetition.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExceptionTests {

	@BeforeEach
	void setUp() throws Exception {
		// unused
	}

	@Test
	void test21() throws Exception {
		assertEquals(2.2, ch.develop.w1repetition.exce2.ReadFile.readList("res/w2/File1.txt").get(0), 0.1);
	}

	@Test
	void test22() {
		assertThrows(java.nio.file.NoSuchFileException.class, () -> ch.develop.w1repetition.exce2.ReadFile.readList("res/w2/File3.txt"));
	}
	
	@Test
	void test23() {
		assertThrows(java.nio.file.InvalidPathException.class, () -> ch.develop.w1repetition.exce2.ReadFile.readList("res/w2/File1:txt"));
	}
	
	
	@Test
	void test24() throws Exception {
		assertEquals(3.0, ch.develop.w1repetition.exce2.ReadFile.readList("res/w2/File1.txt").get(1), 0.1);
	}

	@Test
	void test31() {
		assertEquals(3.0, ch.develop.w1repetition.exce3.ReadFile.readList("res/w2/File1.txt").get(1), 0.1);
	}

	@Test
	void test32() {
		assertEquals(3.0, ch.develop.w1repetition.exce3.ReadFile.readList("res/w2/File2.txt").get(1), 0.1);
	}

	@Test
	void test33() {
		assertEquals(8, ch.develop.w1repetition.exce3.ReadFile.readList("res/w2/File2.txt").size());

	}
	
	@Test
	void test41() {
		assertThrows(ch.develop.w1repetition.exce4.ReadException.class, () -> ch.develop.w1repetition.exce4.ReadFile.readList("res/w2/File3.txt"));
	}
	
	@Test
	void test42() throws ch.develop.w1repetition.exce4.ReadException {
		assertEquals(8, ch.develop.w1repetition.exce4.ReadFile.readList("res/w2/File2.txt").size());
	}
	
	@Test
	void test43() throws ch.develop.w1repetition.exce4.ReadException {
		assertEquals(3.0, ch.develop.w1repetition.exce4.ReadFile.readList("res/w2/File2.txt").get(1), 0.1);
	}
	
}
