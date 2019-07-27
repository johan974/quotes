package com.lct.tutorial.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootApplication
public class QuotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}

	@RestController
	@RequestMapping("/random-quote")
	class QuoteResource{
		@Autowired
		RestTemplate restTemplate;


		@GetMapping
		DeferredResult<String> getQuote(){
			DeferredResult result = new DeferredResult();
			final HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent", "PostmanRuntime/7.15.2");
			headers.set("Accept", "*/*");

			final HttpEntity<String> entity = new HttpEntity<String>(headers);
			QuoteResponse response = restTemplate.postForObject("https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en",entity, QuoteResponse.class);
			result.setResult(response.getQuoteText());
			return result;
		}
	}



	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
@JsonIgnoreProperties(ignoreUnknown = true)
class QuoteResponse{
	String quoteText;

	public QuoteResponse() {
	}

	public String getQuoteText() {
		return quoteText;
	}

	public void setQuoteText(String quoteText) {
		this.quoteText = quoteText;
	}
}
