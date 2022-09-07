### Minikube env
- minikube addons enable ingress
- minikube addons enable ingress-dns
- minikube addons enable metallb
- minikube addons configure metallb
- kubectl create clusterrole deploy --verb=get,list,watch,create,delete,patch,update --resource=services,endpoints
- minikube docker-env (pobierz ip docker.host)

### m2/settings.xml
```xml 
<profile>
            <id>docker</id>
            <properties>
		<docker.host>tcp://192.168.35.219:2376</docker.host>
		<docker.certPath>C:\Users\User\DOCUME~1\MOBAXT~1\home\.minikube\certs</docker.certPath>
	    </properties>
</profile>
```
### ZIPKIN
- kubectl create deployment zipkin --image openzipkin/zipkin
- kubectl expose deployment zipkin --type ClusterIP --port 9411
- minikube service zipkin


### HELM & prometheus & grafana
- helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
- helm install prometheus prometheus-community/kube-prometheus-stack
- kubectl expose service prometheus-grafana --type=NodePort --target-port=3000 --name=prometheus-grafana-np
- kubectl expose service prometheus-operated --type=NodePort --target-port=9090 --name=prometheus-operator-np
- kubectl get secret --namespace default prometheus-grafana -o jsonpath="{.data.admin-password}" | base64 -d ; echo
- minikube service prometheus-grafana-np
- kubectl apply -f service-monitor-prometheus.yaml

---
- kubectl port-forward svc/prometheus-operated 9090:9090
- helm install test ./test
---

### Resources
https://github.com/prometheus-community/helm-charts/tree/main/charts/kube-prometheus-stack
