# Lancement de l'application

	`cd qformation-web && mvn exec:exec -Dexec.executable="C:\dev\ztoolbox\jdk10_0_2_oracle\bin\java.exe" -Dexec.args="-jar target/${project.artifactId}-${project.version}.jar"`

# Lancement des tests selenium

Par défaut dans maven, ils sont lancés sur chrome par défault et localement.

	cd qformation-web && mvn verify -DTEST_IHM=true -Denforcer.skip=true -DSELENIUM_FRONT_URL=http://localhost:80 [-DSELENIUM_HUB_URL=https://monhubselenium] [-DSELENIUM_BROWSER=chrome]
	