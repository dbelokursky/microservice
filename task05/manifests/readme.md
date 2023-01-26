```shell script
kubectl create namespace m

kubectl apply -f secret.yml -n m
kubectl apply -f config-map.yml -n m
kubectl apply -f deployment.yml -n m
kubectl apply -f service.yml -n m
kubectl apply -f ingress.yml -n m
kubectl apply -f postgres-pv.yml -n m
kubectl apply -f postgres-pvc.yml -n m
kubectl apply -f service-monitor.yml -n 
# kubectl apply -f nginx-ingress-service-monitor.yml -n m

kubectl delete -f secret.yml -n m
kubectl delete -f config-map.yml -n m
kubectl delete -f deployment.yml -n m
kubectl delete -f service.yml -n m
kubectl delete -f ingress.yml -n m
kubectl delete -f postgres-pv.yml -n m
kubectl delete -f postgres-pvc.yml -n m
kubectl delete -f service-monitor.yml -n m
# kubectl delete -f nginx-ingress-service-monitor.yml -n m

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ \
&& helm repo add bitnami https://charts.bitnami.com/bitnami \
&& helm repo add prometheus-community https://prometheus-community.github.io/helm-charts \
# && helm install nginx ingress-nginx/ingress-nginx -n m -f nginx-ingress-controller.yml \
&& helm install psql bitnami/postgresql -n m \
--set persistence.existingClaim=postgres-pvc \
--set volumePermissions.enabled=true \
--set auth.username=user_pg \
--set auth.password=pass_pg \
--set auth.database=user_db \
&& helm install prometheus prometheus-community/kube-prometheus-stack -n m 

# && helm upgrade nginx ingress-nginx \
# --repo https://kubernetes.github.io/ingress-nginx \
# --namespace m \
# --set controller.metrics.enabled=true \
# --set-string controller.podAnnotations."prometheus\.io/scrape"="true" \
# --set-string controller.podAnnotations."prometheus\.io/port"="10254"
```
### Install an ingress gateway:
```shell script
helm repo add istio https://istio-release.storage.googleapis.com/charts
helm repo update 
helm install istio-base istio/base -n istio-system 
helm install istiod istio/istiod -n istio-system --wait
kubectl create namespace istio-system
kubectl create namespace istio-ingress
kubectl label namespace istio-ingress istio-injection=enabled
helm install istio-ingress istio/gateway -n istio-ingress --wait
```

### Status of the installation can be verified using Helm:
```shell script
helm status istiod -n istio-system
```
### Разворачиваем Jaeger

Jaeger - решение трассировки. Компоненты Istio, такие как: sidecar-контейнер, gateway, отправляют данные запросов в
систему. Таким образом получается полная трассировка запроса.

Добавить репозиторий в Helm:

```shell script
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
helm repo update
```
Установить оператор, разворачивающий Jaeger:

```shell script
helm install --version "2.19.0" -n jaeger-operator -f jaeger/operator-values.yaml \
jaeger-operator jaegertracing/jaeger-operator
``` 

Развернуть Jaeger:

```shell script
kubectl apply -f jaeger/jaeger.yaml
```

Проверить состояние Jaeger:

```shell script
kubectl get po -n jaeger -l app.kubernetes.io/instance=jaeger
```