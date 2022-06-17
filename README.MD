Virnex Oy / juha valimaki 2022-06-14

# User Guide for ExchangeRatesCronJobApplication App

### Examples of output before the query limit for a free account got exceeded:

ExchangeRateResponseDTO [from=USD, to=EUR, fromAmount=1, toAmount=0.94182, exchangeRate=0.94182]
ExchangeRateResponseDTO [from=USD, to=SEK, fromAmount=1, toAmount=9.917599, exchangeRate=9.917599]
ExchangeRateResponseDTO [from=EUR, to=USD, fromAmount=1, toAmount=1.061774, exchangeRate=1.061774]
ExchangeRateResponseDTO [from=EUR, to=SEK, fromAmount=1, toAmount=10.530246, exchangeRate=10.530246]
ExchangeRateResponseDTO [from=SEK, to=USD, fromAmount=1, toAmount=0.100831, exchangeRate=0.100831]
ExchangeRateResponseDTO [from=SEK, to=EUR, fromAmount=1, toAmount=0.094965, exchangeRate=0.094965]

-> I added later the date and time as the first column.
-> I wrote and use now an alternative toString() method that gives the successful output in JSON format.

### Examples of output after the apikey had exceeded daily&monthly request limit for a free account:
INFO: fetch url: http://api.apilayer.com/exchangerates_data/convert?from=USD&to=EUR&amount=1&apikey=HZOwa1dYHjD4MEEA0KNPsOiOEwqhQcvt
> 429 Too Many Requests: "{"message":"You have exceeded your daily\/monthly API rate limit. Please review and upgrade your subscription plan at https:\/\/promptapi.com\/subscriptions to continue."}"
INFO: fetch url: http://api.apilayer.com/exchangerates_data/convert?from=USD&to=SEK&amount=1&apikey=HZOwa1dYHjD4MEEA0KNPsOiOEwqhQcvt
> 429 Too Many Requests: "{"message":"You have exceeded your daily\/monthly API rate limit. Please review and upgrade your subscription plan at https:\/\/promptapi.com\/subscriptions to continue."}"
INFO: fetch url: http://api.apilayer.com/exchangerates_data/convert?from=EUR&to=USD&amount=1&apikey=HZOwa1dYHjD4MEEA0KNPsOiOEwqhQcvt
> 429 Too Many Requests: "{"message":"You have exceeded your daily\/monthly API rate limit. Please review and upgrade your subscription plan at https:\/\/promptapi.com\/subscriptions to continue."}"
INFO: fetch url: http://api.apilayer.com/exchangerates_data/convert?from=EUR&to=SEK&amount=1&apikey=HZOwa1dYHjD4MEEA0KNPsOiOEwqhQcvt
> 429 Too Many Requests: "{"message":"You have exceeded your daily\/monthly API rate limit. Please review and upgrade your subscription plan at https:\/\/promptapi.com\/subscriptions to continue."}"
INFO: fetch url: http://api.apilayer.com/exchangerates_data/convert?from=SEK&to=USD&amount=1&apikey=HZOwa1dYHjD4MEEA0KNPsOiOEwqhQcvt
> 429 Too Many Requests: "{"message":"You have exceeded your daily\/monthly API rate limit. Please review and upgrade your subscription plan at https:\/\/promptapi.com\/subscriptions to continue."}"
INFO: fetch url: http://api.apilayer.com/exchangerates_data/convert?from=SEK&to=EUR&amount=1&apikey=HZOwa1dYHjD4MEEA0KNPsOiOEwqhQcvt
> 429 Too Many Requests: "{"message":"You have exceeded your daily\/monthly API rate limit. Please review and upgrade your subscription plan at https:\/\/promptapi.com\/subscriptions to continue."}"

### What does this App do?
This app sends periodical requests (queries) to an Currency Exchange Rate API server. The results would be stored in a database (DB), but I ran out of time to implement the DB version. E.g. a single table in a relational database would be a simple and useful solution. Due to lack of time the results are currently written to a flat text file ExchangeRatesLog.txt

The data is fetched with a 1 hour interval for all supported currency exchange rates:
- USD to EUR
- USD to SEK
- EUR to USD
- EUR to SEK
- SEK to USD
- SEK to EUR

The App source code can easily be modified to include other currencies.

But as my apikey (explained later) has exceeded both the max number of daily and hourly queries to the API server with my free of charge account, I do not get any data anymore. Therefore only error log files get generated. The file: ExchangeRatesLog.txt will get updated if you use a still valid apikey. 

With my apikey only the error log file: 
ExchangeRatesErrorLog.txt
gets updated with the reject messages from the API server.

The Eclipse maps the relative file path to some strange location in the directory tree.
The output may currently fail due to "File not Found".

### Note 1: This App has to be rewritten

The token value apikey (explained later) is needed in the Quartz Cron Job.
My original idea was to pass it as a parameter in the Job class constructor.
It turned out that Quartz does not allow any parameter there.

Therefore the apikey is read from a text file apikey.txt, but Eclipse maps the relative path of the file to a strange location in the directory tree.

Any apikey named parameters (String variable) outside of ExchangeRatesFetchJob.java are currently unused dummies.

Later I found a way to pass parameter(s) to Job class in a Quartz specific key/value map. But that solution requires rewriting this App in a different way. I had no time to handle it yet.

### Note 2: Renaming of packages/classes expected

The related packages/classes are to be renamed removing the substring "siili"/"Siili" from them. The source code is NOT from company the company Siili Solutions Oyj (siili.com).

I had not time to handle the renaming yet on 14 June 2022.

### Note 3: You need to create/have your own account at:
<https://apilayer.com/marketplace/exchangerates_data-api>

At the time of writing on June 14 2022, a free account allows up to 250 Currency Exchange queries a month. Today on June 14 2022 the daily limit is the same 250 as well.

You will see the up-to-date limits when you create/upgrade your account.

## apikey

You will get an own apikey token value when you create your account.
You can generate a new apikey whenever you are logged in at the web site - as long as you have not exceeded the limits of your current subscription.
Change of apikey does not help to exceed the limits of your subscription.

The apikey.txt needs to be updated in the file apikey.txt.

This is because, unexpectedly to me, the Quartz Cron Job does not allow passing any parameter to the Job class constructor. I will fix this problem later as the change requires lots of rewriting of the source code.

That's all for now.










