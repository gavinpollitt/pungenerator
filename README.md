curl -X GET http://localhost:8080/punme
curl --data '{"pun": "And this one for size"}' -H "content-type:application/json" -X POST http://localhost:8080/punme

Build:
docker build -t punner .

Run:
#host network to allow comms with external DB
docker run --rm --network host -d punner
