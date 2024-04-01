import java.util.ArrayList;
import java.util.List;

public class GLA3Main {

	public static void main(String[] args) {
		
		List<String> accountNames = new ArrayList<>();
        accountNames.add("User-01");
        accountNames.add("User-02");
        accountNames.add("User-03");

        List<String> tweets = new ArrayList<>();
        tweets.add("If Mr. X wins the election, there will be more unemployed population!");
        tweets.add("Unemployed population is likely to increase if Mr. X wins the election.");
        tweets.add("The weather is fantastic today. Mr. X may lose the election. However, Mr. X is not bad in my opinion�!");
        
        
        /*
        tweets.add("Spaces are superior to tabs!");
        tweets.add("Why, are. soda tabs sharp?");
        tweets.add("Spaces are better than tabs are!");
        */
        
        /*
        tweets.add("Even Odd Prime...");
        tweets.add("One Two Three");
        tweets.add("Two.. One,     Three!");
        */
        

        BotFinder myFinder = new BotFinder(accountNames, tweets);
        String word="election";
        String accountName="User-01";
        double frequency = myFinder.getAccountWordFrequency(accountName, word);
        System.out.println("\nThe frequency score of the word ("+word+") in the account "+accountName+" is :"+frequency);
        
        
        
        double maxFreqSum=0;
        double curFreqSum=0;
        
        int index=0;
        for(int i=0; i<accountNames.size(); i++)
        {
        	curFreqSum=myFinder.getAccountSimilarity(accountNames.get(i%accountNames.size()),accountNames.get((i+1)%accountNames.size()));
        	System.out.print("\nThe similarity score between "+accountNames.get(i%accountNames.size())+" and "+accountNames.get((i+1)%accountNames.size())+" would be: ");
        	System.out.println(curFreqSum);
        	if(maxFreqSum<curFreqSum)
        	{
        		maxFreqSum=curFreqSum;
        		index=i;
        	}
        }
        System.out.println("\nRobots are: "+accountNames.get(index)+" and "+accountNames.get((index+1)%accountNames.size()));
        
	}

}
