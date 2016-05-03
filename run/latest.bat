cd /d %~dp0..
call mvn clean package -DskipTests

call autoconfig %~dp0..\target\membercenter.war -u %~dp0member.pre.properties
@pause