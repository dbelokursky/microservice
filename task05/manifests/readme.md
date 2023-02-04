```shell script
kubectl create namespace m \
&& kubectl apply -f secret.yml -n m \
&& kubectl apply -f config-map.yml -n m \
&& kubectl apply -f deployment.yml -n m \
&& kubectl apply -f service.yml -n m \
&& kubectl apply -f postgres-pv.yml -n m \
&& kubectl apply -f postgres-pvc.yml -n m \
&& kubectl apply -f service-monitor.yml -n m

kubectl delete -f secret.yml -n m \
&& kubectl delete -f config-map.yml -n m \
&& kubectl delete -f deployment.yml -n m \
&& kubectl delete -f service.yml -n m \
&& kubectl delete -f ingress.yml -n m \
&& kubectl delete -f postgres-pv.yml -n m \
&& kubectl delete -f postgres-pvc.yml -n m \
&& kubectl delete -f service-monitor.yml -n m 

helm repo add bitnami https://charts.bitnami.com/bitnami \
&& helm repo add prometheus-community https://prometheus-community.github.io/helm-charts \
&& helm install psql bitnami/postgresql -n m \
--set persistence.existingClaim=postgres-pvc \
--set volumePermissions.enabled=true \
--set auth.username=user_pg \
--set auth.password=pass_pg \
--set auth.database=user_db \
&& helm install prometheus prometheus-community/kube-prometheus-stack -n m 

```
### Install an ingress gateway:
```shell script
kubectl create namespace istio-system \
&& kubectl create namespace istio-ingress \
&& kubectl label namespace istio-ingress istio-injection=enabled \
&& helm repo add istio https://istio-release.storage.googleapis.com/charts \
&& helm repo update \
&& helm install istio-base istio/base -n istio-system \
&& helm install istiod istio/istiod -n istio-system --wait \
&& helm delete istio-ingress istio/gateway -n istio-ingress --wait
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
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts \
&& helm repo update
```
Установить оператор, разворачивающий Jaeger:

```shell script
kubectl create namespace jaeger-operator \
&& helm install --version "2.19.0" -n jaeger-operator -f jaeger/operator-values.yaml \
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

Открыть web-интерфейс Jaeger:

```shell script
minikube service -n jaeger jaeger-query-nodeport
```

Установить оператор, разворачивающий Istio:

```shell script
istioctl operator init --watchedNamespaces istio-system --operatorNamespace istio-operator
```

Развернуть Istio c помощью оператора:

```shell script
kubectl apply -f istio/istio.yaml
```

Проверить состояние Istio:

```shell script
kubectl get all -n istio-system -l istio.io/rev=default
```

Установить настройки по-умолчанию:

```shell
kubectl apply -f istio/disable-mtls.yaml
```

### Устанавливаем Kiali

Kiali - доска управления Service mesh

Добавить репозиторий в Helm:

```shell script
helm repo add kiali https://kiali.org/helm-charts
helm repo update
```

Установить Kiali Operator, разворачивающий Kiali

```shell script
helm install --version "1.33.1" -n kiali-operator -f kiali/operator-values.yaml kiali-operator kiali/kiali-operator
```

Развернуть Kiali:

```shell script
kubectl apply -f kiali/kiali.yaml
```

Проверить состояние Kiali:

```shell script
kubectl get po -n kiali -l app.kubernetes.io/name=kiali
```
