# MaerskTest

1. Availability Check
URL: http://localhost:8080/api/bookings/getAvailability
Body: {
    "containerType": "DRY",
    "containerSize": 20,
    "origin": "Southampton",
    "destination": "Singapore",
    "quantity": 5
}

internally it will call external api endpoint (https://maersk.com/api/bookings/checkAvailable) which is not avaible so response will not be as expected.
Since actuall reponse from external endpoint in not know. Expect compilation issue in ContainerBookingServiceImpl as response is assumed.
