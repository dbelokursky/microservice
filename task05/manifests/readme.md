```shell script
kubectl create namespace m \
&& kubectl apply -f deployment-v1.yml -n m \
&& kubectl apply -f deployment-v2.yml -n m \
&& kubectl apply -f service-v1.yml -n m \
&& kubectl apply -f service-v2.yml -n m \
&& kubectl apply -f vs.yml -n m 
```

```shell script
minikube start --cpus=4 --memory=8g
```

Установка istio
```shell script
curl -L https://git.io/getLatestIstio | sh -

cd istio-${version}
export PATH=$PWD/bin:$PATH
istioctl install
```

Доступ к istio-ingress (127.0.0.1/version)
```shell script
minikube tunnel
```

When you set the istio-injection=enabled label on a namespace and the injection webhook is enabled, 
any new pods that are created in that namespace will automatically have a sidecar added to them.   
```shell script
kubectl label namespace m istio-injection=enabled --overwrite
```


---------

```yaml
minikube service -n <namespace> <service>
```