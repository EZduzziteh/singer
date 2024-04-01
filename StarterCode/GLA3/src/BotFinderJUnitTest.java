import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/* 
 * 
 * 
 * 
 * Please do not change anything from this JUnitTest file
 * 
 * 
 * 
 * */
class BotFinderJUnitTest {

	static List<String> accountNames;
	static List<String> tweets;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        tweets = new ArrayList<>();
        tweets.add("Lorem ipsum dolor sit amet, dicat augue vel eu, his et altera habemus. Ut nam erroribus liberavisse, vero expetendis vel id. At soleat tritani forensibus duo, ad accusam assueverit eam, senserit appellantur nam cu. Mea iusto iracundia te, eius integre conceptam ea sit. Velit graeci pertinax nec id. Eos ei elit decore oblique, nam an dicant labitur, has tale omnes eu.");
        tweets.add("Ex mei veri equidem, et pri doctus noluisse, vis ne alia vidisse erroribus. Ne nam libris dissentias. Et mutat docendi ius. Duo in harum discere splendide. Eu diam suscipit percipitur vix, ad maiorum consectetuer mea. Inermis lucilius qualisque cu his, ius at sumo populo debitis. Clita detracto sea ne.");
        tweets.add("Te per electram disputando consequuntur, Lorem ipsum dolor sit amet, Ut nam erroribus liberavisse, vero expetendis vel id. dicat augue vel eu, his et altera habemus. At soleat tritani forensibus duo, ad accusam assueverit eam, senserit appellantur nam cu. Mea iusto iracundia te, eius integre conceptam ea sit. Velit graeci pertinax nec id. Eos ei elit decore oblique, nam an dicant labitur, has tale omnes eu. Eos ea falli similique. ");  
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test1()
	{
		BotFinder myFinder = new BotFinder(accountNames, tweets);
		double tabsFrequency = myFinder.getAccountWordFrequency("User-01", "vel");
		double groundTruth=0.0328;
		assertEquals(tabsFrequency, groundTruth, 0.0002d);
	}
	
	@Test
	void test2()
	{
		BotFinder myFinder = new BotFinder(accountNames, tweets);
		double curFreqSum=myFinder.getAccountSimilarity(accountNames.get(0),accountNames.get(1));
		double groundTruth=0.0047;
		assertEquals(curFreqSum, groundTruth, 0.0002d);
	}
	@Test
	void test3()
	{
		BotFinder myFinder = new BotFinder(accountNames, tweets);
		double curFreqSum=myFinder.getAccountSimilarity(accountNames.get(1),accountNames.get(2));
		double groundTruth=0.0041;
		assertEquals(curFreqSum, groundTruth, 0.0002d);
	}
	
	@Test
	void test4()
	{
		BotFinder myFinder = new BotFinder(accountNames, tweets);
		double curFreqSum=myFinder.getAccountSimilarity(accountNames.get(2),accountNames.get(0));
		double groundTruth=0.0183;
		assertEquals(curFreqSum, groundTruth, 0.0002d);
	}
	
	@Test
	void test5()
	{
		double maxFreqSum=0;
        double curFreqSum=0;
        BotFinder myFinder = new BotFinder(accountNames, tweets);
        int index=0;
        for(int i=0; i<accountNames.size(); i++)
        {
        	curFreqSum=myFinder.getAccountSimilarity(accountNames.get(i%accountNames.size()),accountNames.get((i+1)%accountNames.size()));
        	if(maxFreqSum<curFreqSum)
        	{
        		maxFreqSum=curFreqSum;
        		index=i;
        	}
        }
        
        String bot1="User-03";
        String bot2="User-01";
        
        assertEquals(bot1, accountNames.get(index));
        assertEquals(bot2, accountNames.get((index+1)%accountNames.size()));
	}
	
	
	@Test
	void test6()
	{
		List<String> accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        List<String> tweets = new ArrayList<>();
        tweets.add("Spaces are superior to tabs!");
        tweets.add("Why, are. soda tabs sharp?");
        tweets.add("Spaces are better than tabs are!");
        
        BotFinder myFinder = new BotFinder(accountNames, tweets);
        double tabsFrequency = myFinder.getAccountWordFrequency("User-01", "are");
		double groundTruth=0.2;
		assertEquals(tabsFrequency, groundTruth, 0.0002d);
        
	}
	
	@Test
	void test7()
	{
		List<String> accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        List<String> tweets = new ArrayList<>();
        tweets.add("Spaces are superior to tabs!");
        tweets.add("Why, are. soda tabs sharp?");
        tweets.add("Spaces are better than tabs are!");
        
        BotFinder myFinder = new BotFinder(accountNames, tweets);
        double tabsFrequency = myFinder.getAccountWordFrequency("User-03", "are");
		double groundTruth=0.3333;
		assertEquals(tabsFrequency, groundTruth, 0.0002d);
        
	}
	
	
	@Test
	void test8()
	{
		List<String> accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        List<String> tweets = new ArrayList<>();
        tweets.add("I am Robot!");
        tweets.add("I am Human...");
        tweets.add("I am also Robot!");
        
        
        BotFinder myFinder = new BotFinder(accountNames, tweets);
        double tabsFrequency = myFinder.getAccountWordFrequency("User-01", "robot");
		double groundTruth=0.3333;
		assertEquals(tabsFrequency, groundTruth, 0.0002d);
        
	}
	
	@Test
	void test9()
	{
		List<String> accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        List<String> tweets = new ArrayList<>();
        tweets.add("I am Robot!");
        tweets.add("I am Human...");
        tweets.add("I am also Robot!");
        
        
        BotFinder myFinder = new BotFinder(accountNames, tweets);
        double curFreqSum=myFinder.getAccountSimilarity(accountNames.get(0),accountNames.get(1));
		double groundTruth=0.2222;
		assertEquals(curFreqSum, groundTruth, 0.0002d);
        
	}
	
	@Test
	void test10()
	{
		List<String> accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        List<String> tweets = new ArrayList<>();
        tweets.add("I am Robot!");
        tweets.add("I am Human...");
        tweets.add("I am also Robot!");
        
        BotFinder myFinder = new BotFinder(accountNames, tweets);
        double curFreqSum=myFinder.getAccountSimilarity(accountNames.get(2),accountNames.get(0));
		double groundTruth=0.25;
		assertEquals(curFreqSum, groundTruth, 0.0002d);
	}
	
	

}
