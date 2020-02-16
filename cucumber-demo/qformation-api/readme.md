#Lancement de l'api

	mvn exec:exec -Dexec.executable="C:\dev\ztoolbox\jdk10_0_2_oracle\bin\java.exe" -Dexec.args="-jar target/${project.artifactId}-${project.version}.jar"