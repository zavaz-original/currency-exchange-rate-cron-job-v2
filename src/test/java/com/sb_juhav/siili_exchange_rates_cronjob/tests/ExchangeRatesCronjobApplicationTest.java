package com.sb_juhav.siili_exchange_rates_cronjob.tests;


/**
 * @author Juha Valimaki, Siili Candidate by Virnex 2022
 *
 */

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.sb_juhav.siili_exchange_rates_cronjob.ExchangeRatesCronJobApplication;

class ExchangeRatesCronjobApplicationTest {

	@Test
	void testExchangeRatesCronJobApplicationConstructorDoesNotThrowException() {
		String apikey = "dummy_api_key";
		ExchangeRatesCronJobApplication exchangeRatesCronJobApplication;
		try {
			exchangeRatesCronJobApplication = new ExchangeRatesCronJobApplication(apikey);
			assertNotNull(exchangeRatesCronJobApplication);
		} catch (Exception e) {
			fail("Exception from constructor ExchangeRatesCronJobApplication(apikey)");
		}
	}

}
