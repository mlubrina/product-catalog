#!/bin/bash
echo "Start System Tests" >system-test-results.log
# Define the list of curl parameters and their expected responses
curl_params=(
  "http://localhost:8080/prices?applicationDate=2020-06-14T00:00:00&productId=35455&brandId=1"
  "http://localhost:8080/prices?applicationDate=2020-06-14T15:20:00&productId=35455&brandId=1"
  "http://localhost:8080/prices?applicationDate=2020-06-15T00:10:00&productId=35455&brandId=1"
  "http://localhost:8080/prices?applicationDate=2020-06-15T16:10:00&productId=35455&brandId=1"
)
expected_responses=(
  '{"productId":35455,"brandId":1,"priceList":1,"startDate":"2020-06-14T00:00:00","endDate":"2020-12-31T23:59:59","price":35.5}'
  '{"productId":35455,"brandId":1,"priceList":2,"startDate":"2020-06-14T15:00:00","endDate":"2020-06-14T18:30:00","price":25.45}'
  '{"productId":35455,"brandId":1,"priceList":3,"startDate":"2020-06-15T00:00:00","endDate":"2020-06-15T11:00:00","price":30.5}'
  '{"productId":35455,"brandId":1,"priceList":4,"startDate":"2020-06-15T16:00:00","endDate":"2020-12-31T23:59:59","price":38.95}'
)

# Run the application using Maven
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev >/dev/null 2>&1 &
app_pid=$!
# Wait for the application to start
sleep 5

# Iterate over the curl parameters and expected responses
for i in "${!curl_params[@]}"; do
  param="${curl_params[$i]}"
  expected_response="${expected_responses[$i]}"

  # Make the curl request and store the response
  curl_result=$(curl --silent --location "$param")

  # Verify the response
  if [ "$curl_result" = "$expected_response" ]; then
    echo "Test $i passed: Response $curl_result matches expected result for parameter: $param" >>system-test-results.log
  else
    echo "Test $i failed: Response $curl_result does not match expected result: $expected_response" >>system-test-results.log
  fi
done

# Stop the application
kill $app_pid

echo "End System Tests" >>system-test-results.log

cat system-test-results.log
# Exit the script
exit
