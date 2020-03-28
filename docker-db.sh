#!/bin/bash

IMAGE="dmen/mysql:5.5"
CONTAINER="gym-jee-db"

MYSQL_USERNAME="gym_db"
MYSQL_PASSWORD="gym_db"
MYSQL_DATABASE="gym_db"

case $1 in
init)
	docker run -d -it \
	-v $(pwd)/db:/tmp/init-ext \
	--env MYSQL_USERNAME="$MYSQL_USERNAME" \
	--env MYSQL_PASSWORD="$MYSQL_PASSWORD" \
	--env MYSQL_DATABASE="$MYSQL_DATABASE" \
	--name $CONTAINER \
	$IMAGE
	;;
start)
	docker start $CONTAINER
	;;
stop)
	docker stop $CONTAINER
	;;
remove)
	docker rm $CONTAINER
	;;
logs)
	docker logs $CONTAINER
	;;
exec)
	if ([ "$#" -eq "1" ]); then
		echo
		echo "Run a command as root:"
		echo "$0 exec bash -l"
		echo "$0 exec bash -l -c \"uname -r\""
		echo "$0 exec bash -l -c \"lsb_release -a\""
		echo
		echo "Log as user:"
		echo "$0 exec su -l mysql -s /bin/bash"
		echo
		exit 1
	fi

	# $#    : the number of arguments, not counting $0
	# $@    : all positional parameters except $0
	# shift : discards the first parameter

	shift
	docker exec -it $CONTAINER "$@"
	;;
ip)
	docker inspect $CONTAINER | grep IPAddress | grep -v null | head -1 | cut -d'"' -f4
	;;
*)
	echo
	echo "Usage: $0 { init | start | stop | remove | logs | exec | ip }"
	echo
	exit 1
	;;
esac
