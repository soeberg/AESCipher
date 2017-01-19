package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
				AESCipherTests.class,
				BinUtilTests.class, 
				KeyExpanderTests.class,
				SBoxTests.class,
				AESDecipherTests.class})
public class AllTests {
}
