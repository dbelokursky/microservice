kubectl create namespace m \
&& helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ \
&& helm repo update \
&& helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress-controller.yaml

kubectl apply -f deployment.yml
kubectl apply -f service.yml
kubectl apply -f ingress.yml
