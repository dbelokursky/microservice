kubectl create namespace m \
&& helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ \
&& helm repo add bitnami https://charts.bitnami.com/bitnami
&& helm repo update \
&& helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress-controller.yaml
&& helm install my-release bitnami/postgresql

kubectl apply -f deployment.yml
kubectl apply -f service.yml
kubectl apply -f ingress.yml

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ \
&& helm repo add bitnami https://charts.bitnami.com/bitnami \
&& helm repo update \
&& helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress-controller.yaml \
&& helm install psql bitnami/postgresql --set persistence.existingClaim=postgres-pvc --set volumePermissions.enabled=true

helm install psql bitnami/postgresql \
--set persistence.existingClaim=postgres-pvc \
--set volumePermissions.enabled=true \
--set auth.username=user_pg \
--set auth.password=pass_pg \
--set auth.database=user_db 

export POSTGRES_PASSWORD=$(kubectl get secret --namespace m psql-postgresql -o jsonpath="{.data.postgres-password}" | base64 -d)