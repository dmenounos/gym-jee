#!/bin/sh

case $1 in
init)
	./docker-db.sh init
	./docker-as.sh init
	;;
start)
	./docker-db.sh start
	./docker-as.sh start
	;;
stop)
	./docker-db.sh stop
	./docker-as.sh stop
	;;
remove)
	./docker-db.sh remove
	./docker-as.sh remove
	;;
*)
	echo
	echo "Usage: $0 { init | start | stop | remove }"
	echo
	exit 1
	;;
esac
