1. Prepare the environment variables

In case your development server does not run on localhost,
edit the pom file and adjust the appropriate profile for your environment.

	E.g., Check hostname:
	sed -n "s/localhost/&/p" pom.xml

	E.g., Adjust hostname for docker:
	sed -i "s/localhost/172.17.0.2/" pom.xml


2. Build and Deploy the project, using the appropriate profile for your environment

	Either build:
	mvn install -P mysql-env,wildfly-env

	Or build and deploy:
	mvn install -P mysql-env,wildfly-env,wildfly-deploy


3. After the project artifacts are deployed check

	E.g.:
	http://localhost:8080/gym-jee-ui/
