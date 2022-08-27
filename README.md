# Dummy Spring Boot REST on Kubernetes


./gradlew bootBuildImage
***
docker run --rm --name catalog-service -p 8080:8080 --platform linux/amd64 catalog-service:0.0.1-SNAPSHOT

open http://localhost:8080
***
minikube start

minikube image load catalog-service:0.0.1-SNAPSHOT

kubectl create deployment catalog-service --image=catalog-service:0.0.1-SNAPSHOT

kubectl get deployment

kubectl get pod

kubectl logs deployment/catalog-service

kubectl expose deployment catalog-service --name=catalog-service --port=8080

kubectl get service catalog-service

kubectl port-forward service/catalog-service 8000:8080

open http://localhost:8000

kubectl delete service catalog-service

kubectl delete deployment catalog-service

minikube stop
