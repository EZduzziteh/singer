import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


//ToDo class

public class BotFinder {

	private Map<String, Map<String, Word>> userMap; // You can change this if you prefer

    /**
     * Create a bot finder with a set of accounts and tweets
     * @param accounts the list of account names
     * @param tweets List of each all tweets per account. tweets.get(i) contains
     *               all tweets for the account at accounts.get(i)
     */
	
    public BotFinder(List<String> accounts, List<String> tweets) {
    	
    	userMap = new HashMap<String, Map<String, Word>>();
    	Map<String, Word> wordMap = new HashMap<String, Word>();
    	
    	//loop through all accounts
    	for(int i = 0; i < accounts.size(); i++) {
    		
    		//removeunwatnted characters
    		String tweetString = tweets.get(i).replaceAll("[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]", "");
    		//split words
    		String[] tweetSplit = tweetString.split(" ");
    		
    		//loop through all elements of the tweetsplit
    		for( int j =0; j<tweetSplit.length; j ++) {
    			wordMap.put(tweets.get(i), new Word(tweetSplit[j],0,0));
    		}
    		System.out.println(wordMap.values());
	    	userMap.put(accounts.get(i).toString(), new HashMap<String, Word>());
		    	
    		
        	
	    }
    	
    	
    	System.out.println(userMap.values());
    	
    }

    /**
     * Find the frequency of use of a specific word by a specific account.
     * @param accountName name of account
     * @param word word to find the frequency of
     * @return the frequency of use for the word by the account
     */
    public double getAccountWordFrequency(String accountName, String word) {
    	//ToDo
    	//find the right tweet by acount name index
    	System.out.println(userMap.get(accountName));
    	
    	
    	
    	//calculate the frequency for every word for a specific account (user)
    	
        return 0.0;
    }

    /**
     * Find the similarity of two accounts based off their word use frequency
     * @param accountOne first account to analyze
     * @param accountTwo second account to analyze
     * @return a similarity score between -1 and 1, representing the account similarity
     */
    public double getAccountSimilarity(String accountOne, String accountTwo) {
    	//ToDo
    	
    	//Calculate similarity score between two accounts
    	
        return 0.0;
    }


    /**
     * Cleans a given string, removing punctuation and converting to lower case
     * @param rawString raw string
     * @return cleaned string
     */
    private static String cleanString(String rawString) {
        return rawString.replaceAll("\\W+", " ").toLowerCase();
    }
}
