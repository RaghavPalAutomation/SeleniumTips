package seleniumtips;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestAllLinks {

	@Test
	public void testLinks() {

		WebDriver driver = new ChromeDriver();

		// Step 1. Navigate to the url
		driver.get("http://opensource.demo.orangehrmlive.com/");

		// Step 2. Find all the links on the webpage and store in a list variable
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// Step 3 : Start a loop with a counter equal to the number of links
		Iterator<WebElement> it = links.iterator();

		String url="";
		HttpURLConnection connection = null;
		int responseCode=200;

		while(it.hasNext()){
			
			// Step 4 : Get url
			url = it.next().getAttribute("href");

			System.out.println("Checking link : "+url);
			
			// Step 5 : Check if url is empty
			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}


			try {
				
				// Step 6 : Create a http connection variable and connect to url
				connection = (HttpURLConnection)(new URL(url).openConnection());

				connection.setRequestMethod("HEAD");

				connection.connect();
				
				// Step 7 : Fetch response code
				responseCode = connection.getResponseCode();

				// Step 8 : Write expectation as per the response fetched
				if(responseCode >= 400){
					System.out.println(url+" is a broken link");
				}
				else{
					System.out.println(url+" is a valid link\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

	}
}
