kubectl create namespace m

kubectl apply -f secret.yml
kubectl apply -f config-map.yml
kubectl apply -f deployment.yml
kubectl apply -f service.yml
kubectl apply -f ingress.yml
kubectl apply -f postgres-pv.yml
kubectl apply -f postgres-pvc.yml

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ \
&& helm repo add bitnami https://charts.bitnami.com/bitnami \
&& helm repo update \
&& helm install nginx ingress-nginx/ingress-nginx -n m -f nginx-ingress-controller.yml \
&& helm install psql bitnami/postgresql -n m \
--set persistence.existingClaim=postgres-pvc \
--set volumePermissions.enabled=true \
--set auth.username=user_pg \
--set auth.password=pass_pg \
--set auth.database=user_db
