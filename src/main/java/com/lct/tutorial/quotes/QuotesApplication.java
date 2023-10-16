package com.lct.tutorial.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class QuotesApplication {
	private static final String QuoteUrl = "https://api.forismatic.com";

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}

	@RestController
	@RequestMapping("/random-quote")
	class QuoteResource{
		@Autowired
		private WebClient webClient;


		@GetMapping
		Mono<QuoteResponse> getQuote(){
//			final HttpHeaders headers = new HttpHeaders();
//			headers.set("User-Agent", "PostmanRuntime/7.15.2");
//			headers.set("Accept", "*/*");

			return webClient.get()
					.uri("/api/1.0/?method=getQuote&format=json&lang=en")
					.retrieve()
					.bodyToMono(QuoteResponse.class);
		}
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl(QuoteUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
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
