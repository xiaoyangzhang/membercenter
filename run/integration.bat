cd /d %~dp0..
call mvn clean package -DskipTests

call autoconfig %~dp0..\target\activity-service.zip -u %~dp0act-integration.properties
@pause